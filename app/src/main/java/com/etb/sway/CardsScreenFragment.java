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

    private SimpleCardStackAdapter mAdapter;

    private CardContainer mCardContainer;

    public static CardsScreenFragment newInstance() {
        CardsScreenFragment fragment = new CardsScreenFragment();

        return fragment;
    }

    public SimpleCardStackAdapter getAdapter() {
        return mAdapter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mAdapter = new SimpleCardStackAdapter(getActivity());

        return inflater.inflate(R.layout.fragment_cards_screen, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCardContainer = (CardContainer) getActivity()
                .findViewById(R.id.layoutview);
        mCardContainer.setAdapter(mAdapter);
        mCardContainer.setContext(getActivity());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(1);
    }

}
