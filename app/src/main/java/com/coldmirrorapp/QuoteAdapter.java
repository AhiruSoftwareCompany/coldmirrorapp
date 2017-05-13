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

    private QuoteAdapter qa;
    private Quote quote;

    QuoteAdapter(Context context, ArrayList<Quote> arrayList) {
        super(context, R.layout.quotes_items, arrayList);
        qa = this;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.quotes_items, parent, false);
        quote = getItem(position);

        Button quoteName = (Button) v.findViewById(R.id.quoteName);
        quoteName.setText(quote.getName());
        quoteName.setTextColor(Color.parseColor(quote.getColor()));

        return v;
    }
}
