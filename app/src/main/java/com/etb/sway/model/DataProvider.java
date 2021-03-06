package com.etb.sway.model;


import com.etb.sway.R;
import com.etb.sway.helper.ImageActions;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataProvider extends AbstractExpandableDataProvider {

    private List<GroupData> mUnSelectedData;

    private List<GroupData> mLikeData = new ArrayList<GroupData>();

    private List<GroupData> mDisLikeData = new ArrayList<GroupData>();

    private Context context;

    // for undo group item
    private GroupData mLastRemovedGroup;

    private int mLastRemovedGroupPosition = -1;

    // for undo child item
    private long mLastRemovedChildParentGroupId = -1;

    private int mLastRemovedChildPosition = -1;

    public DataProvider(Context context) {
        Resources r = context.getResources();
        final ImageActions imageActions = new ImageActions(r);
        mUnSelectedData = new LinkedList<>();

        mUnSelectedData.add(new Poi(1, "Verzetsmuseum",
                "The Dutch Resistance Museum is located in the Plantage neighbourhood in Amsterdam. The Dutch Resistance Museum, chosen as the best historical museum of the Netherlands, tells the story of the Dutch people in World War II.",
                imageActions.resize(r.getDrawable(R.drawable.verzetsmuseum)), 52.3677799,
                4.9128112));
        mUnSelectedData.add(new Poi(2, "Van Gogh Museum",
                "The Van Gogh Museum is an art museum in Amsterdam in the Netherlands dedicated to the works of Vincent van Gogh and his contemporaries.",
                imageActions.resize(r.getDrawable(R.drawable.van_gogh_museum)), 52.358626,
                4.881065));
        mUnSelectedData.add(new Poi(3, "Anne Frank House",
                "The Anne Frank House is a historic house and biographical museum dedicated to Jewish wartime diarist Anne Frank. The building is located at the Prinsengracht, close to the Westerkerk, in central Amsterdam in the Netherlands.",
                imageActions.resize(r.getDrawable(R.drawable.anne_frank_house)), 52.375414,
                4.883944));
        mUnSelectedData.add(new Poi(4, "Vondelpark",
                "The Vondelpark is a public urban park of 47 hectares in Amsterdam, Netherlands. It is part of the borough of Amsterdam-Zuid and situated west from the Leidseplein and the Museumplein.",
                imageActions.resize(r.getDrawable(R.drawable.vondelpark)), 52.358178, 4.868637));
        mUnSelectedData.add(new Poi(5, "National Monument",
                "The National Monument is a 1956 World War II monument on Dam Square in Amsterdam. A national Remembrance of the Dead ceremony is held at the monument every year on 4 May to commemorate the casualties of World War II and subsequent armed conflicts.",
                imageActions.resize(r.getDrawable(R.drawable.national_monument)), 52.373003,
                4.893680));
        mUnSelectedData.add(new Poi(6, "Albert Cuyp Market",
                "The Albert Cuyp Market is a street market in Amsterdam, The Netherlands, on the Albert Cuypstraat between Ferdinand Bolstraat and Van Woustraat, in the De Pijp area of the Oud-Zuid district of the city.",
                imageActions.resize(r.getDrawable(R.drawable.albert_cuyp_market)), 52.356164,
                4.895387));

    }

    @Override
    public GroupData getLastGroupRemoved() {
        return mLastRemovedGroup;
    }

    @Override
    public int getGroupCount() {
        return mUnSelectedData.size();
    }


    @Override
    public GroupData getGroupItem(int groupPosition) {
        if (groupPosition < 0 || groupPosition >= getGroupCount()) {
            throw new IndexOutOfBoundsException("groupPosition = " + groupPosition);
        }

        return mUnSelectedData.get(groupPosition);
    }


    @Override
    public void moveGroupItem(int fromGroupPosition, int toGroupPosition) {
        if (fromGroupPosition == toGroupPosition) {
            return;
        }

        final GroupData item = mUnSelectedData.remove(fromGroupPosition);
        mUnSelectedData.add(toGroupPosition, item);
    }

    @Override
    public void removeGroupItem(int groupPosition) {
        mLastRemovedGroup = mUnSelectedData.remove(groupPosition);
        mLastRemovedGroupPosition = groupPosition;

        mLastRemovedChildParentGroupId = -1;
        mLastRemovedChildPosition = -1;
    }

    @Override
    public long undoLastRemoval() {
        if (mLastRemovedGroup != null) {
            return undoGroupRemoval();
        } else {
            return RecyclerViewExpandableItemManager.NO_EXPANDABLE_POSITION;
        }
    }

    private long undoGroupRemoval() {
        int insertedPosition;
        if (mLastRemovedGroupPosition >= 0 && mLastRemovedGroupPosition < mUnSelectedData.size()) {
            insertedPosition = mLastRemovedGroupPosition;
        } else {
            insertedPosition = mUnSelectedData.size();
        }

        mUnSelectedData.add(insertedPosition, mLastRemovedGroup);

        mLastRemovedGroup = null;
        mLastRemovedGroupPosition = -1;

        return RecyclerViewExpandableItemManager.getPackedPositionForGroup(insertedPosition);
    }

    public List<GroupData> getLikeData() {
        return mLikeData;
    }

    public void setLikeData(ArrayList<GroupData> mLikeData) {
        this.mLikeData = mLikeData;
    }

    public List<GroupData> getDisLikeData() {
        return mDisLikeData;
    }

    public void setDisLikeData(ArrayList<GroupData> dismLikeData) {
        this.mDisLikeData = dismLikeData;
    }

    public void addLikeItem(GroupData mapPoi) {

        mLikeData.add(mapPoi);

    }

    public void addDisLikeItem(GroupData mapPoi) {

        mDisLikeData.add(mapPoi);

    }

    public void onUndo(GroupData mapPoi) {
        try {
            mDisLikeData.remove(mapPoi);
        } catch (Exception e) {
        }
        try {
            mLikeData.remove(mapPoi);
        } catch (Exception e) {
        }
    }
}
