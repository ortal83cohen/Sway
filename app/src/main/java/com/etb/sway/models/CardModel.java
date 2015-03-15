package com.etb.sway.models;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by ortal on 09-Mar-15.
 */

public class CardModel implements Serializable {

    private int id;

    private String title;

    private String description;

    private double latitude;

    private double longitude;

    private transient  Drawable cardImageDrawable;

    private transient  Drawable cardLikeImageDrawable;

    private transient Drawable cardDislikeImageDrawable;

    private boolean likedFlag;

    private boolean dislikedFlag;

    private transient OnCardDimissedListener mOnCardDimissedListener = null;

    private transient OnClickListener mOnClickListener = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public interface OnCardDimissedListener {

        void onLike();

        void onDislike();
    }

    public interface OnClickListener {

        void OnClickListener();
    }

    public CardModel() {
        this(0,null, null, (Drawable) null,0,0);
    }

    public CardModel(int id,String title, String description, Drawable cardImage,double latitude,double longitude) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.cardImageDrawable = cardImage;
        this.longitude=longitude;
        this.latitude=latitude;
    }

    public CardModel(int id,String title, String description, Bitmap cardImage,double latitude,double longitude) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.cardImageDrawable = new BitmapDrawable(null, cardImage);
        this.longitude=longitude;
        this.latitude=latitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drawable getCardImageDrawable() {
        return cardImageDrawable;
    }

    public void setCardImageDrawable(Drawable cardImageDrawable) {
        this.cardImageDrawable = cardImageDrawable;
    }

    public Drawable getCardLikeImageDrawable() {
        return cardLikeImageDrawable;
    }

    public void setCardLikeImageDrawable(Drawable cardLikeImageDrawable) {
        this.cardLikeImageDrawable = cardLikeImageDrawable;
    }

    public Drawable getCardDislikeImageDrawable() {
        return cardDislikeImageDrawable;
    }

    public void setCardDislikeImageDrawable(Drawable cardDislikeImageDrawable) {
        this.cardDislikeImageDrawable = cardDislikeImageDrawable;
    }

    public void setOnCardDimissedListener() {
        this.mOnCardDimissedListener = new com.etb.sway.models.CardModel.OnCardDimissedListener() {
            public void onLike() {
                setLike();
//                Toast.makeText(MainActivity.ma.getBaseContext(), "I like the card " + getTitle(),
//                        Toast.LENGTH_SHORT)
//                        .show();
                  Log.i("Swipeable Cards","I like the card");
            }

            public void onDislike() {
                setDislike();
//                Toast.makeText(getApplicationContext(), "I dislike the card "+card.getTitle(), Toast.LENGTH_SHORT)
//                        .show();
                Log.i("Swipeable Cards","I dislike the card");
            }
        };
    }

    public OnCardDimissedListener getOnCardDimissedListener() {
        return this.mOnCardDimissedListener;
    }


    public void setOnClickListener(OnClickListener listener) {
        this.mOnClickListener =  new com.etb.sway.models.CardModel.OnClickListener() {
            @Override
            public void OnClickListener() {
                Log.i("Swipeable Cards", "I am pressing the card");
            }
        };
    }

    public OnClickListener getOnClickListener() {
        return this.mOnClickListener;
    }


    public void setLike(){
        this.likedFlag = true;
    }

    public void setDislike(){
        this.dislikedFlag= true;
    }
}