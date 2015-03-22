
package com.etb.sway;

import com.etb.sway.model.AbstractExpandableDataProvider;
import com.etb.sway.fragment.ExpandableItemPinnedMessageDialogFragment;
import com.etb.sway.model.PoiDataProviderHolderInterface;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.enums.SnackbarType;
import com.nispok.snackbar.listeners.ActionClickListener;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExpandableDraggableSwipeableFragment extends Fragment implements ExpandableItemPinnedMessageDialogFragment.EventListener {
    private static final String FRAGMENT_LIST_VIEW = "list view";
    private static final String FRAGMENT_TAG_ITEM_PINNED_DIALOG = "item pinned dialog";

    public static ExpandableDraggableSwipeableFragment newInstance() {
        ExpandableDraggableSwipeableFragment fragment = new ExpandableDraggableSwipeableFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view= super.onCreateView(inflater, container, savedInstanceState);

        getFragmentManager().beginTransaction()
                .add(R.id.container, new RecyclerListViewFragment(), FRAGMENT_LIST_VIEW)
                .commit();

        return view;
    }

    /**
     * This method will be called when a group item is removed
     *
     * @param groupPosition The position of the group item within data set
     */
    public void onGroupItemLikedRemoved(int groupPosition) {
        final AbstractExpandableDataProvider.GroupData group = ((PoiDataProviderHolderInterface) getActivity()).getDataProvider()
                .getLastGroupRemoved();
        ((PoiDataProviderHolderInterface) getActivity()).getDataProvider().addLikeItem(group);

        SnackbarManager.show(
                Snackbar.with(getActivity().getApplicationContext())
                        .text(R.string.snack_bar_text_group_item_added)
                        .actionLabel(R.string.snack_bar_action_undo)
                        .actionListener(new ActionClickListener() {
                            @Override
                            public void onActionClicked(Snackbar snackbar) {
                                onItemUndoActionClicked();
                                ((PoiDataProviderHolderInterface) getActivity()).getDataProvider().onUndo(
                                        group);
                            }
                        })
                        .actionColorResource(R.color.snackbar_action_color_done)
                        .duration(5000)
                        .type(SnackbarType.SINGLE_LINE)
                        .swipeToDismiss(true)
                , getActivity());
    }

    public void onGroupItemDisLikedRemoved(int groupPosition) {
        final AbstractExpandableDataProvider.GroupData group = ((PoiDataProviderHolderInterface) getActivity()).getDataProvider()
                .getLastGroupRemoved();
        ((PoiDataProviderHolderInterface) getActivity()).getDataProvider().addDisLikeItem(group);

        SnackbarManager.show(
                Snackbar.with(getActivity().getApplicationContext())
                        .text(R.string.snack_bar_text_group_item_removed)
                        .actionLabel(R.string.snack_bar_action_undo)
                        .actionListener(new ActionClickListener() {
                            @Override
                            public void onActionClicked(Snackbar snackbar) {
                                onItemUndoActionClicked();
                                ((PoiDataProviderHolderInterface) getActivity()).getDataProvider().onUndo(
                                        group);
                            }
                        })
                        .actionColorResource(R.color.snackbar_action_color_done)
                        .duration(5000)
                        .type(SnackbarType.SINGLE_LINE)
                        .swipeToDismiss(true)
                , getActivity());
    }

    /**
     * This method will be called when a child item is removed
     *
     * @param groupPosition The group position of the child item within data set
     * @param childPosition The position of the child item within the group
     */
    public void onChildItemRemoved(int groupPosition, int childPosition) {
        SnackbarManager.show(
                Snackbar.with(getActivity().getApplicationContext())
                        .text(R.string.snack_bar_text_child_item_removed)
                        .actionLabel(R.string.snack_bar_action_undo)
                        .actionListener(new ActionClickListener() {
                            @Override
                            public void onActionClicked(Snackbar snackbar) {
                                onItemUndoActionClicked();
                            }
                        })
                        .actionColorResource(R.color.snackbar_action_color_done)
                        .duration(5000)
                        .type(SnackbarType.SINGLE_LINE)
                        .swipeToDismiss(false)
                , getActivity());
    }

    /**
     * This method will be called when a group item is pinned
     *
     * @param groupPosition The position of the group item within data set
     */
    public void onGroupItemPinned(int groupPosition) {
        final DialogFragment dialog = ExpandableItemPinnedMessageDialogFragment.newInstance(groupPosition, RecyclerView.NO_POSITION);

        getFragmentManager()
                .beginTransaction()
                .add(dialog, FRAGMENT_TAG_ITEM_PINNED_DIALOG)
                .commit();
    }

    /**
     * This method will be called when a child item is pinned
     *
     * @param groupPosition The group position of the child item within data set
     * @param childPosition The position of the child item within the group
     */
    public void onChildItemPinned(int groupPosition, int childPosition) {
        final DialogFragment dialog = ExpandableItemPinnedMessageDialogFragment.newInstance(groupPosition, childPosition);

        getFragmentManager()
                .beginTransaction()
                .add(dialog, FRAGMENT_TAG_ITEM_PINNED_DIALOG)
                .commit();
    }

    public void onGroupItemClicked(int groupPosition) {
        final Fragment fragment = getFragmentManager().findFragmentByTag(FRAGMENT_LIST_VIEW);
        AbstractExpandableDataProvider.GroupData data = ((PoiDataProviderHolderInterface) getActivity()).getDataProvider().getGroupItem(groupPosition);

        if (data.isPinnedToSwipeLeft()) {
            // unpin if tapped the pinned item
            data.setPinnedToSwipeLeft(false);
            ((RecyclerListViewFragment) fragment).notifyGroupItemChanged(groupPosition);
        }
    }

    private void onItemUndoActionClicked() {
        final Fragment fragment = getFragmentManager().findFragmentByTag(FRAGMENT_LIST_VIEW);
        final long result = ((PoiDataProviderHolderInterface) getActivity()).getDataProvider().undoLastRemoval();

        if (result == RecyclerViewExpandableItemManager.NO_EXPANDABLE_POSITION) {
            return;
        }

        final int groupPosition = RecyclerViewExpandableItemManager.getPackedPositionGroup(result);
        final int childPosition = RecyclerViewExpandableItemManager.getPackedPositionChild(result);

        if (childPosition == RecyclerView.NO_POSITION) {
            // group item
            ((RecyclerListViewFragment) fragment).notifyGroupItemRestored(groupPosition);
        } else {
            // child item
            ((RecyclerListViewFragment) fragment).notifyChildItemRestored(groupPosition, childPosition);
        }
    }

    // implements ExpandableItemPinnedMessageDialogFragment.EventListener
    @Override
    public void onNotifyExpandableItemPinnedDialogDismissed(int groupPosition, int childPosition, boolean ok) {
        final Fragment fragment = getFragmentManager().findFragmentByTag(FRAGMENT_LIST_VIEW);

        if (childPosition == RecyclerView.NO_POSITION) {
            // group item
            ((PoiDataProviderHolderInterface) getActivity()).getDataProvider().getGroupItem(
                    groupPosition).setPinnedToSwipeLeft(ok);
            ((RecyclerListViewFragment) fragment).notifyGroupItemChanged(groupPosition);
        }
    }


}
