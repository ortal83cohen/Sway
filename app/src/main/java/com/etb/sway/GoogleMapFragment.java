package com.etb.sway;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.etb.sway.model.CardModel;
import com.etb.sway.model.Likes;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by inbal on 3/14/2015.
 */
public class GoogleMapFragment extends Fragment {

    private Likes likes;

    // GoogleMap class
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_google_map,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        try {
            Intent intent = getActivity().getIntent();
            likes = (Likes) intent
                    .getSerializableExtra("likes");
        }catch (Exception e){

        }
        if(likes == null){
            likes= new Likes();
        }
        // verify we can interact with the Google Map
        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getChildFragmentManager().
                        findFragmentById(R.id.google_map)).getMap();
            }
            // Show a satellite map with roads
            /* MAP_TYPE_NORMAL: Basic map with roads.
            MAP_TYPE_SATELLITE: Satellite view with roads.
            MAP_TYPE_TERRAIN: Terrain view without roads.
            */
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            // Place dot on current location
            googleMap.setMyLocationEnabled(true);

            // Turns traffic layer on
            googleMap.setTrafficEnabled(true);

            // Enables indoor maps
            googleMap.setIndoorEnabled(true);

            // Turns on 3D buildings
            googleMap.setBuildingsEnabled(true);

            // Show Zoom buttons
            googleMap.getUiSettings().setZoomControlsEnabled(true);

            LatLng amsterdam = new LatLng(52.3704,4.897);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(amsterdam)          // Sets the center of the map to Mountain View
                    .zoom(12)                   // Sets the zoom
//                    .bearing(90)                // Sets the orientation of the camera to east
//                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            BitmapDescriptor bitmapMarker
                    = BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
            for( CardModel cardModel: likes.getLikeCardList()){

                googleMap.addMarker(new MarkerOptions().
                        position(new LatLng(cardModel.getLatitude(),
                                cardModel.getLongitude())).title(cardModel.getTitle()).snippet(
                        cardModel.getDescription()).icon(
                        bitmapMarker));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
