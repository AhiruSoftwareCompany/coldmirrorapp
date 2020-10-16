package com.coldmirrorapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class QuoteAdapter extends ArrayAdapter<Quote> {

    private final ArrayList<Quote> quotes;
    private final Context context;
    private final ListStatus listStatus;

    QuoteAdapter(Context context, ArrayList<Quote> arrayList, ListStatus listStatus) {
        super(context, R.layout.quote_list_item, arrayList);

        this.context = context;
        this.quotes = arrayList;
        this.listStatus = listStatus;
    }

    @Override
    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Quote quote = quotes.get(position);

        if (listStatus == ListStatus.BUTTON) {
            convertView = inflater.inflate(R.layout.quote_button_item, parent, false);
            Button quoteName = convertView.findViewById(R.id.quoteName);
            quoteName.setText(quote.getName());
            quoteName.setTextColor(Color.parseColor(quote.getColor()));

        } else {
            convertView = inflater.inflate(R.layout.quote_list_item, parent, false);
            TextView quoteTitle = convertView.findViewById(R.id.quoteTitle);
            TextView quoteCategory = convertView.findViewById(R.id.quoteCategory);
            TextView quoteSource = convertView.findViewById(R.id.quoteSource);

            quoteTitle.setText(quote.getName());
            quoteTitle.setTextColor(Color.parseColor(quote.getColor()));
            quoteCategory.setText(quote.getCategoryName());
            quoteCategory.setTextColor(Color.parseColor(quote.getColor()));

            quoteSource.setText(quote.getSource());
            quoteSource.setTextColor(Color.parseColor(quote.getColor()));
        }


        return convertView;
        /*

         */
    }
}
