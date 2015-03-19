
package com.etb.sway.model;

import android.graphics.drawable.Drawable;

public abstract class AbstractExpandableDataProvider {
    public abstract GroupData getLastGroupRemoved();

    public abstract int getGroupCount();


    public abstract GroupData getGroupItem(int groupPosition);


    public abstract void moveGroupItem(int fromGroupPosition, int toGroupPosition);


    public abstract void removeGroupItem(int groupPosition);

    public abstract long undoLastRemoval();

    public static abstract class BaseData {

        public abstract int getSwipeReactionType();

        public abstract String getTitle();

        public abstract long getId() ;

        public abstract String getDescription() ;

        public abstract int getSwipeReaction() ;

        public abstract Drawable getCardImage();

        public abstract double getLatitude() ;

        public abstract double getLongitude();

        public abstract boolean isPinnedToSwipeLeft();

        public abstract void setPinnedToSwipeLeft(boolean pinned);
    }

    public static abstract class GroupData extends BaseData implements MapPoiInterface {
        public abstract boolean isSectionHeader();
        public abstract long getGroupId();
    }


}
