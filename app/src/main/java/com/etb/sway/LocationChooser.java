package com.etb.sway;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by ortal on 08-Mar-15.
 */
public class LocationChooser extends Activity {

    AutoCompleteTextView location;
    String[] locations = {"amsterdam"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_chooser);


        //Creating the instance of ArrayAdapter containing list of language names
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, locations);
        //Getting the instance of AutoCompleteTextView
        location = (AutoCompleteTextView) findViewById(
                R.id.autoCompleteTextView_location);
        location.setThreshold(1);//will start working from first character
        location.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        location.setTextColor(Color.RED);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void chosenLocation(View view) {
        try {
            if (location == null || !Arrays.asList(locations).contains(location.getText().toString())) {
                Toast.makeText(this, getString(R.string.location_chooser_warning),
                        Toast.LENGTH_LONG)
                        .show();
            } else {
                Intent intent = new Intent(LocationChooser.this, CardsScreen.class);
                startActivity(intent);
                finish();
            }
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),
                    Toast.LENGTH_LONG)
                    .show();
        }
    }
}
