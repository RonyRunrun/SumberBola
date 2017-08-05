package com.sumberbola.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sumberbola.Interface.RequestPrancis;
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
 * Created by Rony R on 3/21/2017.
 */

public class PrancisFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Coba> data;
    private MyAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SquareLoading sqload;


    public PrancisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadJSON();
    }


    private void loadJSON(){
        //final ProgressDialog loading = ProgressDialog.show(getActivity(),"Memuat Berita","Harap tunggu..",false,false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.sumberbola.com/wp-json/wp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestPrancis request = retrofit.create(RequestPrancis.class);
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
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        sqload = (SquareLoading) rootView.findViewById(R.id.squareLoading);

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


        if (data==null) {
            sqload.setVisibility(View.VISIBLE);
            //Toast.makeText(getActivity(), "kosong", Toast.LENGTH_LONG).show();
        }else{
            sqload.setVisibility(View.INVISIBLE);
            //Toast.makeText(getActivity(), String.valueOf(adapter.getItemCount()), Toast.LENGTH_LONG).show();
        }

        recyclerView.setHasFixedSize(true);
        //loadJSON();
        adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        return rootView;
    }

}