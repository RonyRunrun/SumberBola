package com.oktagon.sumberbola.sumberbola.adapters;

/**
 * Created by RR_PC on 2/19/2017.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oktagon.sumberbola.sumberbola.R;
import com.oktagon.sumberbola.sumberbola.service.AndroidVersion;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataset;
    private ArrayList<AndroidVersion> android;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTextView;
        public TextView mDetView;
        public TextView mVerView;

        public MyViewHolder(View v) {
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text);
            mDetView = (TextView) v.findViewById(R.id.tv_blah);
            mVerView = (TextView) v.findViewById(R.id.tv_wew);

        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<AndroidVersion> android) {
        this.android = android;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        holder.mTextView.setText(android.get(position).getName());
        holder.mDetView.setText(android.get(position).getName());
        holder.mVerView.setText(android.get(position).getVer());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }
}