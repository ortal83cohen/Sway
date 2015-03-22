package com.etb.sway;

import com.etb.sway.model.LocationChangerInterface;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

/**
 * Created by ortal on 08-Mar-15.
 */
public class LocationChooserFragment extends Fragment {

    AutoCompleteTextView location;

    String[] locations = {"amsterdam"};

    public static LocationChooserFragment newInstance() {
        LocationChooserFragment fragment = new LocationChooserFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_location_chooser, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

//        View otherLocationsView = getActivity().findViewById(R.id.other_locations_fragment);
//        if (  getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            otherLocationsView.setVisibility(View.GONE);
//        } else {
//            otherLocationsView.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void otherLocations(View view) {
        try {
            ((LocationChangerInterface) getActivity()).changLocation("Amsterdam");

        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(),
                    Toast.LENGTH_LONG)
                    .show();
        }
    }
}
