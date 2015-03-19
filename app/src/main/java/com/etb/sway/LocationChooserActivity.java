package com.etb.sway;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by ortal on 08-Mar-15.
 */
public class LocationChooserActivity extends FragmentActivity {

    AutoCompleteTextView location;

    String[] locations = {"amsterdam"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_chooser);

        FragmentManager fragmentManager = getFragmentManager();

        // beginTransaction() begins the FragmentTransaction which allows you to
        // add, attach, detach, hide, remove, replace, animate, transition or
        // show fragments
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        // The Configuration object provides device configuration info
        // http://developer.android.com/reference/android/content/res/Configuration.html
        Configuration configInfo = getResources().getConfiguration();

        // Depending on the screen orientation replace with the correct fragment

//        OtherLocationsFragment otherLocationsFragment = new OtherLocationsFragment();
//        InsertLocationFragment insertLocationFragment = new InsertLocationFragment();
//        fragmentTransaction.replace(R.id.other_locations_fragment,
//                otherLocationsFragment);
//        fragmentTransaction.replace(R.id.insert_location_fragment,
//                insertLocationFragment);
        View view = findViewById(R.id.other_locations_fragment);
        if (configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }

        // Schedule for the replacement of the Fragment as soon as possible
        fragmentTransaction.commit();

        // setContentView(R.layout.activity_my);
    }

    public void chosenLocation(View view) {
        try {
            location = (AutoCompleteTextView) findViewById(
                    R.id.autoCompleteTextView_location);
            if (location == null || !Arrays.asList(locations)
                    .contains(location.getText().toString())) {
                Toast.makeText(LocationChooserActivity.this,
                        getString(R.string.location_chooser_warning),
                        Toast.LENGTH_LONG)
                        .show();
            } else {
                Intent intent = new Intent(LocationChooserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        } catch (Exception e) {
            Toast.makeText(LocationChooserActivity.this, e.getMessage(),
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void otherLocations(View view) {
        try {
            Intent intent = new Intent(LocationChooserActivity.this, MainActivity.class);
            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(LocationChooserActivity.this, e.getMessage(),
                    Toast.LENGTH_LONG)
                    .show();
        }
    }
}
