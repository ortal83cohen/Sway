
package com.etb.sway.common.data;


import android.graphics.drawable.Drawable;
import android.support.v4.util.Pair;

import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SectionExpandableDataProvider extends AbstractExpandableDataProvider {
    private List<Pair<GroupData, List<ChildData>>> mData;

    // for undo group item
    private Pair<GroupData, List<ChildData>> mLastRemovedGroup;
    private int mLastRemovedGroupPosition = -1;

    // for undo child item
    private ChildData mLastRemovedChild;
    private long mLastRemovedChildParentGroupId = -1;
    private int mLastRemovedChildPosition = -1;

    public SectionExpandableDataProvider() {
        final String groupItems = "|ABC|DEF|GHI|JKL|MNO|PQR|STU|VWX|YZ";
        final String childItems = "abc";

        mData = new LinkedList<>();

        int sectionCount = 1;

        for (int i = 0; i < groupItems.length(); i++) {
            //noinspection UnnecessaryLocalVariable
            final long groupId = i;
            final boolean isSection = (groupItems.charAt(i) == '|');
            final String groupText = isSection ? ("Section " + sectionCount) : Character.toString(groupItems.charAt(i));
            final int groupSwipeReaction = isSection ? RecyclerViewSwipeManager.REACTION_CAN_NOT_SWIPE_BOTH : RecyclerViewSwipeManager.REACTION_CAN_SWIPE_BOTH;
            final ConcreteGroupData group = new ConcreteGroupData(groupId, isSection, groupText, groupSwipeReaction);
            final List<ChildData> children = new ArrayList<>();

            if (isSection) {
                sectionCount += 1;
            } else {
                // add child items
                for (int j = 0; j < childItems.length(); j++) {
                    final long childId = group.generateNewChildId();
                    final String childText = Character.toString(childItems.charAt(j));
                    final int childSwipeReaction = RecyclerViewSwipeManager.REACTION_CAN_SWIPE_BOTH;

                    children.add(new ConcreteChildData(childId, childText, childSwipeReaction));
                }
            }

            mData.add(new Pair<GroupData, List<ChildData>>(group, children));
        }
    }

    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return mData.get(groupPosition).second.size();
    }

    @Override
    public GroupData getGroupItem(int groupPosition) {
        if (groupPosition < 0 || groupPosition >= getGroupCount()) {
            throw new IndexOutOfBoundsException("groupPosition = " + groupPosition);
        }

        return mData.get(groupPosition).first;
    }

    @Override
    public ChildData getChildItem(int groupPosition, int childPosition) {
        if (groupPosition < 0 || groupPosition >= getGroupCount()) {
            throw new IndexOutOfBoundsException("groupPosition = " + groupPosition);
        }

        final List<ChildData> children = mData.get(groupPosition).second;

        if (childPosition < 0 || childPosition >= children.size()) {
            throw new IndexOutOfBoundsException("childPosition = " + childPosition);
        }

        return children.get(childPosition);
    }

    @Override
    public void moveGroupItem(int fromGroupPosition, int toGroupPosition) {
        if (fromGroupPosition == toGroupPosition) {
            return;
        }

        final Pair<GroupData, List<ChildData>> item = mData.remove(fromGroupPosition);
        mData.add(toGroupPosition, item);
    }

    @Override
    public void moveChildItem(int fromGroupPosition, int fromChildPosition, int toGroupPosition, int toChildPosition) {
        if ((fromGroupPosition == toGroupPosition) && (fromChildPosition == toChildPosition)) {
            return;
        }

        final Pair<GroupData, List<ChildData>> fromGroup = mData.get(fromGroupPosition);
        final Pair<GroupData, List<ChildData>> toGroup = mData.get(toGroupPosition);

        if (fromGroup.first.isSectionHeader()) {
            throw new IllegalStateException("Source group is a section header!");
        }
        if (toGroup.first.isSectionHeader()) {
            throw new IllegalStateException("Destination group is a section header!");
        }

        final ConcreteChildData item = (ConcreteChildData) fromGroup.second.remove(fromChildPosition);

        if (toGroupPosition != fromGroupPosition) {
            // assign a new ID
            final long newId = ((ConcreteGroupData) toGroup.first).generateNewChildId();
            item.setChildId(newId);
        }

        toGroup.second.add(toChildPosition, item);
    }

    @Override
    public void removeGroupItem(int groupPosition) {
        mLastRemovedGroup = mData.remove(groupPosition);
        mLastRemovedGroupPosition = groupPosition;

        mLastRemovedChild = null;
        mLastRemovedChildParentGroupId = -1;
        mLastRemovedChildPosition = -1;
    }

    @Override
    public void removeChildItem(int groupPosition, int childPosition) {
        mLastRemovedChild = mData.get(groupPosition).second.remove(childPosition);
        mLastRemovedChildParentGroupId = mData.get(groupPosition).first.getGroupId();
        mLastRemovedChildPosition = childPosition;

        mLastRemovedGroup = null;
        mLastRemovedGroupPosition = -1;
    }


    @Override
    public long undoLastRemoval() {
        if (mLastRemovedGroup != null) {
            return undoGroupRemoval();
        } else if (mLastRemovedChild != null) {
            return undoChildRemoval();
        } else {
            return RecyclerViewExpandableItemManager.NO_EXPANDABLE_POSITION;
        }
    }

    private long undoGroupRemoval() {
        int insertedPosition;
        if (mLastRemovedGroupPosition >= 0 && mLastRemovedGroupPosition < mData.size()) {
            insertedPosition = mLastRemovedGroupPosition;
        } else {
            insertedPosition = mData.size();
        }

        mData.add(insertedPosition, mLastRemovedGroup);

        mLastRemovedGroup = null;
        mLastRemovedGroupPosition = -1;

        return RecyclerViewExpandableItemManager.getPackedPositionForGroup(insertedPosition);
    }

    private long undoChildRemoval() {
        Pair<GroupData, List<ChildData>> group = null;
        int groupPosition = -1;

        // find the group
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).first.getGroupId() == mLastRemovedChildParentGroupId) {
                group = mData.get(i);
                groupPosition = i;
                break;
            }
        }

        if (group == null) {
            return RecyclerViewExpandableItemManager.NO_EXPANDABLE_POSITION;
        }

        int insertedPosition;
        if (mLastRemovedChildPosition >= 0 && mLastRemovedChildPosition < group.second.size()) {
            insertedPosition = mLastRemovedChildPosition;
        } else {
            insertedPosition = group.second.size();
        }

        group.second.add(insertedPosition, mLastRemovedChild);

        mLastRemovedChildParentGroupId = -1;
        mLastRemovedChildPosition = -1;
        mLastRemovedChild = null;

        return RecyclerViewExpandableItemManager.getPackedPositionForChild(groupPosition, insertedPosition);
    }

    public static final class ConcreteGroupData extends GroupData {

        private final long mId;
        private final boolean mIsSectionHeader;
        private final String mText;
        private final int mSwipeReaction;
        private boolean mPinnedToSwipeLeft;
        private long mNextChildId;

        ConcreteGroupData(long id, boolean isSectionHeader, String text, int swipeReaction) {
            mId = id;
            mIsSectionHeader = isSectionHeader;
            mText = text;
            mSwipeReaction = swipeReaction;
            mNextChildId = 0;
        }

        @Override
        public long getGroupId() {
            return mId;
        }

        @Override
        public boolean isSectionHeader() {
            return mIsSectionHeader;
        }

        @Override
        public int getSwipeReactionType() {
            return mSwipeReaction;
        }

        @Override
        public String getText() {
            return mText;
        }

        @Override
        public long getId() {
            return 0;
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public int getSwipeReaction() {
            return 0;
        }

        @Override
        public long getNextChildId() {
            return 0;
        }

        @Override
        public Drawable getCardImage() {
            return null;
        }

        @Override
        public double getLatitude() {
            return 0;
        }

        @Override
        public double getLongitude() {
            return 0;
        }

        @Override
        public void setPinnedToSwipeLeft(boolean pinnedToSwipeLeft) {
            mPinnedToSwipeLeft = pinnedToSwipeLeft;
        }

        @Override
        public boolean isPinnedToSwipeLeft() {
            return mPinnedToSwipeLeft;
        }

        public long generateNewChildId() {
            final long id = mNextChildId;
            mNextChildId += 1;
            return id;
        }
    }

    public static final class ConcreteChildData extends ChildData {

        private long mId;
        private final String mText;
        private final int mSwipeReaction;
        private boolean mPinnedToSwipeLeft;

        ConcreteChildData(long id, String text, int swipeReaction) {
            mId = id;
            mText = text;
            mSwipeReaction = swipeReaction;
        }

        @Override
        public long getChildId() {
            return mId;
        }

        @Override
        public int getSwipeReactionType() {
            return mSwipeReaction;
        }

        @Override
        public String getText() {
            return mText;
        }

        @Override
        public long getId() {
            return 0;
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public int getSwipeReaction() {
            return 0;
        }

        @Override
        public long getNextChildId() {
            return 0;
        }

        @Override
        public Drawable getCardImage() {
            return null;
        }

        @Override
        public double getLatitude() {
            return 0;
        }

        @Override
        public double getLongitude() {
            return 0;
        }

        @Override
        public void setPinnedToSwipeLeft(boolean pinnedToSwipeLeft) {
            mPinnedToSwipeLeft = pinnedToSwipeLeft;
        }

        @Override
        public boolean isPinnedToSwipeLeft() {
            return mPinnedToSwipeLeft;
        }

        public void setChildId(long id) {
            this.mId = id;
        }
    }
}
