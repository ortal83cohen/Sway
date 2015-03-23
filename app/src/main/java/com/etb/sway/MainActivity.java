package com.etb.sway;

import com.etb.sway.fragment.ExpandableDataProviderFragment;
import com.etb.sway.model.AbstractExpandableDataProvider;
import com.etb.sway.model.LocationChangerInterface;
import com.etb.sway.model.PoiDataProviderHolderInterface;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


/**
 * Created by ortal on 09-Mar-15.
 */

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        PoiDataProviderHolderInterface, LocationChangerInterface {

    public static final String DRAGGABLE_SWIPEABLE_FRAGMENT= "draggable_swipeable_fragment" ;
    public static final String CARDS_SCREEN_FRAGMENT= "cards_screen_fragment" ;

    public static final String LOCATION_CHOOSER_FRAGMENT = "location_chooser_fragment";

    public static final String GOOGLE_MAP_FRAGMENT= "google_map_fragment" ;
    public static final String FRAGMENT_TAG_DATA_PROVIDER = "data provider";

    private int OPTION_MENU = R.menu.global;

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(new ExpandableDataProviderFragment(), FRAGMENT_TAG_DATA_PROVIDER)
                    .commit();

        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (position) {
            case 0:
                OPTION_MENU = R.menu.global;
                fragmentTransaction
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)

                        .replace(R.id.container, LocationChooserFragment.newInstance(),
                                LOCATION_CHOOSER_FRAGMENT);
                break;
            case 1:
                OPTION_MENU = R.menu.global;
                GoogleMapFragment googleMapFragment = GoogleMapFragment.newInstance();
                fragmentTransaction
                        .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter,
                                R.anim.pop_exit)
                        .addToBackStack(LOCATION_CHOOSER_FRAGMENT)
                        .replace(R.id.container, googleMapFragment, GOOGLE_MAP_FRAGMENT);
                break;
            case 2:
                OPTION_MENU = R.menu.main;
                fragmentTransaction
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                      .addToBackStack(LOCATION_CHOOSER_FRAGMENT)
                        .replace(R.id.container, ExpandableDraggableSwipeableFragment.newInstance(),
                                DRAGGABLE_SWIPEABLE_FRAGMENT);
                break;
            case 3:
                OPTION_MENU = R.menu.main;
                fragmentTransaction
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                   .addToBackStack(LOCATION_CHOOSER_FRAGMENT)
                        .replace(R.id.container, CardsScreenFragment.newInstance(),
                                CARDS_SCREEN_FRAGMENT);
                break;
            case 4:
                OPTION_MENU = R.menu.main;
                fragmentTransaction
                        .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                        .addToBackStack(LOCATION_CHOOSER_FRAGMENT)
                        .replace(R.id.container, FeedbackFragment.newInstance(),
                                CARDS_SCREEN_FRAGMENT);
                break;

            default:

        }
        fragmentTransaction.commit();

        invalidateOptionsMenu();

    }

    public void onSectionAttached(int number) {

    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentByTag(GOOGLE_MAP_FRAGMENT) != null) {
            getSupportFragmentManager().popBackStack(LOCATION_CHOOSER_FRAGMENT,0);
            mNavigationDrawerFragment.selectItem(0,false);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(OPTION_MENU, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (item.getItemId() == R.id.item_map) {
            mNavigationDrawerFragment.selectItem(1,false);
        }
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public AbstractExpandableDataProvider getDataProvider() {
        final Fragment fragment = getFragmentManager().findFragmentByTag(
                FRAGMENT_TAG_DATA_PROVIDER);
        return ((ExpandableDataProviderFragment) fragment).getDataProvider();
//        ((PoiDataProviderHolderInterface) getActivity()).getDataProvider()
    }

    @Override
    public void changLocation(String location) {
        mNavigationDrawerFragment.selectItem(1,false);
        //close keyboard
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this
                    .getSystemService(INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}

