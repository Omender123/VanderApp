package com.Vander.Vanderapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Vander.Vanderapp.ModelResponse.categoryid;
import com.Vander.Vanderapp.R;

import java.util.ArrayList;

public class CategorySpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<categoryid>categoryids;
    LayoutInflater inflter;
    public CategorySpinnerAdapter(Context context, ArrayList<categoryid> categoryids) {
        this.context = context;
        this.categoryids = categoryids;
        inflter = (LayoutInflater.from(context));
    }



    @Override
    public int getCount() {
        return categoryids.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.category_spinner_layout, null);
        TextView Names = (TextView) view.findViewById(R.id.coinName);
        //TextView coinSymbol = (TextView) view.findViewById(R.id.coinsymbols);

       // coinImages.setImageResource(coinImage[i]);


        Names.setText(categoryids.get(position).getName());
       // coinSymbol.setText(coinSymbols[position]);


        return view;
    }
    }

