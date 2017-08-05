package com.sumberbola;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sumberbola.Fragment.BlankFragment;
import com.sumberbola.Fragment.IndonesiaFragment;
import com.sumberbola.Fragment.InggrisFragment;
import com.sumberbola.Fragment.ItalyFragment;
import com.sumberbola.Fragment.JermanFragment;
import com.sumberbola.Fragment.KlasmenFragment;
import com.sumberbola.Fragment.PrancisFragment;
import com.sumberbola.Fragment.PrediksiFragment;
import com.sumberbola.Fragment.RacingFragment;
import com.sumberbola.Fragment.SpanyolFragment;
import com.sumberbola.Fragment.TransferFragment;
import com.sumberbola.adapters.MyAdapter;
import com.sumberbola.service.AndroidVersion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<AndroidVersion> data;
    private MyAdapter adapter;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    public TextView tas;
    public TextView mdet;
    private Toolbar toolbar;
    private  ViewPager viewPager;
    private  ViewPager viewPagerchild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initViews();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_user);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.sblogo);
        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.layout_ab);
        getSupportActionBar().setTitle(null);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter =
                new PagerAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(pagerAdapter);

//        viewPagerchild = (ViewPager) findViewById(viewpagerc);
//        PagerAdapterchild pagerAdapterchild =
//                new PagerAdapterchild(getSupportFragmentManager(), MainActivity.this);
//        viewPagerchild.setAdapter(pagerAdapterchild);

        //declare text
        //tas = (TextView) findViewById(R.id.tv_atas);
        //mdet = (TextView) findViewById(R.id.tv_blah);
        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

//        final TabLayout tabkecil = (TabLayout) findViewById(R.id.tab_child);
//        tabkecil.setSelected(false);
//        tabkecil.setupWithViewPager(viewPagerchild);

//        //navigation menu
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        navView = (NavigationView) findViewById(R.id.navigation);
//        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                // cek apakah menuItem sudah diklik (checked) atau tidak
//                if(menuItem.isChecked())
//                    menuItem.setChecked(false);
//                else
//                    menuItem.setChecked(true);
//
//                // menutup drawer ketika menuItem diklik
//                drawerLayout.closeDrawers();
//
//                switch (menuItem.getItemId()){
//                    //kode handle untuk tiap-tiap menu item
//                    case R.id.nav_home:
//                        Toast.makeText(MainActivity.this, "Home clicked", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.nav_my_profile:
//                        Toast.makeText(MainActivity.this, "My profile clicked", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.nav_settings:
//                        Toast.makeText(MainActivity.this, "Settings clicked", Toast.LENGTH_SHORT).show();
//                        return true;
//
//                }
//                return true;
//            }
//        });


        // Iterate over all tabs and set the custom view

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }

//        for (int a = 0; a < tabkecil.getTabCount(); a++) {
//            TabLayout.Tab tabs = tabkecil.getTabAt(a);
//            tabs.setCustomView(pagerAdapterchild.getTabView(a));
//        }


        final String[] tabsTitles = {"Terbaru","Pilihan", "Prediksi", "Inggris", "Spanyol","Italy","Jerman","Prancis","Indonesia"};
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
//                if (position==2) {
//                    tabkecil.setVisibility(View.VISIBLE);
//                }else {
//                    tabkecil.setVisibility(View.GONE);
//                }
               // mdet.setText(tabsTitles[position]);
                //tas.setText(tabsTitles[position]);
               // toolbar.setTitle(tabsTitles[position]);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int pos) {
                // TODO Auto-generated method stub

            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // menghandle ketika tombol home diklik, Navigation View akan terbuka
            case R.id.action_about:
                Intent intent = new Intent(getApplication(),AboutApp.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupToolbar()
    {
        // kode untuk setupToolbar di sini
    }

    class PagerAdapter extends FragmentPagerAdapter {

        String tabTitles[] = new String[] { "Terbaru", "Prediksi","Transfer", "Inggris", "Spanyol","Italy"
                ,"Jerman","Prancis","Indonesia","Klasmen","Racing"};
        Context context;

        public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new BlankFragment();
                case 1:
                    return new PrediksiFragment();
                case 2:
                    return new TransferFragment();
                case 3:
                    return new InggrisFragment();
                case 4:
                    return new SpanyolFragment();
                case 5:
                    return new ItalyFragment();
                case 6:
                    return new JermanFragment();
                case 7:
                    return new PrancisFragment();
                case 8:
                    return new IndonesiaFragment();
                case 9:
                    return new KlasmenFragment();
                case 10:
                    return new RacingFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }

        public View getTabView(int position) {
            View tab = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab, null);
            TextView tv = (TextView) tab.findViewById(R.id.custom_text);
            tv.setText(tabTitles[position]);
            return tab;
        }
        
    }

//    class PagerAdapterchild extends FragmentPagerAdapter {
//
//        String tabTitchild[] = new String[] { "Terbaru","Pilihan","Inggris"};
//        Context context;
//
//        public PagerAdapterchild(FragmentManager fm, Context context) {
//            super(fm);
//            this.context = context;
//        }
//
//        @Override
//        public int getCount() {
//            return tabTitchild.length;
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//
//            switch (position) {
//                case 0:
//                    return new Fragment();
//                case 1:
//                    return new ChildFragment();
//                case 2:
//                    return new Fragment();
//                               }
//            return null;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            // Generate title based on item position
//            return tabTitchild[position];
//        }
//
//        public View getTabView(int position) {
//            View tabs = LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_child, null);
//            TextView tv = (TextView) tabs.findViewById(R.id.custom_child);
//            tv.setText(tabTitchild[position]);
//            return tabs;
//        }
//
//    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Klik BACK lagi untuk keluar Aplikasi.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }

}