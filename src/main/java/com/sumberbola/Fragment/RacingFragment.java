package com.sumberbola.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sumberbola.Interface.RequestF1;
import com.sumberbola.Interface.RequestGp;
import com.sumberbola.Interface.RequestRacing;
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
 * Created by ronyrahmawan on 4/5/17.
 */

public class RacingFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Coba> data;
    private MyAdapter adapter;
    private ViewPager viewPagerchild;
    private SquareLoading sqload;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    public RacingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadJSON();
    }

    public  void onStart(){
        super.onStart();
        //loadJSON();
    }

    private void loadJSON(){
        //final ProgressDialog loading = ProgressDialog.show(getActivity(),"Memuat Berita","Harap tunggu..",false,false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.sumberbola.com/wp-json/wp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestRacing request = retrofit.create(RequestRacing.class);
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
//        TabLayout tabs= (TabLayout) rootView.findViewById(R.id.tab_sub);
        // tabs.setVisibility(View.VISIBLE);
        RelativeLayout relt= (RelativeLayout) rootView.findViewById(R.id.rel_sub);
        relt.setVisibility(View.VISIBLE);
        final Button btn1= (Button) rootView.findViewById(R.id.bt_sub1);
        final Button btn2= (Button) rootView.findViewById(R.id.bt_sub2);
        btn1.setText("GP");
        btn2.setText("Formula 1");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"clicked",Toast.LENGTH_LONG).show();
                sqload.setVisibility(View.VISIBLE);
                btn1.setSelected(true);
                btn2.setSelected(false);
                btn1.setBackgroundResource(R.drawable.bg_tab_sel);
                btn2.setBackgroundResource(R.drawable.bg_tab);
                loadGP();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"clicked",Toast.LENGTH_LONG).show();
                sqload.setVisibility(View.VISIBLE);
                btn2.setSelected(true);
                btn1.setSelected(false);
                btn2.setBackgroundResource(R.drawable.bg_tab_sel);
                btn1.setBackgroundResource(R.drawable.bg_tab);
                loadF1();
            }
        });
//        viewPagerchild = (ViewPager) rootView.findViewById(R.id.viewpagers);
//        PagerAdapterchild pagerAdapterchild =
//                new PagerAdapterchild(getFragmentManager(), PrediksiFragment.this);
//        viewPagerchild.setAdapter(pagerAdapterchild);

//        tabs.setupWithViewPager(viewPagerchild);

//        for (int a = 0; a < tabs.getTabCount(); a++) {
//            TabLayout.Tab tabss = tabs.getTabAt(a);
//            tabss.setCustomView(pagerAdapterchild.getTabView(a));
//        }

        sqload = (SquareLoading) rootView.findViewById(R.id.squareLoading);
        mSwipeRefreshLayout= (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                sqload.setVisibility(View.VISIBLE);
                if(btn1.isSelected()==true){
                    loadGP();
                    mSwipeRefreshLayout.setRefreshing(false);
                }else if (btn2.isSelected()==true){
                    loadF1();
                    mSwipeRefreshLayout.setRefreshing(false);
                }else {
                    loadJSON();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });


        if (data==null) {
            sqload.setVisibility(View.VISIBLE);
            //Toast.makeText(getActivity(), "kosong", Toast.LENGTH_LONG).show();
        }else{
            sqload.setVisibility(View.INVISIBLE);
            //Toast.makeText(getActivity(), String.valueOf(adapter.getItemCount()), Toast.LENGTH_LONG).show();
        }

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        recyclerView.setHasFixedSize(true);
        //loadJSON();
        adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        return rootView;
    }




    class PagerAdapterchild extends FragmentPagerAdapter {

        String tabTitchild[] = new String[] { "Terbaru","Pilihan","Inggris"};
        PrediksiFragment context;

        public PagerAdapterchild(FragmentManager fm, PrediksiFragment context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return tabTitchild.length;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new Fragment();
                case 1:
                    return new ChildFragment();
                case 2:
                    return new Fragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitchild[position];
        }

        public View getTabView(int position) {
            View tabs = LayoutInflater.from(getActivity()).inflate(R.layout.tab_child, null);
            TextView tv = (TextView) tabs.findViewById(R.id.custom_child);
            tv.setText(tabTitchild[position]);
            return tabs;
        }

    }

    private void loadGP(){
        //final ProgressDialog loading = ProgressDialog.show(getActivity(),"Memuat Berita","Harap tunggu..",false,false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.sumberbola.com/wp-json/wp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestGp request = retrofit.create(RequestGp.class);
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

    private void loadF1(){
        //final ProgressDialog loading = ProgressDialog.show(getActivity(),"Memuat Berita","Harap tunggu..",false,false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.sumberbola.com/wp-json/wp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestF1 request = retrofit.create(RequestF1.class);
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
}
