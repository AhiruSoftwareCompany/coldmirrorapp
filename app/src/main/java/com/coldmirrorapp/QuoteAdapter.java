package com.coldmirrorapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

class QuoteAdapter extends ArrayAdapter<Quote> {

    private boolean useButtonLayout = true;

    QuoteAdapter(Context context, ArrayList<Quote> arrayList) {
        super(context, R.layout.quote_button_item, arrayList);
        QuoteAdapter qa = this;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        Quote quote = getItem(position);
        View v;

        if (useButtonLayout) {
            v = inflater.inflate(R.layout.quote_button_item, parent, false);
            Button quoteName = v.findViewById(R.id.quoteName);
            quoteName.setText(quote.getName());
            quoteName.setTextColor(Color.parseColor(quote.getColor()));
        } else {
            v = inflater.inflate(R.layout.quote_list_item, parent, false);
            TextView quoteName = v.findViewById(R.id.quoteName);
            TextView quoteSource = v.findViewById(R.id.quoteSource);
            TextView quoteCategory = v.findViewById(R.id.quoteCategory);

            quoteName.setText(quote.getName());
            quoteName.setTextColor(Color.parseColor(quote.getColor()));

            quoteSource.setText(quote.getSource());
            quoteSource.setTextColor(Color.parseColor(quote.getColor()));

            quoteCategory.setText(quote.getCategory());
            quoteCategory.setTextColor(Color.parseColor(quote.getColor()));
        }
        return v;
    }


    public void setButtonLayout() {
        useButtonLayout = true;
    }

    public void setListLayout() {
        useButtonLayout = false;
    }
}
