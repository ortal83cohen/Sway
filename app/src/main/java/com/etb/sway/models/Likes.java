package com.etb.sway.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ortal on 09-Mar-15.
 */
@SuppressWarnings("serial")//with this annotation we are going to hide compiler warning
public class Likes implements Serializable {
    public ArrayList<CardModel> likeCardList ;
    public ArrayList<CardModel> disLikeCardList ;

    public ArrayList<CardModel> getLikeCardList() {
        return likeCardList;
    }

    public void setLikeCardList(ArrayList<CardModel> likeCardList) {
        this.likeCardList = likeCardList;
    }

    public ArrayList<CardModel> getDisLikeCardList() {
        return disLikeCardList;
    }

    public void setDisLikeCardList(ArrayList<CardModel> disLikeCardList) {
        this.disLikeCardList = disLikeCardList;
    }

    public  Likes(){
        likeCardList = new ArrayList<CardModel>();
        disLikeCardList = new ArrayList<CardModel>();
    }

    public void addLikeItem(CardModel cardModel ){

        likeCardList.add(cardModel);

    }
    public void addDisLikeItem(CardModel cardModel ){

        disLikeCardList.add(cardModel);

    }
}
