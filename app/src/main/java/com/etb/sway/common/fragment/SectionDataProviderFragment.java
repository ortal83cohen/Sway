
package com.etb.sway.common.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;


import com.etb.sway.common.data.AbstractDataProvider;
import com.etb.sway.common.data.SectionDataProvider;

public class SectionDataProviderFragment extends Fragment {
    private SectionDataProvider mDataProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);  // keep the mDataProvider instance
        mDataProvider = new SectionDataProvider();
    }

    public AbstractDataProvider getDataProvider() {
        return mDataProvider;
    }
}
