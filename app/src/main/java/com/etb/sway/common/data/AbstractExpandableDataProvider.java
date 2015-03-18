
package com.etb.sway.common.data;

import android.graphics.drawable.Drawable;

public abstract class AbstractExpandableDataProvider {
    public static abstract class BaseData {

        public abstract int getSwipeReactionType();

        public abstract String getText();

        public abstract long getId() ;

        public abstract String getDescription() ;

        public abstract int getSwipeReaction() ;

        public abstract long getNextChildId() ;

        public abstract Drawable getCardImage();

        public abstract double getLatitude() ;

        public abstract double getLongitude();

        public abstract void setPinnedToSwipeLeft(boolean pinned);

        public abstract boolean isPinnedToSwipeLeft();
    }

    public static abstract class GroupData extends BaseData {
        public abstract boolean isSectionHeader();
        public abstract long getGroupId();
    }

    public static abstract class ChildData extends BaseData {
        public abstract long getChildId();
    }

    public abstract int getGroupCount();
    public abstract int getChildCount(int groupPosition);

    public abstract GroupData getGroupItem(int groupPosition);
    public abstract ChildData getChildItem(int groupPosition, int childPosition);

    public abstract void moveGroupItem(int fromGroupPosition, int toGroupPosition);
    public abstract void moveChildItem(int fromGroupPosition, int fromChildPosition, int toGroupPosition, int toChildPosition);

    public abstract void removeGroupItem(int groupPosition);
    public abstract void removeChildItem(int groupPosition, int childPosition);

    public abstract long undoLastRemoval();


}
