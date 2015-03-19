package com.etb.sway.model;

import com.etb.sway.common.data.MapPoiInterface;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ortal on 09-Mar-15.
 */
@SuppressWarnings("serial")//with this annotation we are going to hide compiler warning
public class Likes {
    public ArrayList<MapPoiInterface> likeCardList ;
    public ArrayList<MapPoiInterface> disLikeCardList ;

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

    public  Likes(){
        likeCardList = new ArrayList<MapPoiInterface>();
        disLikeCardList = new ArrayList<MapPoiInterface>();
    }

    public void addLikeItem(MapPoiInterface cardModel ){

        likeCardList.add(cardModel);

    }
    public void addDisLikeItem(MapPoiInterface cardModel ){

        disLikeCardList.add(cardModel);

    }
}
