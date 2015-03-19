package com.etb.sway.common.data;


import com.etb.sway.R;
import com.etb.sway.helper.ImageActions;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExpandableDataProvider extends AbstractExpandableDataProvider {
    private List<Pair<GroupData, List<ChildData>>> mData;

    // for undo group item
    private Pair<GroupData, List<ChildData>> mLastRemovedGroup;
    private int mLastRemovedGroupPosition = -1;

    // for undo child item
    private ChildData mLastRemovedChild;
    private long mLastRemovedChildParentGroupId = -1;
    private int mLastRemovedChildPosition = -1;

    public ExpandableDataProvider(Context context) {
        Resources r = context.getResources();
       final ImageActions imageActions = new ImageActions(r);
        mData = new LinkedList<>();


//                adapter.add(new com.etb.sway.model.CardModel(1,"Verzetsmuseum", "The Dutch Resistance Museum is located in the Plantage neighbourhood in Amsterdam. The Dutch Resistance Museum, chosen as the best historical museum of the Netherlands, tells the story of the Dutch people in World War II.", imageActions.resize( r.getDrawable(
//                R.drawable.verzetsmuseum)),52.3677799,4.9128112));
//        adapter.add(new com.etb.sway.model.CardModel(2,"Van Gogh Museum", "The Van Gogh Museum is an art museum in Amsterdam in the Netherlands dedicated to the works of Vincent van Gogh and his contemporaries.", imageActions.resize(r.getDrawable(R.drawable.van_gogh_museum)),52.358626, 4.881065));
//        adapter.add(new com.etb.sway.model.CardModel(3,"Anne Frank House", "The Anne Frank House is a historic house and biographical museum dedicated to Jewish wartime diarist Anne Frank. The building is located at the Prinsengracht, close to the Westerkerk, in central Amsterdam in the Netherlands.",imageActions.resize( r.getDrawable(R.drawable.anne_frank_house)),52.375414, 4.883944));
//        adapter.add(new com.etb.sway.model.CardModel(4,"Vondelpark", "The Vondelpark is a public urban park of 47 hectares in Amsterdam, Netherlands. It is part of the borough of Amsterdam-Zuid and situated west from the Leidseplein and the Museumplein.", imageActions.resize(r.getDrawable(R.drawable.vondelpark)),52.358178, 4.868637));
//        adapter.add(new com.etb.sway.model.CardModel(5,"National Monument", "The National Monument is a 1956 World War II monument on Dam Square in Amsterdam. A national Remembrance of the Dead ceremony is held at the monument every year on 4 May to commemorate the casualties of World War II and subsequent armed conflicts.", imageActions.resize(r.getDrawable(R.drawable.national_monument)),52.373003, 4.893680));
//        adapter.add(new com.etb.sway.model.CardModel(6,"Albert Cuyp Market", "The Albert Cuyp Market is a street market in Amsterdam, The Netherlands, on the Albert Cuypstraat between Ferdinand Bolstraat and Van Woustraat, in the De Pijp area of the Oud-Zuid district of the city.",imageActions.resize( r.getDrawable(R.drawable.albert_cuyp_market)),52.356164, 4.895387));

         ConcreteGroupData group = new ConcreteGroupData(1, "Verzetsmuseum","The Dutch Resistance Museum is located in the Plantage neighbourhood in Amsterdam. The Dutch Resistance Museum, chosen as the best historical museum of the Netherlands, tells the story of the Dutch people in World War II.",imageActions.resize(r.getDrawable( R.drawable.verzetsmuseum),300,300),52.3677799,4.9128112, RecyclerViewSwipeManager.REACTION_CAN_SWIPE_LEFT | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_RIGHT);
         List<ChildData> children = new ArrayList<>();
         String childText = group.getDescription();
                 int childSwipeReaction =  RecyclerViewSwipeManager.REACTION_CAN_NOT_SWIPE_BOTH;
                children.add(new ConcreteChildData(0, childText, childSwipeReaction));
        mData.add( new Pair<GroupData, List<ChildData>>(group, children));

        group = new ConcreteGroupData(2,"Van Gogh Museum", "The Van Gogh Museum is an art museum in Amsterdam in the Netherlands dedicated to the works of Vincent van Gogh and his contemporaries.", imageActions.resize(r.getDrawable(R.drawable.van_gogh_museum),300,300),52.358626, 4.881065        , RecyclerViewSwipeManager.REACTION_CAN_SWIPE_LEFT | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_RIGHT);
        children = new ArrayList<>();
        childText = group.getDescription();
        childSwipeReaction =  RecyclerViewSwipeManager.REACTION_CAN_NOT_SWIPE_BOTH;
        children.add(new ConcreteChildData(0, childText, childSwipeReaction));
        mData.add( new Pair<GroupData, List<ChildData>>(group, children));

        group = new ConcreteGroupData(3,"Anne Frank House", "The Anne Frank House is a historic house and biographical museum dedicated to Jewish wartime diarist Anne Frank. The building is located at the Prinsengracht, close to the Westerkerk, in central Amsterdam in the Netherlands.",imageActions.resize( r.getDrawable(R.drawable.anne_frank_house),300,300),52.375414, 4.883944        , RecyclerViewSwipeManager.REACTION_CAN_SWIPE_LEFT | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_RIGHT);
        children = new ArrayList<>();
        childText = group.getDescription();
        childSwipeReaction =  RecyclerViewSwipeManager.REACTION_CAN_NOT_SWIPE_BOTH;
        children.add(new ConcreteChildData(0, childText, childSwipeReaction));
        mData.add( new Pair<GroupData, List<ChildData>>(group, children));

         group = new ConcreteGroupData(4,"Vondelpark", "The Vondelpark is a public urban park of 47 hectares in Amsterdam, Netherlands. It is part of the borough of Amsterdam-Zuid and situated west from the Leidseplein and the Museumplein.", imageActions.resize(r.getDrawable(R.drawable.vondelpark),300,300),52.358178, 4.868637        , RecyclerViewSwipeManager.REACTION_CAN_SWIPE_LEFT | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_RIGHT);
         children = new ArrayList<>();
         childText = group.getDescription();
         childSwipeReaction =  RecyclerViewSwipeManager.REACTION_CAN_NOT_SWIPE_BOTH;
        children.add(new ConcreteChildData(0, childText, childSwipeReaction));
        mData.add( new Pair<GroupData, List<ChildData>>(group, children));

         group = new ConcreteGroupData(5,"National Monument", "The National Monument is a 1956 World War II monument on Dam Square in Amsterdam. A national Remembrance of the Dead ceremony is held at the monument every year on 4 May to commemorate the casualties of World War II and subsequent armed conflicts.", imageActions.resize(r.getDrawable(R.drawable.national_monument),300,300),52.373003, 4.893680        , RecyclerViewSwipeManager.REACTION_CAN_SWIPE_LEFT | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_RIGHT);
         children = new ArrayList<>();
         childText = group.getDescription();
         childSwipeReaction =  RecyclerViewSwipeManager.REACTION_CAN_NOT_SWIPE_BOTH;
        children.add(new ConcreteChildData(0, childText, childSwipeReaction));
        mData.add( new Pair<GroupData, List<ChildData>>(group, children));

         group = new ConcreteGroupData(6,"Albert Cuyp Market", "The Albert Cuyp Market is a street market in Amsterdam, The Netherlands, on the Albert Cuypstraat between Ferdinand Bolstraat and Van Woustraat, in the De Pijp area of the Oud-Zuid district of the city.",imageActions.resize( r.getDrawable(R.drawable.albert_cuyp_market),300,300),52.356164, 4.895387        , RecyclerViewSwipeManager.REACTION_CAN_SWIPE_LEFT | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_RIGHT);
         children = new ArrayList<>();
         childText = group.getDescription();
         childSwipeReaction =  RecyclerViewSwipeManager.REACTION_CAN_NOT_SWIPE_BOTH;
        children.add(new ConcreteChildData(0, childText, childSwipeReaction));
        mData.add( new Pair<GroupData, List<ChildData>>(group, children));

    }

    @Override
    public GroupData getLastGroupRemoved() {
        return mLastRemovedGroup.first;
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

        return RecyclerViewExpandableItemManager.getPackedPositionForChild(groupPosition,
                insertedPosition);
    }

    public static final class ConcreteGroupData extends GroupData {

        private final long mId;
        private final String mText;
        private final String mDescription;
        private final int mSwipeReaction;
        private boolean mPinnedToSwipeLeft;
        private long mNextChildId;
        private Drawable mCardImage;
        private double mLatitude;
        private double mLongitude;

        ConcreteGroupData(long id, String text,String description , Drawable cardImage,double latitude,double longitude,int swipeReaction) {
            mId = id;
            mText = text;
            mSwipeReaction = swipeReaction;
            mNextChildId = 0;
            mDescription = description;
            mCardImage = cardImage;
            mLatitude = latitude;
            mLongitude = longitude;
        }

        @Override
        public long getGroupId() {
            return mId;
        }

        @Override
        public boolean isSectionHeader() {
            return false;
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
            return mId;
        }

        @Override
        public String getDescription() {
            return mDescription;
        }

        @Override
        public String getTitle() {
            return mText;
        }

        @Override
        public int getSwipeReaction() {
            return mSwipeReaction;
        }

        @Override
        public long getNextChildId() {
            return 0;
        }

        @Override
        public Drawable getCardImage() {
            return mCardImage;
        }

        @Override
        public double getLatitude() {
            return mLatitude;
        }

        @Override
        public double getLongitude() {
            return mLongitude;
        }

        @Override
        public boolean isPinnedToSwipeLeft() {
            return mPinnedToSwipeLeft;
        }

        @Override
        public void setPinnedToSwipeLeft(boolean pinnedToSwipeLeft) {
            mPinnedToSwipeLeft = pinnedToSwipeLeft;
        }

        public long generateNewChildId() {
            final long id = mNextChildId;
            mNextChildId += 1;
            return id;
        }
    }

    public static final class ConcreteChildData extends ChildData {

        private final String mText;

        private final int mSwipeReaction;

        private long mId;

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

        public void setChildId(long id) {
            this.mId = id;
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
        public boolean isPinnedToSwipeLeft() {
            return mPinnedToSwipeLeft;
        }

        @Override
        public void setPinnedToSwipeLeft(boolean pinnedToSwipeLeft) {
            mPinnedToSwipeLeft = pinnedToSwipeLeft;
        }
    }
}
