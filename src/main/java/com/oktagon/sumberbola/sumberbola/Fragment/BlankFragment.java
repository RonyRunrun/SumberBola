package com.oktagon.sumberbola.sumberbola.Fragment;

/**
 * Created by RR_PC on 2/19/2017.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oktagon.sumberbola.sumberbola.Interface.RequestInterface;
import com.oktagon.sumberbola.sumberbola.R;
import com.oktagon.sumberbola.sumberbola.adapters.MyAdapter;
import com.oktagon.sumberbola.sumberbola.service.AndroidVersion;
import com.oktagon.sumberbola.sumberbola.service.JSONResponse;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

;

public class BlankFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<AndroidVersion> data;
    private MyAdapter adapter;

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    loadJSON();
    }


    private void loadJSON(){
        final ProgressDialog loading = ProgressDialog.show(getActivity(),"Memuat Berita","Harap Tunggu Sebentar..",false,false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.learn2crack.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                String ds=jsonResponse.getAndroid().toString();
                System.out.println(ds);
                //Log.e("Error",ds);
                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
//                adapter = new MyAdapter(data);
                recyclerView.setAdapter(adapter);
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
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
        recyclerView.setHasFixedSize(true);
        loadJSON();
//        adapter = new MyAdapter(data);
//        recyclerView.setAdapter(adapter);
//        MyAdapter adapter = new MyAdapter(new ArrayList<AndroidVersion>());
//        recyclerView.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        return rootView;
    }



}