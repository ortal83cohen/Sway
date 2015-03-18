
package com.etb.sway.common.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewParent;

public class AdapterUtils {
    public static View findParentViewHolderItemView(View v) {
        final ViewParent parent = v.getParent();
        if (parent instanceof RecyclerView) {
            // returns the passed instance if the parent is RecyclerView
            return v;
        } else if (parent instanceof View) {
            // check the parent view recursively
            return findParentViewHolderItemView((View) parent);
        } else {
            return null;
        }
    }
}
