package com.etb.sway.adapter;


import com.etb.sway.R;
import com.etb.sway.model.AbstractExpandableDataProvider;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public final class SimpleCardStackAdapter extends CardStackAdapter {

    public SimpleCardStackAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public View getCardView(int position, AbstractExpandableDataProvider.GroupData model,
            View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.std_card_inner, parent, false);
            assert convertView != null;
        }

        ((ImageView) convertView.findViewById(R.id.image))
                .setImageDrawable(model.getCardImage());
        ((TextView) convertView.findViewById(R.id.title)).setText(model.getTitle());
        ((TextView) convertView.findViewById(R.id.description)).setText(model.getDescription());

        return convertView;
    }
}
