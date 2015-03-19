package com.etb.sway.adapter;

import com.etb.sway.R;
import com.etb.sway.model.AbstractExpandableDataProvider;
import com.etb.sway.utils.AdapterUtils;
import com.etb.sway.utils.ViewUtils;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableSwipeableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableSwipeableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;

import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandableDraggableSwipeableItemAdapter
        extends
        AbstractExpandableItemAdapter<ExpandableDraggableSwipeableItemAdapter.MyGroupViewHolder, ExpandableDraggableSwipeableItemAdapter.MyChildViewHolder>
        implements
        ExpandableDraggableItemAdapter<ExpandableDraggableSwipeableItemAdapter.MyGroupViewHolder, ExpandableDraggableSwipeableItemAdapter.MyChildViewHolder>,
        ExpandableSwipeableItemAdapter<ExpandableDraggableSwipeableItemAdapter.MyGroupViewHolder, ExpandableDraggableSwipeableItemAdapter.MyChildViewHolder> {

    private static final String TAG = "MyEDSItemAdapter";

    private AbstractExpandableDataProvider mProvider;

    private EventListener mEventListener;

    private View.OnClickListener mItemViewOnClickListener;

    private View.OnClickListener mSwipeableViewContainerOnClickListener;

    public ExpandableDraggableSwipeableItemAdapter(AbstractExpandableDataProvider dataProvider) {
        mProvider = dataProvider;
        mItemViewOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemViewClick(v);
            }
        };
        mSwipeableViewContainerOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSwipeableViewContainerClick(v);
            }
        };

        // ExpandableItemAdapter, ExpandableDraggableItemAdapter and ExpandableSwipeableItemAdapter
        // require stable ID, and also have to implement the getGroupItemId()/getChildItemId() methods appropriately.
        setHasStableIds(true);
    }

    private void onItemViewClick(View v) {
        if (mEventListener != null) {
            mEventListener.onItemViewClicked(v, true);  // true --- pinned
        }
    }

    private void onSwipeableViewContainerClick(View v) {
        if (mEventListener != null) {
            mEventListener.onItemViewClicked(AdapterUtils.findParentViewHolderItemView(v),
                    false);  // false --- not pinned
        }
    }

    @Override
    public int getGroupCount() {
        return mProvider.getGroupCount();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return mProvider.getChildCount(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mProvider.getGroupItem(groupPosition).getGroupId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mProvider.getChildItem(groupPosition, childPosition).getChildId();
    }

    @Override
    public int getGroupItemViewType(int groupPosition) {
        return 0;
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public MyGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.list_group_item, parent, false);
        return new MyGroupViewHolder(v);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.list_item, parent, false);
        return new MyChildViewHolder(v);
    }

    @Override
    public void onBindGroupViewHolder(MyGroupViewHolder holder, int groupPosition, int viewType) {
        // group item
        final AbstractExpandableDataProvider.GroupData item = mProvider.getGroupItem(groupPosition);

        // set listeners
        holder.itemView.setOnClickListener(mItemViewOnClickListener);

        // set text
        holder.mTextView.setText(item.getTitle());
        holder.mImageView.setImageDrawable(item.getCardImage());

        // set background resource (target view ID: container)
        final int dragState = holder.getDragStateFlags();
        final int expandState = holder.getExpandStateFlags();
        final int swipeState = holder.getSwipeStateFlags();

        if (((dragState & RecyclerViewDragDropManager.STATE_FLAG_IS_UPDATED) != 0) ||
                ((expandState & RecyclerViewExpandableItemManager.STATE_FLAG_IS_UPDATED) != 0) ||
                ((swipeState & RecyclerViewSwipeManager.STATE_FLAG_IS_UPDATED) != 0)) {
            int bgResId;

            if ((dragState & RecyclerViewDragDropManager.STATE_FLAG_IS_ACTIVE) != 0) {
                bgResId = R.drawable.bg_group_item_dragging_active_state;
            } else if ((dragState & RecyclerViewDragDropManager.STATE_FLAG_DRAGGING) != 0) {
                bgResId = R.drawable.bg_group_item_dragging_state;
            } else if ((swipeState & RecyclerViewSwipeManager.STATE_FLAG_IS_ACTIVE) != 0) {
                bgResId = R.drawable.bg_group_item_swiping_active_state;
            } else if ((swipeState & RecyclerViewSwipeManager.STATE_FLAG_SWIPING) != 0) {
                bgResId = R.drawable.bg_group_item_swiping_state;
            } else if ((expandState & RecyclerViewExpandableItemManager.STATE_FLAG_IS_EXPANDED)
                    != 0) {
                bgResId = R.drawable.bg_group_item_expanded_state;
            } else {
                bgResId = R.drawable.bg_group_item_normal_state;
            }

            holder.mContainer.setBackgroundResource(bgResId);
        }

        // set swiping properties
        holder.setSwipeItemSlideAmount(
                item.isPinnedToSwipeLeft() ? RecyclerViewSwipeManager.OUTSIDE_OF_THE_WINDOW_LEFT
                        : 0);
    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder holder, int groupPosition,
            int childPosition, int viewType) {
        // child item
        final AbstractExpandableDataProvider.ChildData item = mProvider
                .getChildItem(groupPosition, childPosition);

        // set listeners
        // (if the item is *not pinned*, click event comes to the itemView)
        holder.itemView.setOnClickListener(mItemViewOnClickListener);
        // (if the item is *pinned*, click event comes to the mContainer)
        holder.mContainer.setOnClickListener(mSwipeableViewContainerOnClickListener);

        // set text
        holder.mTextView.setText(item.getTitle());

        final int dragState = holder.getDragStateFlags();
        final int swipeState = holder.getSwipeStateFlags();

        if (((dragState & RecyclerViewDragDropManager.STATE_FLAG_IS_UPDATED) != 0) ||
                ((swipeState & RecyclerViewSwipeManager.STATE_FLAG_IS_UPDATED) != 0)) {
            int bgResId;

            if ((dragState & RecyclerViewDragDropManager.STATE_FLAG_IS_ACTIVE) != 0) {
                bgResId = R.drawable.bg_item_dragging_active_state;
            } else if ((dragState & RecyclerViewDragDropManager.STATE_FLAG_DRAGGING) != 0) {
                bgResId = R.drawable.bg_item_dragging_state;
            } else if ((swipeState & RecyclerViewSwipeManager.STATE_FLAG_IS_ACTIVE) != 0) {
                bgResId = R.drawable.bg_item_swiping_active_state;
            } else if ((swipeState & RecyclerViewSwipeManager.STATE_FLAG_SWIPING) != 0) {
                bgResId = R.drawable.bg_item_swiping_state;
            } else {
                bgResId = R.drawable.bg_item_normal_state;
            }

            holder.mContainer.setBackgroundResource(bgResId);
        }

        // set swiping properties
        holder.setSwipeItemSlideAmount(
                item.isPinnedToSwipeLeft() ? RecyclerViewSwipeManager.OUTSIDE_OF_THE_WINDOW_LEFT
                        : 0);
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupViewHolder holder, int groupPosition,
            int x, int y, boolean expand) {
        // check the item is *not* pinned
        if (mProvider.getGroupItem(groupPosition).isPinnedToSwipeLeft()) {
            // return false to raise View.OnClickListener#onClick() event
            return false;
        }

        // check is enabled
        if (!(holder.itemView.isEnabled() && holder.itemView.isClickable())) {
            return false;
        }

        final View containerView = holder.mContainer;
        final View dragHandleView = holder.mDragHandle;

        final int offsetX = containerView.getLeft() + (int) (
                ViewCompat.getTranslationX(containerView) + 0.5f);
        final int offsetY = containerView.getTop() + (int) (
                ViewCompat.getTranslationY(containerView) + 0.5f);

        return !ViewUtils.hitTest(dragHandleView, x - offsetX, y - offsetY);
    }

    @Override
    public boolean onCheckGroupCanStartDrag(MyGroupViewHolder holder, int groupPosition, int x,
            int y) {
        // x, y --- relative from the itemView's top-left
        final View containerView = holder.mContainer;
        final View dragHandleView = holder.mDragHandle;

        final int offsetX = containerView.getLeft() + (int) (
                ViewCompat.getTranslationX(containerView) + 0.5f);
        final int offsetY = containerView.getTop() + (int) (
                ViewCompat.getTranslationY(containerView) + 0.5f);

        return ViewUtils.hitTest(dragHandleView, x - offsetX, y - offsetY);
    }

    @Override
    public boolean onCheckChildCanStartDrag(MyChildViewHolder holder, int groupPosition,
            int childPosition, int x, int y) {
        // x, y --- relative from the itemView's top-left
        final View containerView = holder.mContainer;
        final View dragHandleView = holder.mDragHandle;

        final int offsetX = containerView.getLeft() + (int) (
                ViewCompat.getTranslationX(containerView) + 0.5f);
        final int offsetY = containerView.getTop() + (int) (
                ViewCompat.getTranslationY(containerView) + 0.5f);

        return ViewUtils.hitTest(dragHandleView, x - offsetX, y - offsetY);
    }

    @Override
    public ItemDraggableRange onGetGroupItemDraggableRange(MyGroupViewHolder holder,
            int groupPosition) {
        // no drag-sortable range specified
        return null;
    }

    @Override
    public ItemDraggableRange onGetChildItemDraggableRange(MyChildViewHolder holder,
            int groupPosition, int childPosition) {
        // no drag-sortable range specified
        return null;
    }

    @Override
    public void onMoveGroupItem(int fromGroupPosition, int toGroupPosition) {
        mProvider.moveGroupItem(fromGroupPosition, toGroupPosition);
    }

    @Override
    public void onMoveChildItem(int fromGroupPosition, int fromChildPosition, int toGroupPosition,
            int toChildPosition) {
        mProvider.moveChildItem(fromGroupPosition, fromChildPosition, toGroupPosition,
                toChildPosition);
    }

    @Override
    public int onGetGroupItemSwipeReactionType(MyGroupViewHolder holder, int groupPosition, int x,
            int y) {
        if (onCheckGroupCanStartDrag(holder, groupPosition, x, y)) {
            return RecyclerViewSwipeManager.REACTION_CAN_NOT_SWIPE_BOTH;
        }

        return mProvider.getGroupItem(groupPosition).getSwipeReactionType();
    }

    @Override
    public int onGetChildItemSwipeReactionType(MyChildViewHolder holder, int groupPosition,
            int childPosition, int x, int y) {
        if (onCheckChildCanStartDrag(holder, groupPosition, childPosition, x, y)) {
            return RecyclerViewSwipeManager.REACTION_CAN_NOT_SWIPE_BOTH;
        }

        return mProvider.getChildItem(groupPosition, childPosition).getSwipeReactionType();
    }

    @Override
    public void onSetGroupItemSwipeBackground(MyGroupViewHolder holder, int groupPosition,
            int type) {
        int bgResId = 0;
        switch (type) {
            case RecyclerViewSwipeManager.DRAWABLE_SWIPE_NEUTRAL_BACKGROUND:
                bgResId = R.drawable.bg_swipe_item_neutral;
                break;
            case RecyclerViewSwipeManager.DRAWABLE_SWIPE_LEFT_BACKGROUND:
                bgResId = R.drawable.bg_swipe_group_item_left;
                break;
            case RecyclerViewSwipeManager.DRAWABLE_SWIPE_RIGHT_BACKGROUND:
                bgResId = R.drawable.bg_swipe_group_item_right;
                break;
        }

        holder.itemView.setBackgroundResource(bgResId);
    }

    @Override
    public void onSetChildItemSwipeBackground(MyChildViewHolder holder, int groupPosition,
            int childPosition, int type) {
        int bgResId = 0;
        switch (type) {
            case RecyclerViewSwipeManager.DRAWABLE_SWIPE_NEUTRAL_BACKGROUND:
                bgResId = R.drawable.bg_swipe_item_neutral;
                break;
            case RecyclerViewSwipeManager.DRAWABLE_SWIPE_LEFT_BACKGROUND:
                bgResId = R.drawable.bg_swipe_item_left;
                break;
            case RecyclerViewSwipeManager.DRAWABLE_SWIPE_RIGHT_BACKGROUND:
                bgResId = R.drawable.bg_swipe_item_right;
                break;
        }

        holder.itemView.setBackgroundResource(bgResId);
    }

    @Override
    public int onSwipeGroupItem(MyGroupViewHolder holder, int groupPosition, int result) {
        Log.d(TAG,
                "onSwipeGroupItem(groupPosition = " + groupPosition + ", result = " + result + ")");

        switch (result) {
            // swipe right --- remove
            case RecyclerViewSwipeManager.RESULT_SWIPED_RIGHT:
                return RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_REMOVE_ITEM;
            // swipe left -- pin
            case RecyclerViewSwipeManager.RESULT_SWIPED_LEFT:
                return RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_MOVE_TO_SWIPED_DIRECTION;
            // other --- do nothing
            case RecyclerViewSwipeManager.RESULT_CANCELED:
            default:
                return RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_DEFAULT;
        }
    }

    @Override
    public int onSwipeChildItem(MyChildViewHolder holder, int groupPosition, int childPosition,
            int result) {
        Log.d(TAG, "onSwipeChildItem(groupPosition = " + groupPosition + ", childPosition = "
                + childPosition + ", result = " + result + ")");

        switch (result) {
            // swipe right --- remove
            case RecyclerViewSwipeManager.RESULT_SWIPED_RIGHT:
                return RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_REMOVE_ITEM;
            // swipe left -- pin
            case RecyclerViewSwipeManager.RESULT_SWIPED_LEFT:
                return RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_MOVE_TO_SWIPED_DIRECTION;
            // other --- do nothing
            case RecyclerViewSwipeManager.RESULT_CANCELED:
            default:
                return RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_DEFAULT;
        }
    }

    @Override
    public void onPerformAfterSwipeGroupReaction(MyGroupViewHolder holder, int groupPosition,
            int result, int reaction) {
        Log.d(TAG,
                "onPerformAfterSwipeGroupReaction(groupPosition = " + groupPosition + ", result = "
                        + result + ", reaction = " + reaction + ")");

        final AbstractExpandableDataProvider.GroupData item = mProvider.getGroupItem(groupPosition);
        final int flatPosition = holder.getPosition();

        if (reaction == RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_REMOVE_ITEM) {
            mProvider.removeGroupItem(groupPosition);
            notifyItemRemoved(flatPosition);

            if (mEventListener != null) {
                mEventListener.onGroupItemLikedRemoved(groupPosition);
            }
        } else if (reaction
                == RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_MOVE_TO_SWIPED_DIRECTION) {
//            item.setPinnedToSwipeLeft(true); todo:if you want to pin
//            notifyItemChanged(flatPosition);
//            if (mEventListener != null) {
//                mEventListener.onGroupItemPinned(groupPosition);
//            }
            mProvider.removeGroupItem(groupPosition);
            notifyItemRemoved(flatPosition);
            if (mEventListener != null) {
                mEventListener.onGroupItemDisLikedRemoved(groupPosition);
            }

        } else {
            item.setPinnedToSwipeLeft(false);
        }
    }

    @Override
    public void onPerformAfterSwipeChildReaction(MyChildViewHolder holder, int groupPosition,
            int childPosition, int result, int reaction) {
        Log.d(TAG, "onPerformAfterSwipeGroupReaction(groupPosition = " + groupPosition
                + ", childPosition = " + childPosition +
                ", result = " + result + ", reaction = " + reaction + ")");

        final AbstractExpandableDataProvider.ChildData item = mProvider
                .getChildItem(groupPosition, childPosition);
        final int flatPosition = holder.getPosition();

        if (reaction == RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_REMOVE_ITEM) {
            mProvider.removeChildItem(groupPosition, childPosition);
            notifyItemRemoved(flatPosition);

            if (mEventListener != null) {
                mEventListener.onChildItemRemoved(groupPosition, childPosition);
            }
        } else if (reaction
                == RecyclerViewSwipeManager.AFTER_SWIPE_REACTION_MOVE_TO_SWIPED_DIRECTION) {
            item.setPinnedToSwipeLeft(true);
            notifyItemChanged(flatPosition);

            if (mEventListener != null) {
                mEventListener.onChildItemPinned(groupPosition, childPosition);
            }
        } else {
            item.setPinnedToSwipeLeft(false);
        }
    }

    public EventListener getEventListener() {
        return mEventListener;
    }

    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }

    public interface EventListener {

        void onGroupItemLikedRemoved(int groupPosition);

        void onChildItemRemoved(int groupPosition, int childPosition);

        void onGroupItemDisLikedRemoved(int groupPosition);

        void onChildItemPinned(int groupPosition, int childPosition);

        void onItemViewClicked(View v, boolean pinned);
    }

    public static abstract class MyBaseViewHolder extends AbstractDraggableSwipeableItemViewHolder
            implements ExpandableItemViewHolder {

        public ViewGroup mContainer;

        public View mDragHandle;

        public TextView mTextView;

        public ImageView mImageView;

        private int mExpandStateFlags;

        public MyBaseViewHolder(View v) {
            super(v);
            mContainer = (ViewGroup) v.findViewById(R.id.container);
            mDragHandle = v.findViewById(R.id.drag_handle);
            mTextView = (TextView) v.findViewById(android.R.id.text1);
            mImageView = (ImageView) v.findViewById(R.id.image_item);
        }

        @Override
        public int getExpandStateFlags() {
            return mExpandStateFlags;
        }

        @Override
        public void setExpandStateFlags(int flag) {
            mExpandStateFlags = flag;
        }

        @Override
        public View getSwipeableContainerView() {
            return mContainer;
        }
    }

    public static class MyGroupViewHolder extends MyBaseViewHolder {

        public MyGroupViewHolder(View v) {
            super(v);
        }
    }

    public static class MyChildViewHolder extends MyBaseViewHolder {

        public MyChildViewHolder(View v) {
            super(v);
        }
    }
}
