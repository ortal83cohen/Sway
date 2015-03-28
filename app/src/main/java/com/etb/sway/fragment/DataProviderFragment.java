package com.etb.sway.fragment;


import com.etb.sway.model.AbstractExpandableDataProvider;
import com.etb.sway.model.DataProvider;

import android.app.Fragment;
import android.os.Bundle;


public class DataProviderFragment extends Fragment {

    private DataProvider mDataProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);  // keep the mDataProvider instance
        mDataProvider = new DataProvider(getActivity());
    }

    public AbstractExpandableDataProvider getDataProvider() {
        return mDataProvider;
    }
}
