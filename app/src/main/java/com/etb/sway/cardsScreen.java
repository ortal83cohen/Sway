package com.etb.sway;


        import android.app.Activity;
        import android.content.Intent;
        import android.content.res.Resources;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.drawable.Drawable;
        import android.os.Bundle;
        import android.os.Environment;
        import android.util.Log;
        import android.view.View;
        import android.view.Window;
        import android.widget.Toast;

        import com.etb.sway.model.CardModel;
        import com.etb.sway.view.CardContainer;
        import com.etb.sway.view.SimpleCardStackAdapter;

        import java.io.File;
        import java.io.InputStream;
        import java.net.URL;

/**
 * Created by ortal on 09-Mar-15.
 */

public class CardsScreen extends Activity {

    private static String MEDIA_PATH = null;

    /**
     * This variable is the container that will host our cards
     */
    private com.etb.sway.view.CardContainer mCardContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cards_screen_layout);

        mCardContainer = (com.etb.sway.view.CardContainer) findViewById(R.id.layoutview);

        Resources r = getResources();

        com.etb.sway.view.SimpleCardStackAdapter
                adapter = new com.etb.sway.view.SimpleCardStackAdapter(this);

        adapter.add(new com.etb.sway.model.CardModel(1,"Title1", "Description goes here1", r.getDrawable(R.drawable.picture1),1,2));
        adapter.add(new com.etb.sway.model.CardModel(2,"Title2", "Description goes here2", r.getDrawable(R.drawable.picture2),1,2));
        adapter.add(new com.etb.sway.model.CardModel(3,"Title3", "Description goes here3", r.getDrawable(R.drawable.picture3),1,2));
        adapter.add(new com.etb.sway.model.CardModel(4,"Title4", "Description goes here4", r.getDrawable(R.drawable.picture1),1,2));
        adapter.add(new com.etb.sway.model.CardModel(5,"Title5", "Description goes here5", r.getDrawable(R.drawable.picture2),1,2));
        adapter.add(new com.etb.sway.model.CardModel(6,"Title6", "Description goes here6", r.getDrawable(R.drawable.picture3),1,2));


        com.etb.sway.model.CardModel
                cardModel = new com.etb.sway.model.CardModel(7,"Title1", "Description goes here7", r.getDrawable(R.drawable.picture1),1,2);
        cardModel.setOnClickListener(new com.etb.sway.model.CardModel.OnClickListener() {
            @Override
            public void OnClickListener() {
                Log.i("Swipeable Cards", "I am pressing the card");
            }
        });

        cardModel.setOnCardDimissedListener(new com.etb.sway.model.CardModel.OnCardDimissedListener() {
            @Override
            public void onLike(CardModel card) {
                Toast.makeText(getApplicationContext(), "I like the card ", Toast.LENGTH_SHORT)
                        .show();
                //  Log.i("Swipeable Cards","I like the card");
            }

            @Override
            public void onDislike(CardModel card) {
                card.getId();
                Toast.makeText(getApplicationContext(), "I dislike the card "+card.getTitle(), Toast.LENGTH_SHORT)
                        .show();
//                Log.i("Swipeable Cards","I dislike the card");
            }
        });

        adapter.add(cardModel);

        mCardContainer.setAdapter(adapter);
    }

    public void shoeOnMap(View view) {
        Intent intent = new Intent(CardsScreen.this, MapActivity.class);
        startActivity(intent);
        finish();
    }
}
