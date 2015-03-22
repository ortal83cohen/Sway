package com.etb.sway;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.etb.sway.model.MapPoiInterface;
import com.etb.sway.model.PoiDataProviderHolderInterface;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ortal on 3/14/2015.
 */
public class GoogleMapView extends Fragment {


    // GoogleMap class
    private GoogleMap googleMap;

    private View view;

    public static GoogleMapView newInstance() {
        GoogleMapView fragment = new GoogleMapView();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_google_map, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // verify we can interact with the Google Map
        try {
//            if (googleMap == null) {
            MapView mMapView = ((MapView) view.findViewById(R.id.google_map));
            mMapView.onCreate(savedInstanceState);

            mMapView.onResume();// needed to get the map to display immediately

            try {
                MapsInitializer.initialize(getActivity().getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            googleMap = mMapView.getMap();
//            }
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

            LatLng amsterdam = new LatLng(52.3704, 4.897);
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
            for (MapPoiInterface MapPoi : ((PoiDataProviderHolderInterface) getActivity())
                    .getDataProvider().getLikeData()) {

                googleMap.addMarker(new MarkerOptions().
                        position(new LatLng(MapPoi.getLatitude(),
                                MapPoi.getLongitude())).title(MapPoi.getTitle()).snippet(
                        MapPoi.getDescription()).icon(
                        bitmapMarker));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(3);
    }

}
