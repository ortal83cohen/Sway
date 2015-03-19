package com.etb.sway.model;

import com.etb.sway.common.data.MapPoiInterface;

import java.util.ArrayList;

/**
 * Created by ortal on 09-Mar-15.
 */
@SuppressWarnings("serial")//with this annotation we are going to hide compiler warning
public class PoiData {

    public ArrayList<MapPoiInterface> likeCardList;

    public ArrayList<MapPoiInterface> disLikeCardList;

    public ArrayList<MapPoiInterface> unSelectedList;

    public PoiData() {
        likeCardList = new ArrayList<MapPoiInterface>();
        disLikeCardList = new ArrayList<MapPoiInterface>();
    }

    public ArrayList<MapPoiInterface> getLikeCardList() {
        return likeCardList;
    }

    public void setLikeCardList(ArrayList<MapPoiInterface> likeCardList) {
        this.likeCardList = likeCardList;
    }

    public ArrayList<MapPoiInterface> getDisLikeCardList() {
        return disLikeCardList;
    }

    public void setDisLikeCardList(ArrayList<MapPoiInterface> disLikeCardList) {
        this.disLikeCardList = disLikeCardList;
    }

    public void addLikeItem(MapPoiInterface mapPoi) {

        likeCardList.add(mapPoi);

    }

    public void addDisLikeItem(MapPoiInterface mapPoi) {

        disLikeCardList.add(mapPoi);

    }

    public void onUndo(MapPoiInterface mapPoi) {
        try {
            disLikeCardList.remove(mapPoi);
        } catch (Exception e) {
        }
        try {
            likeCardList.remove(mapPoi);
        } catch (Exception e) {
        }
    }
}
