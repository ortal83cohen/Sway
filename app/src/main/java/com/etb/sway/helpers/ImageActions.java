package com.etb.sway.helpers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by ortal on 12-Mar-15.
 */
public class ImageActions {
    Resources resources;

    public ImageActions(Resources app_resources) {
        resources = app_resources;
    }

    public Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 1000, 1200, false);
        return new BitmapDrawable(resources, bitmapResized);
    }

}
