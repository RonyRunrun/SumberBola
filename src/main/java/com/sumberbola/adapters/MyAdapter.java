package com.sumberbola.adapters;

/**
 * Created by RR_PC on 2/19/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sumberbola.DetailBerita;
import com.sumberbola.R;
import com.sumberbola.service.Coba;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataset;
    private ArrayList<Coba> coba;
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView mTextView;
        public TextView mDetView;
        public TextView mVerView;
        public ImageView mImgPost;

        public MyViewHolder(View v) {
            super(v);
            mImgPost = (ImageView) v.findViewById(R.id.iv_image);
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.tv_text);
            mDetView = (TextView) v.findViewById(R.id.tv_blah);
            mVerView = (TextView) v.findViewById(R.id.tv_wew);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Coba> coba) {
        this.coba = coba;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        mContext = parent.getContext();
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
       // String ss=String.valueOf(coba.get(position).getBetterFeaturedImage().getMediaDetails().getSizes().getTd356x364().getSourceUrl());
        if (coba.get(position).getBetterFeaturedImage().getMediaDetails() == null) {
            Glide.with(mContext).load("")
                    .placeholder(R.mipmap.hazard)
                    .thumbnail(0.8f)
                    .override(220,220)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.mImgPost);
        }else {
            Glide.with(mContext).load(String.valueOf(coba.get(position).getBetterFeaturedImage().getMediaDetails().getSizes().getTd356x364().getSourceUrl()))
                    .thumbnail(0.8f)
                    .override(220, 220)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.mImgPost);
        }
        holder.mTextView.setText(Html.fromHtml(String.valueOf(coba.get(position).getTitle().getRendered())));
     //   holder.mDetView.setText(Html.fromHtml(String.valueOf(coba.get(position).getContent().getRendered())));
    //  holder.mDetView.setText(String.valueOf(coba.get(position).getSlug()));
        Locale locale= new Locale("id","ID");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat destFormat = new SimpleDateFormat("dd-MMM-yyyy",locale); //here 'a' for AM/PM
        Date date = null;
        try {
            date = sourceFormat.parse(String.valueOf(coba.get(position).getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final String formattedDate = destFormat.format(date);

        holder.mVerView.setText(formattedDate);

        holder.mCardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(v.getContext(),DetailBerita.class);
                intent.putExtra("Gambargede",String.valueOf(coba.get(position).getBetterFeaturedImage().getMediaDetails().getSizes().getTd534x462().getSourceUrl()));
                intent.putExtra("Gambar",String.valueOf(coba.get(position).getBetterFeaturedImage().getMediaDetails().getSizes().getMedium().getSourceUrl()));
                intent.putExtra("Content",String.valueOf(coba.get(position).getContent().getRendered()));
                intent.putExtra("Judul",String.valueOf(coba.get(position).getTitle().getRendered()));
                intent.putExtra("Kategori", String.valueOf(coba.get(position).getCategories()));
                intent.putExtra("Tanggal",formattedDate);
                intent.putExtra("Link",String.valueOf(coba.get(position).getLink()));
                intent.putExtra("Tags",String.valueOf(coba.get(position).getTags()));
                v.getContext().startActivity(intent);
                System.out.println("Clicked");
            }
        });
    }

    @Override
    public int getItemCount() {
        return (coba == null) ? 0 : coba.size();
    }
}