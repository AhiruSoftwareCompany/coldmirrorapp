package com.coldmirrorapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class QuoteAdapter extends ArrayAdapter<Quote> {

    private boolean useButtonLayout = true;
    private ArrayList<Quote> quotes;
    private Context context;
    private Quote quote;

    QuoteAdapter(Context context, ArrayList<Quote> arrayList) {
        super(context, R.layout.quote_list_item, arrayList);
        this.context = context;
        this.quotes = arrayList;
    }

    @Override
    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.quote_list_item, parent, false);

        TextView quoteTitle = convertView.findViewById(R.id.quoteTitle);
        TextView quoteCategory = convertView.findViewById(R.id.quoteCategory);
        TextView quoteSource = convertView.findViewById(R.id.quoteSource);

        quoteTitle.setText(quotes.get(position).getName());
        quoteTitle.setTextColor(Color.parseColor(quotes.get(position).getColor()));
        quoteCategory.setText(quotes.get(position).getCategoryName());
        quoteCategory.setTextColor(Color.parseColor(quotes.get(position).getColor()));

        quoteSource.setText(quotes.get(position).getSource());
        quoteSource.setTextColor(Color.parseColor(quotes.get(position).getColor()));

        return convertView;
/*
            Button quoteName = v.findViewById(R.id.quoteName);
            quoteName.setText(quote.getName());
            quoteName.setTextColor(Color.parseColor(quote.getColor()));

            TextView quoteName = v.findViewById(R.id.quoteName);
            TextView quoteSource = v.findViewById(R.id.quoteSource);
            TextView quoteCategory = v.findViewById(R.id.quoteCategory);

            quoteName.setText(quote.getName());
            quoteName.setTextColor(Color.parseColor(quote.getColor()));

            quoteSource.setText(quote.getSource());
            quoteSource.setTextColor(Color.parseColor(quote.getColor()));

            quoteCategory.setText(quote.getCategory());
            quoteCategory.setTextColor(Color.parseColor(quote.getColor()));
*/
    }
}
