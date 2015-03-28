package com.etb.sway.fragment;


import com.etb.sway.MainActivity;
import com.etb.sway.R;
import com.etb.sway.adapter.CardContainer;
import com.etb.sway.adapter.SimpleCardStackAdapter;
import com.etb.sway.model.OrientationListenerInterface;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ortal on 09-Mar-15.
 */

public class CardsScreenFragment extends Fragment implements OrientationListenerInterface {

    private static String MEDIA_PATH = null;

    private View view;

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
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        try {
            view = inflater.inflate(R.layout.fragment_cards_screen, container, false);
        } catch (InflateException e) {
        /* view is already there, just return it as it is */
        }
        mCardContainer = (CardContainer) view
                .findViewById(R.id.card_container);
        mCardContainer.setAdapter(mAdapter);
        mCardContainer.setContext(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        ((MainActivity) activity).onSectionAttached(1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Fragment f = getFragmentManager()
//                .findFragmentByTag("cards_screen_fragment");
//        if (f != null) {
//            getActivity().getSupportFragmentManager().beginTransaction().remove(f).commit();
//        }
    }

    @Override
    public void onLose() {
        mCardContainer.onLose();
    }

    @Override
    public void onSkip() {
        mCardContainer.onSkip();
    }

    @Override
    public void onKeep() {
        mCardContainer.onKeep();
    }
}
