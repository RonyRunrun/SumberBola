package com.sumberbola.adapters;

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

/**
 * Created by Rony R on 3/15/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private String[] mDataset;
    private ArrayList<Coba> coba;
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public CardView mCardRel;
        public TextView mJdlRel;
        public ImageView mImgRel;

        public MyViewHolder(View v) {
            super(v);
            mImgRel = (ImageView) v.findViewById(R.id.iv_rel_head);
            mCardRel = (CardView) v.findViewById(R.id.card_rel);
            mJdlRel = (TextView) v.findViewById(R.id.tv_rel_jdl);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(ArrayList<Coba> coba) {
        this.coba = coba;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        mContext = parent.getContext();
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_terkait, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ListAdapter.MyViewHolder vh = new ListAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ListAdapter.MyViewHolder holder, final int position) {
        Glide.with(mContext).load(String.valueOf(coba.get(position).getBetterFeaturedImage().getMediaDetails().getSizes().getMedium().getSourceUrl()))
                .thumbnail(0.8f)
                .crossFade()
                .override(170,170)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mImgRel);
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
        holder.mJdlRel.setText(Html.fromHtml(String.valueOf(coba.get(position).getTitle().getRendered())));
        holder.mCardRel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(v.getContext(),DetailBerita.class);
                //intent.putExtra("AltGambar",String.valueOf(coba.get(position).getBetterFeaturedImage().getMediaDetails().getSizes().getLarge().getSourceUrl()));
                //intent.putExtra("Gambargede",String.valueOf(coba.get(position).getBetterFeaturedImage().getMediaDetails().getSizes().getTd534x462().getSourceUrl()));
                intent.putExtra("Gambar",String.valueOf(coba.get(position).getBetterFeaturedImage().getMediaDetails().getSizes().getTd534x462().getSourceUrl()));
                intent.putExtra("Content",String.valueOf(coba.get(position).getContent().getRendered()));
                intent.putExtra("Judul",String.valueOf(coba.get(position).getTitle().getRendered()));
                intent.putExtra("Kategori",String.valueOf(coba.get(position).getCategories()));
                //Toast.makeText(v.getContext(),String.valueOf(coba.get(position).getCategories()),Toast.LENGTH_LONG).show();
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
