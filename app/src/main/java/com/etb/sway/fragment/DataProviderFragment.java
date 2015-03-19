package com.etb.sway.fragment;


import com.etb.sway.model.AbstractDataProvider;
import com.etb.sway.model.DataProvider;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class DataProviderFragment extends Fragment {

    private DataProvider mDataProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);  // keep the mDataProvider instance
        mDataProvider = new DataProvider(true); // true: example test data
    }

    public AbstractDataProvider getDataProvider() {
        return mDataProvider;
    }
}