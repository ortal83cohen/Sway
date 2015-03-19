package com.etb.sway.model;


import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by ortal on 09-Mar-15.
 */

public final class Poi extends AbstractExpandableDataProvider.GroupData  {


    private long mId;

    private String mTitle;

    private String mDescription;

    private double mLatitude;

    private double mLongitude;

    private transient Drawable mCardImageDrawable;

    private transient Drawable mCardLikeImageDrawable;

    private transient Drawable mCardDislikeImageDrawable;

    private transient OnCardDimissedListener mOnCardDimissedListener = null;

    private transient OnClickListener mOnClickListener = null;

    private  boolean mIsSectionHeader = false;

    private  int mSwipeReaction;
    private boolean mPinnedToSwipeLeft;
    private long mNextChildId;


    public Poi() {
        this(0, null, null, (Drawable) null, 0, 0);
    }

    public Poi(int id, String title, String description, Drawable cardImage, double latitude,
            double longitude) {
        setOnCardDimissedListener();
        mSwipeReaction = RecyclerViewSwipeManager.REACTION_CAN_SWIPE_LEFT | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_RIGHT;
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
        setOnCardDimissedListener();
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

    public void setDescription(String description) {
        this.mDescription = description;
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

    public void setOnCardDimissedListener() {
        this.mOnCardDimissedListener = new Poi.OnCardDimissedListener() {
            public void onLike() {

//                Toast.makeText(MainActivity.ma.getBaseContext(), "I like the card " + getTitle(),
//                        Toast.LENGTH_SHORT)
//                        .show();
                Log.i("Swipeable Cards", "I like the card");
            }

            public void onDislike() {

//                Toast.makeText(getApplicationContext(), "I dislike the card "+card.getTitle(), Toast.LENGTH_SHORT)
//                        .show();
                Log.i("Swipeable Cards", "I dislike the card");
            }
        };
    }

    public OnCardDimissedListener getOnCardDimissedListener() {
        return this.mOnCardDimissedListener;
    }

    public OnClickListener getOnClickListener() {
        return this.mOnClickListener;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mOnClickListener = new Poi.OnClickListener() {
            @Override
            public void OnClickListener() {
                Log.i("Swipeable Cards", "I am pressing the card");
            }
        };
    }

    public interface OnCardDimissedListener {

        void onLike();

        void onDislike();
    }

    public interface OnClickListener {

        void OnClickListener();
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

    public long generateNewChildId() {
        final long id = mNextChildId;
        mNextChildId += 1;
        return id;
    }
}

