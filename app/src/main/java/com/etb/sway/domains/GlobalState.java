package com.etb.sway.domains;

/**
 * Created by ortal on 15-Mar-15.
 */
import com.etb.sway.CardsScreenFragment;
import com.etb.sway.GoogleMapView;

import android.app.Application;


public class GlobalState extends Application
{
    private CardsScreenFragment cardsScreenFragment;
    private GoogleMapView googleMapView;

    public CardsScreenFragment getCardsScreenFragment() {
        return cardsScreenFragment;
    }

    public GoogleMapView getGoogleMapView() {
        return googleMapView;
    }

    public void init() {
        cardsScreenFragment = CardsScreenFragment.newInstance();
        googleMapView = GoogleMapView.newInstance();
    }
}