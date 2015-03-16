package com.etb.sway;


        import android.app.Activity;
        import android.support.v4.app.Fragment;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.etb.sway.models.Likes;
        import com.etb.sway.view.SimpleCardStackAdapter;

/**
 * Created by ortal on 09-Mar-15.
 */

public class CardsScreenFragment extends Fragment {

    private static String MEDIA_PATH = null;

    /**
     * This variable is the container that will host our cards
     */

    private com.etb.sway.view.SimpleCardStackAdapter adapter;
    private com.etb.sway.view.CardContainer mCardContainer;

    public static CardsScreenFragment newInstance() {
        CardsScreenFragment fragment = new CardsScreenFragment();

        return fragment;
    }

    public void setAdapter(SimpleCardStackAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_cards_screen,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

            mCardContainer = (com.etb.sway.view.CardContainer) getActivity().findViewById(R.id.layoutview);
            mCardContainer.setAdapter(adapter);
            mCardContainer.setLikes(new Likes());
            Likes likes = null;
            try {
                Intent intent = getActivity().getIntent();
                likes = (Likes) intent
                        .getSerializableExtra("likes");
            }catch (Exception e){

            }
            if(likes == null){
                likes= new Likes();
            }
        }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(1);
    }

}
