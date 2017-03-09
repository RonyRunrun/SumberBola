package com.oktagon.sumberbola.sumberbola.adapters;

/**
 * Created by RR_PC on 2/19/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oktagon.sumberbola.sumberbola.DetailBerita;
import com.oktagon.sumberbola.sumberbola.R;
import com.oktagon.sumberbola.sumberbola.service.PostWp;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataset;
    //private ArrayList<AndroidVersion> android;
    private ArrayList<PostWp> postWpList;
    Context context;
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
    public MyAdapter(ArrayList<PostWp> postWpList) {
        this.postWpList = postWpList;
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
        holder.mTextView.setText(postWpList.get(position).getStudentId());
        holder.mDetView.setText(postWpList.get(position).getStudentName());
        holder.mVerView.setText(postWpList.get(position).getStudentMarks());
        holder.mCardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),DetailBerita.class);
                v.getContext().startActivity(intent);
                System.out.println("Clicked");
            }
        });
    }

    @Override
    public int getItemCount() {
        return (postWpList == null) ? 0 : postWpList.size();
    }
}