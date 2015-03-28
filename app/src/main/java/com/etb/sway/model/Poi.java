package com.etb.sway.model;


import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by ortal on 09-Mar-15.
 */

public final class Poi extends AbstractExpandableDataProvider.GroupData {


    private long mId;

    private String mTitle;

    private String mDescription;

    private double mLatitude;

    private double mLongitude;

    private transient Drawable mCardImageDrawable;

    private transient Drawable mCardLikeImageDrawable;

    private transient Drawable mCardDislikeImageDrawable;

    private boolean mIsSectionHeader = false;

    private int mSwipeReaction;

    private boolean mPinnedToSwipeLeft;

    private long mNextChildId;


    public Poi() {
        this(0, null, null, (Drawable) null, 0, 0);
    }

    public Poi(int id, String title, String description, Drawable cardImage, double latitude,
            double longitude) {
        mSwipeReaction = RecyclerViewSwipeManager.REACTION_CAN_SWIPE_LEFT
                | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_RIGHT;
        mNextChildId = 0;
        this.mId = id;
        this.mTitle = title;
        this.mDescription = description;
        this.mCardImageDrawable = cardImage;
        this.mLongitude = longitude;
        this.mLatitude = latitude;
    }

    public Poi(int id, String title, String description, Bitmap cardImage, double latitude,
            double longitude) {
        this.mId = id;
        this.mTitle = title;
        this.mDescription = description;
        this.mCardImageDrawable = new BitmapDrawable(null, cardImage);
        this.mLongitude = longitude;
        this.mLatitude = latitude;
    }

    public Poi(long id, boolean isSectionHeader, String text, int swipeReaction) {
        mId = id;
        mIsSectionHeader = isSectionHeader;
        mTitle = text;
        mSwipeReaction = swipeReaction;
        mNextChildId = 0;
    }

    public Drawable getCardImageDrawable() {
        return mCardImageDrawable;
    }

    public void setCardImageDrawable(Drawable cardImageDrawable) {
        this.mCardImageDrawable = cardImageDrawable;
    }

    public Drawable getCardLikeImageDrawable() {
        return mCardLikeImageDrawable;
    }

    public void setCardLikeImageDrawable(Drawable cardLikeImageDrawable) {
        this.mCardLikeImageDrawable = cardLikeImageDrawable;
    }

    public Drawable getCardDislikeImageDrawable() {
        return mCardDislikeImageDrawable;
    }

    public void setCardDislikeImageDrawable(Drawable cardDislikeImageDrawable) {
        this.mCardDislikeImageDrawable = cardDislikeImageDrawable;
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
    public String getTitle() {
        return mTitle;
    }

    @Override
    public long getId() {
        return mId;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    @Override
    public int getSwipeReaction() {
        return mSwipeReaction;
    }

    @Override
    public Drawable getCardImage() {
        return mCardImageDrawable;
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

}

