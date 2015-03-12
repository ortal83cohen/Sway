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

        import com.etb.sway.helpers.ImageActions;
        import com.etb.sway.model.CardModel;
        import com.etb.sway.model.Likes;
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

    private com.etb.sway.view.SimpleCardStackAdapter adapter;
    private com.etb.sway.view.CardContainer mCardContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cards_screen_layout);

        mCardContainer = (com.etb.sway.view.CardContainer) findViewById(R.id.layoutview);

        Resources r = getResources();

        ImageActions imageActions = new ImageActions(r);

        adapter = new com.etb.sway.view.SimpleCardStackAdapter(this);
        Likes likes = null;
        try {
            Intent intent = getIntent();
            likes = (Likes) intent
                    .getSerializableExtra("likes");
        }catch (Exception e){

        }
        if(likes == null){
            likes= new Likes();
        }

        adapter.add(new com.etb.sway.model.CardModel(1,"Verzetsmuseum", "The Dutch Resistance Museum is located in the Plantage neighbourhood in Amsterdam. The Dutch Resistance Museum, chosen as the best historical museum of the Netherlands, tells the story of the Dutch people in World War II.", imageActions.resize( r.getDrawable(R.drawable.verzetsmuseum)),52.3677799,4.9128112));
        adapter.add(new com.etb.sway.model.CardModel(2,"Van Gogh Museum", "The Van Gogh Museum is an art museum in Amsterdam in the Netherlands dedicated to the works of Vincent van Gogh and his contemporaries.", imageActions.resize(r.getDrawable(R.drawable.van_gogh_museum)),52.358626, 4.881065));
        adapter.add(new com.etb.sway.model.CardModel(3,"Anne Frank House", "The Anne Frank House is a historic house and biographical museum dedicated to Jewish wartime diarist Anne Frank. The building is located at the Prinsengracht, close to the Westerkerk, in central Amsterdam in the Netherlands.",imageActions.resize( r.getDrawable(R.drawable.anne_frank_house)),52.375414, 4.883944));
        adapter.add(new com.etb.sway.model.CardModel(4,"Vondelpark", "The Vondelpark is a public urban park of 47 hectares in Amsterdam, Netherlands. It is part of the borough of Amsterdam-Zuid and situated west from the Leidseplein and the Museumplein.", imageActions.resize(r.getDrawable(R.drawable.vondelpark)),52.358178, 4.868637));
        adapter.add(new com.etb.sway.model.CardModel(5,"National Monument", "The National Monument is a 1956 World War II monument on Dam Square in Amsterdam. A national Remembrance of the Dead ceremony is held at the monument every year on 4 May to commemorate the casualties of World War II and subsequent armed conflicts.", imageActions.resize(r.getDrawable(R.drawable.national_monument)),52.373003, 4.893680));
        adapter.add(new com.etb.sway.model.CardModel(6,"Albert Cuyp Market", "The Albert Cuyp Market is a street market in Amsterdam, The Netherlands, on the Albert Cuypstraat between Ferdinand Bolstraat and Van Woustraat, in the De Pijp area of the Oud-Zuid district of the city.",imageActions.resize( r.getDrawable(R.drawable.albert_cuyp_market)),52.356164, 4.895387));

        mCardContainer.setAdapter(adapter);
        mCardContainer.setLikes(new Likes());
    }

    public void shoeOnMap(View view) {

        Intent intent = new Intent(CardsScreen.this, MapActivity.class);
        Likes likes = mCardContainer.getLikes();
        intent.putExtra("likes", likes);
        startActivity(intent);
    }
}
