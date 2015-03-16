package com.etb.sway;

import com.etb.sway.domain.GlobalState;
import com.etb.sway.model.Likes;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

/**
 * Created by ortal on 09-Mar-15.
 */

public class MainActivity extends ActionBarActivity        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        GlobalState gs = (GlobalState) getApplication();

        if(position == 0){
            fragmentManager.beginTransaction()
                    .replace(R.id.container,gs.getCardsScreenFragment())
                    .commit();
        }
        if(position == 1){

            Toast.makeText(this,"We currently don't support list view", Toast.LENGTH_LONG).show();
        }
        if(position == 2){

            com.etb.sway.view.CardContainer mCardContainer = (com.etb.sway.view.CardContainer) findViewById(R.id.layoutview);
            Likes likes = mCardContainer != null ?mCardContainer.getLikes():new Likes();
            GoogleMapView googleMapView = gs.getGoogleMapView();
            googleMapView.setLikes(likes);
            fragmentManager.beginTransaction()
                    .replace(R.id.container, googleMapView)
                    .commit();
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.cards_view);
                break;
            case 2:
                mTitle = getString(R.string.list_view);
                break;
            case 3:
                mTitle = getString(R.string.map_view);

                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        if (!mNavigationDrawerFragment.isDrawerOpen()) {
//            // Only show items in the action bar relevant to this screen
//            // if the drawer is not showing. Otherwise, let the drawer
//            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);


//            restoreActionBar();
//            return true;
//        }
        return super.onCreateOptionsMenu(menu);
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

}
