package com.sumberbola;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sumberbola.adapters.ListAdapter;
import com.sumberbola.adapters.PredAdapter;
import com.sumberbola.service.Coba;
import com.sumberbola.utility.TextViewHandler;
import com.sumberbola.utility.UlTagHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.github.yuweiguocn.lib.squareloading.SquareLoading;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Rony R on 3/2/2017.
 */

public class DetailBerita extends AppCompatActivity{

    private ImageView iv_big;
    private TextView t_judul;
    private TextView t_tgl;
    private TextView t_isi;
    private TextView t_terkait;
    private ListAdapter adapter;
    private PredAdapter adapterpred;
    private ListView lv_terkait;
    private FloatingActionButton fb_share;
    private static Context mContext;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewpred;
    private ShareActionProvider mShareActionProvider;
    private ArrayList<Coba> data;
    private ArrayList<Coba> datapred;
    private TextView tit;
    private SquareLoading sqload;
    private  String ab;
    static String sl;

    public DetailBerita() {
        // Required empty public constructor
    }

    public static Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.isi_berita);
        mContext=getApplicationContext();
        //insialisasi konten
        iv_big = (ImageView) findViewById(R.id.iv_head);
        fb_share = (FloatingActionButton) findViewById(R.id.fb_share);
        t_judul = (TextView) findViewById(R.id.tv_judul);
        t_tgl = (TextView) findViewById(R.id.tv_tgl_post);
        t_isi = (TextView) findViewById(R.id.tv_konten);
        t_terkait = (TextView) findViewById(R.id.terkait);
        sqload = (SquareLoading) findViewById(R.id.squareload);
        tit = (TextView) findViewById(R.id.tit_ab);

        final Intent mIntent = getIntent();
        ab = mIntent.getStringExtra("Kategori");
        ab.trim();
        //ab =ab.substring(1,ab.length()-1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbardet);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SetTit();
        //Toast.makeText(getContext(),ab,Toast.LENGTH_LONG).show();

        //Format tanggal
        Locale locale= new Locale("id","ID");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("dd-MMM-yyyy",locale);
        SimpleDateFormat destFormat = new SimpleDateFormat("EEEE, dd-MMM-yyyy",locale); //here 'a' for AM/PM
        Date date = null;
        try {
            date = sourceFormat.parse(mIntent.getStringExtra("Tanggal"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final String formattedDate = destFormat.format(date);

        //panggil data intent
        String ads=getString(R.string.adsgo);
        String kn= mIntent.getStringExtra("Content");
        kn= kn.replace("(adsbygoogle = window.adsbygoogle || []).push({});","");
//        kn= kn.replace("<ul>","<div style=\"\n" +
//                "    border: 1px solid;\n" +
//                "    padding: 5px;\n" +
//                "    list-style: none;\n" +
//                "    background: #e6e6e6;\n" +
//                "\">");
       //kn= kn.replace("Baca juga:","Baca juga:" +
       //        "<br> ----------------------------------------------- </br> ");
        t_isi.setText(Html.fromHtml(kn,null,new UlTagHandler()));
        //Toast.makeText(t_isi.getContext(),kn,Toast.LENGTH_LONG).show();
        //set klik link ke activity baru
        t_isi.setMovementMethod(new TextViewHandler() {
            @Override
            public void onLinkClick(String url) {
                sl= url;
                sl= sl.substring(26,sl.length()-1);
                sqload.setVisibility(View.VISIBLE);
                //Toast.makeText(t_isi.getContext(), sl, Toast.LENGTH_SHORT).show();
                //final ProgressDialog loading = ProgressDialog.show(t_isi.getContext(),"Memuat Berita","Harap tunggu..",false,false);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://www.sumberbola.com/wp-json/wp/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RequestRela request = retrofit.create(RequestRela.class);
                Call<List<Coba>> call = request.getPostDet("v2/posts?fields=id,slug,title.rendered,better_featured_image.media_details.sizes,date,content.rendered,link,tags&slug="+sl);
                call.enqueue(new Callback<List<Coba>>() {
                    @Override
                    public void onResponse(Call<List<Coba>> call, Response<List<Coba>> response) {
                        try {
                            List<Coba> jsonResponse = response.body();
                            data= new ArrayList<>();
                            data.addAll(jsonResponse);
                            //loading.dismiss();
                            sqload.setVisibility(View.INVISIBLE);
                            adapter = new ListAdapter(data);
                            adapter.notifyDataSetChanged();
                            //recyclerView.setAdapter(adapter);
                            System.out.println(data.get(0).getTitle().getRendered());
                            Log.i("HASIL", "onResponse: "+data);
                            Locale locale= new Locale("id","ID");
                            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            SimpleDateFormat destFormat = new SimpleDateFormat("dd-MMM-yyyy",locale); //here 'a' for AM/PM
                            Date date = null;
                            try {
                                date = sourceFormat.parse(String.valueOf(data.get(0).getDate()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            final String formattedDate = destFormat.format(date);
                            //Toast.makeText(t_isi.getContext(), data.get(0).getTitle().getRendered(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getContext(),DetailBerita.class);

                            intent.putExtra("Gambargede",String.valueOf(data.get(0).getBetterFeaturedImage().getMediaDetails().getSizes().getTd534x462().getSourceUrl()));
                            intent.putExtra("Gambar",String.valueOf(data.get(0).getBetterFeaturedImage().getMediaDetails().getSizes().getTd534x462().getSourceUrl()));
                            intent.putExtra("Content",String.valueOf(data.get(0).getContent().getRendered()));
                            intent.putExtra("Judul",String.valueOf(data.get(0).getTitle().getRendered()));
                            intent.putExtra("Kategori",String.valueOf(data.get(0).getCategories()));
                            intent.putExtra("Tanggal",formattedDate);
                            intent.putExtra("Link",String.valueOf(data.get(0).getLink()));
                            intent.putExtra("Tags",String.valueOf(data.get(0).getTags()));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getContext().startActivity(intent);
                        }catch (Exception e) {
                            Log.d("onResponse", "There is an error");
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Coba>> call, Throwable t) {
                        Log.d("Error",t.getMessage());
                    }
                });
            }
        });

        t_tgl.setText(formattedDate);
        String mm= mIntent.getStringExtra("Tags");
        mm = mm.substring(0,mm.length()-1);
        t_judul.setText(Html.fromHtml(mIntent.getStringExtra("Judul")));
        //t_terkait.setText(mm.substring(1,mm.length()));
        initViews();
        initViewsPred();
        //load big image
        if (mIntent.getStringExtra("Gambargede")!=null) {
            Glide.with(mContext).load(mIntent.getStringExtra("Gambargede"))
                    .thumbnail(0.3f)
                    //.override(400,400)
                    .crossFade()
                    // .fitCenter()
                    .animate(R.anim.anim_head)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iv_big);
        }else{
            Glide.with(mContext).load(mIntent.getStringExtra("Gambar"))
                    .thumbnail(0.3f)
                    //.override(400,400)
                    .crossFade()
                    // .fitCenter()
                    .animate(R.anim.anim_head)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iv_big);
        }
        fb_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String ShareBody = (mIntent.getStringExtra("Link"));
                intent.putExtra(Intent.EXTRA_TEXT,ShareBody);
                startActivity(Intent.createChooser(intent, "Share via"));
            }
        });

    }

    private  void SetTit(){
        if (ab.equals("[85]")) {
            tit.setText("Inggris");
        }else if (ab.equals("[93]")){
            tit.setText("Spanyol");
        }else if (ab.equals("[88]")){
            tit.setText("Indonesia");
        }else if (ab.equals("[86]")){
            tit.setText("Jerman");
        }else if (ab.equals("[84]")){
            tit.setText("Spanyol");
        }else if (ab.equals("[2107]")){
            tit.setText("Prancis");
        }else if (ab.equals("[3509]")){
            tit.setText("Prediksi");
        }else if (ab.equals("[3056]")) {
            tit.setText("Pilihan");
        }else if (ab.equals("[1271]")){
            tit.setText("Transfer");
        }else {
            tit.setText("SumberBola");
        }
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.rv_det);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        loadJSON();
        adapter = new ListAdapter(data);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initViewsPred(){
        recyclerViewpred = (RecyclerView)findViewById(R.id.rv_pred);
        recyclerViewpred.setHasFixedSize(true);
        recyclerViewpred.setNestedScrollingEnabled(false);
        loadJSONlink();
        adapterpred = new PredAdapter(datapred);
        recyclerViewpred.setAdapter(adapterpred);
        LinearLayoutManager layoutManagerpred = new LinearLayoutManager(getApplicationContext());
        recyclerViewpred.setLayoutManager(layoutManagerpred);
    }

    //request artikel terkait
    public interface RequestRela {
        @GET
        Call<List<Coba>> getPostDet(@Url String url);
    }
    //load data artikel terkait
    private void loadJSON(){
        final Intent inten=new Intent(getIntent());
        String sa = inten.getStringExtra("Tags");
        sa = sa.substring(0,sa.length()-1);
        String ak=sa.substring(1,sa.length());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.sumberbola.com/wp-json/wp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestRela request = retrofit.create(RequestRela.class);
        Call<List<Coba>> call = request.getPostDet("v2/posts?fields=id,slug,title.rendered,better_featured_image.media_details.sizes,date,content.rendered,link,tags&categories_exclude=5315&per_page=5&tags="+ak);
        call.enqueue(new Callback<List<Coba>>() {
            @Override
            public void onResponse(Call<List<Coba>> call, Response<List<Coba>> response) {
                try {
                    List<Coba> jsonResponse = response.body();
                    data= new ArrayList<>();
                    data.addAll(jsonResponse);
                    adapter = new ListAdapter(data);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                    System.out.println("GAMBARL :" +jsonResponse.get(0).getBetterFeaturedImage().getMediaDetails().getSizes().getLarge().getSourceUrl());
                    Log.i("HASILKU", "onResponse: "+jsonResponse.get(0).getBetterFeaturedImage().getMediaDetails().getSizes().getTd534x462().getSourceUrl());
                }catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Coba>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    //Request Prediksi
    private void loadJSONlink(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.sumberbola.com/wp-json/wp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestRela request = retrofit.create(RequestRela.class);
        Call<List<Coba>> call = request.getPostDet("v2/posts?fields=id,slug,title.rendered,better_featured_image.media_details.sizes.td_534x462.source_url,date,content.rendered,link&categories=3509&per_page=5");
        call.enqueue(new Callback<List<Coba>>() {
            @Override
            public void onResponse(Call<List<Coba>> call, Response<List<Coba>> response) {
                try {
                    List<Coba> jsonResponse = response.body();
                    datapred= new ArrayList<>();
                    datapred.addAll(jsonResponse);
                    adapterpred = new PredAdapter(datapred);
                    adapterpred.notifyDataSetChanged();
                    recyclerViewpred.setAdapter(adapterpred);
                    System.out.println("GAMBAR :"+jsonResponse.get(0).getBetterFeaturedImage().getMediaDetails().getSizes().getTd534x462().getSourceUrl());
                    Log.i("HASIL", "onResponse: "+datapred);
                }catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Coba>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
