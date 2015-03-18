
package com.etb.sway.common.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.etb.sway.common.data.AbstractDataProvider;
import com.etb.sway.common.data.DataProvider;

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
