
package com.etb.sway.model;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractExpandableDataProvider {
    public abstract GroupData getLastGroupRemoved();

    public abstract int getGroupCount();


    public abstract GroupData getGroupItem(int groupPosition);


    public abstract void moveGroupItem(int fromGroupPosition, int toGroupPosition);


    public abstract void removeGroupItem(int groupPosition);

    public abstract long undoLastRemoval();

    public abstract List<GroupData> getLikeData();

    public abstract void setLikeData(ArrayList<GroupData> mLikeData);

    public abstract List<GroupData> getDisLikeData() ;

    public abstract void setDisLikeData(ArrayList<GroupData> dismLikeData) ;

    public abstract void addLikeItem(GroupData mapPoi) ;

    public abstract void addDisLikeItem(GroupData mapPoi) ;

    public abstract void onUndo(GroupData mapPoi);

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
