package com.oktagon.sumberbola.sumberbola.Fragment;

/**
 * Created by RR_PC on 2/19/2017.
 */

//import android.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oktagon.sumberbola.sumberbola.Interface.RequestSumbol;
import com.oktagon.sumberbola.sumberbola.R;
import com.oktagon.sumberbola.sumberbola.adapters.MyAdapter;
import com.oktagon.sumberbola.sumberbola.service.Post;
import com.oktagon.sumberbola.sumberbola.service.PostWp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

;

public class EditorFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<List<PostWp>> data;
    private MyAdapter adapter;

    public EditorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 //       loadJSON();
    }


    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.sumberbola.com/wp-json/wp")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestSumbol request = retrofit.create(RequestSumbol.class);
        Call<List<Post>> call = request.getPostDet();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                    List<Post> jsonResponse = response.body();
                        String ss = jsonResponse.toString();
                        System.out.println(ss);
                        Log.e("Test", ss);
                   //     data = new ArrayList<>(Arrays.asList(jsonResponse));

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
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
        //adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        return rootView;
    }



}