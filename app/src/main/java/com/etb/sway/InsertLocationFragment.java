package com.etb.sway;

import com.etb.sway.model.LocationChangerInterface;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by ortal on 10-Mar-15.
 */
public class InsertLocationFragment extends Fragment {

    AutoCompleteTextView location;

    String[] locations = {"amsterdam"};

    public static InsertLocationFragment newInstance() {
        InsertLocationFragment fragment = new InsertLocationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_location, container, false);
        //Creating the instance of ArrayAdapter containing list of language names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.select_dialog_item, locations);
        //Getting the instance of AutoCompleteTextView
        location = (AutoCompleteTextView) view.findViewById(
                R.id.autoCompleteTextView_location);
        location.setThreshold(1);//will start working from first character
        location.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//        location.setTextColor(Color.RED);

        Button button = (Button) view.findViewById(R.id.SearchLocationButton);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    location = (AutoCompleteTextView) getActivity().findViewById(
                            R.id.autoCompleteTextView_location);
                    if (location == null || !Arrays.asList(locations)
                            .contains(location.getText().toString())) {
                        Toast.makeText(getActivity(),
                                getString(R.string.location_chooser_warning),
                                Toast.LENGTH_LONG)
                                .show();
                    } else {
                        ((LocationChangerInterface) getActivity())
                                .changLocation(location.getText().toString());
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(),
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        location.setInputType(InputType.TYPE_CLASS_TEXT);


        location.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    ((LocationChangerInterface)getActivity()).changLocation(location.getText().toString());
                    return true;
                }
                return false;
            }
        });

        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


}


