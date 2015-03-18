
package com.etb.sway.common.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;


import com.etb.sway.common.data.AbstractExpandableDataProvider;
import com.etb.sway.common.data.ExpandableDataProvider;

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
