package com.coldmirrorapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Klaus
 */

public class QuoteAdapter extends ArrayAdapter<Quote> {

	private Context c;
	private QuoteAdapter qa;
	private Quote quote;

	public QuoteAdapter(Context context, ArrayList<Quote> arrayList) {
		super(context, R.layout.quotes_items, arrayList);
		c = context;
		qa = this;
	}


	///TODO Maybe colorize by category (after putting quotes in enums)
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.quotes_items, parent, false);
		quote = getItem(position);

		TextView quoteName = (TextView) v.findViewById(R.id.quoteName);
		quoteName.setText(quote.getName());

		return v;
	}

	public Context getC() {
		return c;
	}
}
