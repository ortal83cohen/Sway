package com.etb.sway.domain;

/**
 * Created by ortal on 15-Mar-15.
 */

import com.etb.sway.CardsScreenFragment;
import com.etb.sway.ExpandableDraggableSwipeableFragment;
import com.etb.sway.GoogleMapView;
import com.etb.sway.R;
import com.etb.sway.adapter.SimpleCardStackAdapter;
import com.etb.sway.helper.ImageActions;
import com.etb.sway.model.Poi;

import android.app.Application;
import android.content.res.Resources;


public class GlobalState extends Application {

    private CardsScreenFragment cardsScreenFragment;

    private GoogleMapView googleMapView;

    private ExpandableDraggableSwipeableFragment expandableDraggableSwipeableFragment;

    public CardsScreenFragment getCardsScreenFragment() {
        return cardsScreenFragment;
    }

    public ExpandableDraggableSwipeableFragment getExpandableDraggableSwipeableFragment() {
        return expandableDraggableSwipeableFragment;
    }

    public GoogleMapView getGoogleMapView() {
        return googleMapView;
    }

    public void init() {
        cardsScreenFragment = CardsScreenFragment.newInstance();
        googleMapView = GoogleMapView.newInstance();
        expandableDraggableSwipeableFragment = ExpandableDraggableSwipeableFragment.newInstance();

        cardsScreenFragment.setAdapter(getSimpleCardStackAdapter());
//        expandableDraggableSwipeableFragment.setAdapter(adapter);
    }

    private SimpleCardStackAdapter getSimpleCardStackAdapter() {
        SimpleCardStackAdapter adapter
                = new SimpleCardStackAdapter(getApplicationContext());
        Resources r = getResources();

        ImageActions imageActions = new ImageActions(r);
        adapter.add(new Poi(1, "Verzetsmuseum",
                "The Dutch Resistance Museum is located in the Plantage neighbourhood in Amsterdam. The Dutch Resistance Museum, chosen as the best historical museum of the Netherlands, tells the story of the Dutch people in World War II.",
                imageActions.resize(r.getDrawable(
                        R.drawable.verzetsmuseum)), 52.3677799, 4.9128112));
        adapter.add(new Poi(2, "Van Gogh Museum",
                "The Van Gogh Museum is an art museum in Amsterdam in the Netherlands dedicated to the works of Vincent van Gogh and his contemporaries.",
                imageActions.resize(r.getDrawable(R.drawable.van_gogh_museum)), 52.358626,
                4.881065));
        adapter.add(new Poi(3, "Anne Frank House",
                "The Anne Frank House is a historic house and biographical museum dedicated to Jewish wartime diarist Anne Frank. The building is located at the Prinsengracht, close to the Westerkerk, in central Amsterdam in the Netherlands.",
                imageActions.resize(r.getDrawable(R.drawable.anne_frank_house)), 52.375414,
                4.883944));
        adapter.add(new Poi(4, "Vondelpark",
                "The Vondelpark is a public urban park of 47 hectares in Amsterdam, Netherlands. It is part of the borough of Amsterdam-Zuid and situated west from the Leidseplein and the Museumplein.",
                imageActions.resize(r.getDrawable(R.drawable.vondelpark)), 52.358178, 4.868637));
        adapter.add(new Poi(5, "National Monument",
                "The National Monument is a 1956 World War II monument on Dam Square in Amsterdam. A national Remembrance of the Dead ceremony is held at the monument every year on 4 May to commemorate the casualties of World War II and subsequent armed conflicts.",
                imageActions.resize(r.getDrawable(R.drawable.national_monument)), 52.373003,
                4.893680));
        adapter.add(new Poi(6, "Albert Cuyp Market",
                "The Albert Cuyp Market is a street market in Amsterdam, The Netherlands, on the Albert Cuypstraat between Ferdinand Bolstraat and Van Woustraat, in the De Pijp area of the Oud-Zuid district of the city.",
                imageActions.resize(r.getDrawable(R.drawable.albert_cuyp_market)), 52.356164,
                4.895387));
        return adapter;
    }
}