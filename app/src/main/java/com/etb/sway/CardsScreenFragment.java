package com.etb.sway;


import com.etb.sway.adapter.CardContainer;
import com.etb.sway.adapter.SimpleCardStackAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ortal on 09-Mar-15.
 */

public class CardsScreenFragment extends Fragment {

    private static String MEDIA_PATH = null;

    /**
     * This variable is the container that will host our cards
     */

    private SimpleCardStackAdapter adapter;

    private CardContainer mCardContainer;

    public static CardsScreenFragment newInstance() {
        CardsScreenFragment fragment = new CardsScreenFragment();

        return fragment;
    }

    public SimpleCardStackAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(SimpleCardStackAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_cards_screen, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCardContainer = (CardContainer) getActivity()
                .findViewById(R.id.layoutview);
        mCardContainer.setAdapter(adapter);
        mCardContainer.setContext(getActivity());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(1);
    }

}
