package com.sumberbola.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sumberbola.Interface.RequestJerman;
import com.sumberbola.R;
import com.sumberbola.adapters.MyAdapter;
import com.sumberbola.service.Coba;

import java.util.ArrayList;
import java.util.List;

import io.github.yuweiguocn.lib.squareloading.SquareLoading;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rony R on 3/13/2017.
 */

public class KlasmenFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Coba> data;
    private MyAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SquareLoading sqload;
    private WebView webView;

    public KlasmenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //loadJSON();
    }


    private void loadJSON(){
        //final ProgressDialog loading = ProgressDialog.show(getActivity(),"Memuat Berita","Harap tunggu..",false,false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.sumberbola.com/wp-json/wp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestJerman request = retrofit.create(RequestJerman.class);
        Call<List<Coba>> call = request.getPostDet();
        call.enqueue(new Callback<List<Coba>>() {
            @Override
            public void onResponse(Call<List<Coba>> call, Response<List<Coba>> response) {
                try {
                    List<Coba> jsonResponse = response.body();
                    data= new ArrayList<>();
                    data.addAll(jsonResponse);
                    adapter = new MyAdapter(data);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                    sqload.setVisibility(View.INVISIBLE);
                    //loading.dismiss();
                    Log.i("HASIL", "onResponse: "+data);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_web, container, false);
       // ScrollView scrollView = (ScrollView) rootView.findViewById(R.id.scr);
        webView = (WebView) rootView.findViewById(R.id.wv_klas);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.loadUrl("http://www.btfstats.com/widgets/leagues-table-small.php?name=England%3APremier+League&type=1");
        webView.setWebViewClient(new MyBrowser());

        sqload = (SquareLoading) rootView.findViewById(R.id.squareLoading);
        sqload.setVisibility(View.INVISIBLE);
        mSwipeRefreshLayout= (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                sqload.setVisibility(View.VISIBLE);
                loadJSON();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        return rootView;
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            webView.loadUrl(url);
            return true;
        }
    }
}