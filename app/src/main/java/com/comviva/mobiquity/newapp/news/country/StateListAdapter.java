package com.comviva.mobiquity.newapp.news.country;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class StateListAdapter extends ArrayAdapter {

    private ArrayList<Country> listItems;

    public StateListAdapter(Context context, int textViewResourceId,
                            ArrayList<Country> values) {
        super(context, textViewResourceId, values);
        this.listItems = new ArrayList<>(values);
    }


    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Country getItem(int itemPosition) {
        return listItems.get(itemPosition);
    }

    @Override
    public long getItemId(int itemPosition) {
        return itemPosition;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Country country = listItems.get(position);
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(country.getCountryValue());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView dropDownStateView = (TextView) super.getDropDownView(position, convertView, parent);
        dropDownStateView.setTextColor(Color.BLACK);
        dropDownStateView.setText(listItems.get(position).getCountryValue());
        return dropDownStateView;
    }
}
