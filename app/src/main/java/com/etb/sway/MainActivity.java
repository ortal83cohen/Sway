package com.etb.sway;

import com.etb.sway.model.Likes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by ortal on 09-Mar-15.
 */

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_in_cards, menu);
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

        if (id == R.id.action_show_map) {
            com.etb.sway.view.CardContainer mCardContainer = (com.etb.sway.view.CardContainer) findViewById(R.id.layoutview);
            Intent intent = new Intent(this, MapActivity.class);
            Likes likes = mCardContainer.getLikes();
            intent.putExtra("likes", likes);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
