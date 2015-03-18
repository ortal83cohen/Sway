package com.etb.sway.helper;

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
        return  resize(image,1000, 1200);
    }
    public Drawable resize(Drawable image,int dstWidth, int dstHeight) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, dstWidth, dstHeight, false);
        return new BitmapDrawable(resources, bitmapResized);
    }

}
