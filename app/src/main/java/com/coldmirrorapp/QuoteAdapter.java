package com.coldmirrorapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

class QuoteAdapter extends ArrayAdapter<Quote> {

    QuoteAdapter(Context context, ArrayList<Quote> arrayList) {
        super(context, R.layout.quotes_items, arrayList);
        QuoteAdapter qa = this;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.quotes_items, parent, false);
        Quote quote = getItem(position);

        Button quoteName = v.findViewById(R.id.quoteName);
        quoteName.setText(quote.getName());
        quoteName.setTextColor(Color.parseColor(quote.getColor()));

        return v;
    }
}
