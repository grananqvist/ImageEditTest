package com.fgranqvist.imageedit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Filip on 2016-06-05.
 */
class CustomAdapter extends ArrayAdapter<String> {
    public CustomAdapter(Context context, String[] filters) {
        super(context, R.layout.custom_row, filters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.custom_row, parent, false);

        String filterItem = getItem(position);
        TextView custom_rowText = (TextView) customView.findViewById(R.id.custom_rowText);
        ImageView custom_rowPic = (ImageView) customView.findViewById(R.id.custom_rowPic);

        custom_rowText.setText(filterItem);
        custom_rowPic.setImageResource(R.drawable.bert);
        return customView;
    }
}
