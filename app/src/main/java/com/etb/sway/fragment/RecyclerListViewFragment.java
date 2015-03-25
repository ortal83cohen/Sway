package com.etb.sway.fragment;

import com.etb.sway.MainActivity;
import com.etb.sway.R;
import com.etb.sway.adapter.ExpandableDraggableSwipeableItemAdapter;
import com.etb.sway.model.AbstractExpandableDataProvider;
import com.etb.sway.model.PoiDataProviderHolderInterface;
import com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.ItemShadowDecorator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.SimpleListDividerDecorator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;

import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecyclerListViewFragment extends Fragment {

    private static final String SAVED_STATE_EXPANDABLE_ITEM_MANAGER
            = "RecyclerViewExpandableItemManager";

    private static String DRAGGABLE_SWIPEABLE_FRAGMENT = MainActivity.DRAGGABLE_SWIPEABLE_FRAGMENT;

    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.Adapter mWrappedAdapter;

    private RecyclerViewExpandableItemManager mRecyclerViewExpandableItemManager;

    private RecyclerViewDragDropManager mRecyclerViewDragDropManager;

    private RecyclerViewSwipeManager mRecyclerViewSwipeManager;

    private RecyclerViewTouchActionGuardManager mRecyclerViewTouchActionGuardManager;

    public RecyclerListViewFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_list_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //noinspection ConstantConditions
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());

        final Parcelable eimSavedState = (savedInstanceState != null) ? savedInstanceState
                .getParcelable(SAVED_STATE_EXPANDABLE_ITEM_MANAGER) : null;
        mRecyclerViewExpandableItemManager = new RecyclerViewExpandableItemManager(eimSavedState);

        // touch guard manager  (this class is required to suppress scrolling while swipe-dismiss animation is running)
        mRecyclerViewTouchActionGuardManager = new RecyclerViewTouchActionGuardManager();
        mRecyclerViewTouchActionGuardManager
                .setInterceptVerticalScrollingWhileAnimationRunning(true);
        mRecyclerViewTouchActionGuardManager.setEnabled(true);

        // drag & drop manager
        mRecyclerViewDragDropManager = new RecyclerViewDragDropManager();
        mRecyclerViewDragDropManager.setDraggingItemShadowDrawable(
                (NinePatchDrawable) getResources().getDrawable(R.drawable.material_shadow_z3));

        // swipe manager
        mRecyclerViewSwipeManager = new RecyclerViewSwipeManager();

        //adapter
        getDataProvider();
        final ExpandableDraggableSwipeableItemAdapter
                myItemAdapter = new ExpandableDraggableSwipeableItemAdapter(getDataProvider(),
                getActivity());

        myItemAdapter.setEventListener(new ExpandableDraggableSwipeableItemAdapter.EventListener() {
            @Override
            public void onGroupItemLikedRemoved(int groupPosition) {
                ((ExpandableDraggableSwipeableFragment) getFragmentManager()
                        .findFragmentByTag(DRAGGABLE_SWIPEABLE_FRAGMENT)).onGroupItemLikedRemoved(
                        groupPosition);
            }

            @Override
            public void onChildItemRemoved(int groupPosition, int childPosition) {
//                ((ExpandableDraggableSwipeableFragment) getFragmentManager()
//                        .findFragmentByTag(DRAGGABLE_SWIPEABLE_FRAGMENT))
//                        .onChildItemRemoved(groupPosition, childPosition);
            }

            @Override
            public void onGroupItemDisLikedRemoved(int groupPosition) {
                ((ExpandableDraggableSwipeableFragment) getFragmentManager()
                        .findFragmentByTag(DRAGGABLE_SWIPEABLE_FRAGMENT))
                        .onGroupItemDisLikedRemoved(
                                groupPosition);
            }

            @Override
            public void onChildItemPinned(int groupPosition, int childPosition) {
//                ((ExpandableDraggableSwipeableFragment) getFragmentManager()
//                        .findFragmentByTag(DRAGGABLE_SWIPEABLE_FRAGMENT))
//                        .onChildItemPinned(groupPosition, childPosition);
            }

            @Override
            public void onItemViewClicked(View v, boolean pinned) {
                onItemViewClick(v, pinned);
            }
        });

        mAdapter = myItemAdapter;

        mWrappedAdapter = mRecyclerViewExpandableItemManager
                .createWrappedAdapter(myItemAdapter);       // wrap for expanding
        mWrappedAdapter = mRecyclerViewDragDropManager
                .createWrappedAdapter(mWrappedAdapter);           // wrap for dragging
        mWrappedAdapter = mRecyclerViewSwipeManager
                .createWrappedAdapter(mWrappedAdapter);      // wrap for swiping

        final GeneralItemAnimator animator = new SwipeDismissItemAnimator();

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mWrappedAdapter);  // requires *wrapped* adapter
        mRecyclerView.setItemAnimator(animator);
        mRecyclerView.setHasFixedSize(false);

        // additional decorations
        //noinspection StatementWithEmptyBody
        if (supportsViewElevation()) {
            // Lollipop or later has native drop shadow feature. ItemShadowDecorator is not required.
        } else {
            mRecyclerView.addItemDecoration(new ItemShadowDecorator(
                    (NinePatchDrawable) getResources().getDrawable(R.drawable.material_shadow_z1)));
        }
        mRecyclerView.addItemDecoration(
                new SimpleListDividerDecorator(getResources().getDrawable(R.drawable.list_divider),
                        true));

        // NOTE:
        // The initialization order is very important! This order determines the priority of touch event handling.
        //
        // priority: TouchActionGuard > Swipe > DragAndDrop > ExpandableItem
        mRecyclerViewTouchActionGuardManager.attachRecyclerView(mRecyclerView);
        mRecyclerViewSwipeManager.attachRecyclerView(mRecyclerView);
        mRecyclerViewDragDropManager.attachRecyclerView(mRecyclerView);
        mRecyclerViewExpandableItemManager.attachRecyclerView(mRecyclerView);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save current state to support screen rotation, etc...
        if (mRecyclerViewExpandableItemManager != null) {
            outState.putParcelable(
                    SAVED_STATE_EXPANDABLE_ITEM_MANAGER,
                    mRecyclerViewExpandableItemManager.getSavedState());
        }
    }

    @Override
    public void onDestroyView() {
        if (mRecyclerViewDragDropManager != null) {
            mRecyclerViewDragDropManager.release();
            mRecyclerViewDragDropManager = null;
        }

        if (mRecyclerViewSwipeManager != null) {
            mRecyclerViewSwipeManager.release();
            mRecyclerViewSwipeManager = null;
        }

        if (mRecyclerViewTouchActionGuardManager != null) {
            mRecyclerViewTouchActionGuardManager.release();
            mRecyclerViewTouchActionGuardManager = null;
        }

        if (mRecyclerViewExpandableItemManager != null) {
            mRecyclerViewExpandableItemManager.release();
            mRecyclerViewExpandableItemManager = null;
        }

        if (mRecyclerView != null) {
            mRecyclerView.setItemAnimator(null);
            mRecyclerView.setAdapter(null);
            mRecyclerView = null;
        }

        if (mWrappedAdapter != null) {
            WrapperAdapterUtils.releaseAll(mWrappedAdapter);
            mWrappedAdapter = null;
        }
        mAdapter = null;
        mLayoutManager = null;

        super.onDestroyView();
    }

    private void onItemViewClick(View v, boolean pinned) {
        final int flatPosition = mRecyclerView.getChildPosition(v);

        if (flatPosition == RecyclerView.NO_POSITION) {
            return;
        }

        final long expandablePosition = mRecyclerViewExpandableItemManager
                .getExpandablePosition(flatPosition);
        final int groupPosition = RecyclerViewExpandableItemManager
                .getPackedPositionGroup(expandablePosition);
        final int childPosition = RecyclerViewExpandableItemManager
                .getPackedPositionChild(expandablePosition);

        if (childPosition == RecyclerView.NO_POSITION) {
            ((ExpandableDraggableSwipeableFragment) getFragmentManager()
                    .findFragmentByTag(DRAGGABLE_SWIPEABLE_FRAGMENT)).onGroupItemClicked(
                    groupPosition);
        }
    }

    private boolean supportsViewElevation() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
    }

    public AbstractExpandableDataProvider getDataProvider() {
        return ((PoiDataProviderHolderInterface) getActivity()).getDataProvider();
    }

    public void notifyGroupItemRestored(int groupPosition) {
        mAdapter.notifyDataSetChanged();

        final long expandablePosition = RecyclerViewExpandableItemManager
                .getPackedPositionForGroup(groupPosition);
        final int flatPosition = mRecyclerViewExpandableItemManager
                .getFlatPosition(expandablePosition);
        mRecyclerView.scrollToPosition(flatPosition);
    }

    public void notifyChildItemRestored(int groupPosition, int childPosition) {
        mAdapter.notifyDataSetChanged();

        final long expandablePosition = RecyclerViewExpandableItemManager
                .getPackedPositionForChild(groupPosition, childPosition);
        final int flatPosition = mRecyclerViewExpandableItemManager
                .getFlatPosition(expandablePosition);
        mRecyclerView.scrollToPosition(flatPosition);
    }

    public void notifyGroupItemChanged(int groupPosition) {
        final long expandablePosition = RecyclerViewExpandableItemManager
                .getPackedPositionForGroup(groupPosition);
        final int flatPosition = mRecyclerViewExpandableItemManager
                .getFlatPosition(expandablePosition);

        mAdapter.notifyItemChanged(flatPosition);
    }

    public void notifyChildItemChanged(int groupPosition, int childPosition) {
        final long expandablePosition = RecyclerViewExpandableItemManager
                .getPackedPositionForChild(groupPosition, childPosition);
        final int flatPosition = mRecyclerViewExpandableItemManager
                .getFlatPosition(expandablePosition);

        mAdapter.notifyItemChanged(flatPosition);
    }
}
