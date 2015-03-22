package com.etb.sway.fragment;


import com.etb.sway.model.AbstractExpandableDataProvider;
import com.etb.sway.model.ExpandableDataProvider;

import android.app.Fragment;
import android.os.Bundle;


public class ExpandableDataProviderFragment extends Fragment {

    private ExpandableDataProvider mDataProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);  // keep the mDataProvider instance
        mDataProvider = new ExpandableDataProvider(getActivity());
    }

    public AbstractExpandableDataProvider getDataProvider() {
        return mDataProvider;
    }
}
