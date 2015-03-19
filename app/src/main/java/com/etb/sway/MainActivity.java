package com.etb.sway;

import com.etb.sway.common.data.MapPoiInterface;
import com.etb.sway.domain.GlobalState;
import com.etb.sway.model.CardModel;
import com.etb.sway.model.Likes;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;


/**
 * Created by ortal on 09-Mar-15.
 */

public class MainActivity extends ActionBarActivity  implements NavigationDrawerFragment.NavigationDrawerCallbacks , LikeListenerHolder {

    public static String DRAGGABLE_SWIPEABLE_FRAGMENT= "draggable_swipeable_fragment" ;
    public static String CARDS_SCREEN_FRAGMENT= "cards_screen_fragment" ;
    public static String GOOGLE_MAP_FRAGMENT= "google_map_fragment" ;
    private Likes likes;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;


    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likes=new Likes();
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
                    .replace(R.id.container,gs.getCardsScreenFragment(),CARDS_SCREEN_FRAGMENT)
                    .commit();
        }
        if(position == 1){
            fragmentManager.beginTransaction()
                    .replace(R.id.container, gs.getExpandableDraggableSwipeableFragment(),DRAGGABLE_SWIPEABLE_FRAGMENT)
                    .commit();

        }
        if(position == 2){

            com.etb.sway.view.CardContainer mCardContainer = (com.etb.sway.view.CardContainer) findViewById(R.id.layoutview);
            GoogleMapView googleMapView = gs.getGoogleMapView();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, googleMapView,GOOGLE_MAP_FRAGMENT)
                    .commit();
        }
//        View itemMap = findViewById(R.id.item_map);
//        itemMap.setVisibility(View.VISIBLE);
    }

    public void onSectionAttached(int number) {

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

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        GlobalState gs = (GlobalState) getApplication();

        int id = item.getItemId();

        if (item.getItemId() == R.id.item_map) {
            mNavigationDrawerFragment.selectItem(2);
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLike(int id) {

    }

    @Override
    public void onDislike(int id) {

    }

    @Override
    public void onUndo(int id) {

    }

    @Override
    public void addLikeItem(MapPoiInterface cardModel) {
        likes.addLikeItem(cardModel);
    }

    @Override
    public void addDisLikeItem(MapPoiInterface cardModel) {
        likes.addDisLikeItem(cardModel);
    }

    @Override
    public Likes getLikes() {
        return likes;
    }
}

