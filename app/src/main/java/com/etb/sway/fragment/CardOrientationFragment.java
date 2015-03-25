package com.etb.sway.fragment;

import com.etb.sway.R;
import com.etb.sway.adapter.CardContainer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by ortal on 25-Mar-15.
 */
public class CardOrientationFragment extends Fragment {

    public static final String CARDS_SCREEN_FRAGMENT = "cards_screen_fragment";

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        try {
            view = inflater.inflate(R.layout.fragment_cards_orientation, container, false);
        } catch (InflateException e) {
        /* view is already there, just return it as it is */
        }



        Button loseCard = (Button) getActivity().findViewById(R.id.lose_card);
        Button skipCard = (Button) getActivity().findViewById(R.id.skip_card);
        Button keepCard = (Button) getActivity().findViewById(R.id.keep_card);




        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        Fragment f = getActivity().getSupportFragmentManager()
                .findFragmentById(R.layout.fragment_cards_orientation);
        if (f != null) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(f).commit();
        }
    }


}
