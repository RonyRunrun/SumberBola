package com.oktagon.sumberbola.sumberbola;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
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

import com.oktagon.sumberbola.sumberbola.Fragment.BlankFragment;
import com.oktagon.sumberbola.sumberbola.adapters.MyAdapter;
import com.oktagon.sumberbola.sumberbola.service.AndroidVersion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<AndroidVersion> data;
    private MyAdapter adapter;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initViews();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_user);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.sbicon);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter =
                new PagerAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(pagerAdapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

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
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar()
    {
        // kode untuk setupToolbar di sini
    }

    class PagerAdapter extends FragmentPagerAdapter {

        String tabTitles[] = new String[] { "Terbaru", "Inggris", "Italia", "Jerman","Lokal","Belanda","Prediksi"};
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
                    return new BlankFragment();
                case 2:
                    return new BlankFragment();
                case 3:
                    return new BlankFragment();
                case 4:
                    return new BlankFragment();
                case 5:
                    return new BlankFragment();
                case 6:
                    return new BlankFragment();
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
}