package com.etb.sway;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;

        import com.google.android.gms.maps.GoogleMap;

        import com.etb.sway.models.Likes;

/**
 * Created by ortal on 09-Mar-15.
 */
public class MapActivity extends Activity {

    // Constant for defining latitude and longitude

    private Likes likes;

    // GoogleMap class
    private GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_in_map, menu);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        if (id == R.id.action_sow_cards) {
            Intent intent;
            intent = new Intent(MapActivity.this,MainActivity.class);
            intent.putExtra("likes", likes);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void backToCardsScreen(View view) {

    }
}