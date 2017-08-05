package com.sumberbola;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by ronyrahmawan on 4/20/17.
 */

public class AboutApp extends AppCompatActivity {

    private TextView verapp;
    private TextView tig;
    private TextView ttw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_menu);
        verapp = (TextView) findViewById(R.id.tv_verapp);
        tig = (TextView) findViewById(R.id.tv_ig);
        ttw = (TextView) findViewById(R.id.tv_tw);
        tig.setText("@sumberbola");
        ttw.setText("@info_sumberbola");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarab);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About");
        verapp.setText(String.valueOf(BuildConfig.VERSION_NAME)+" ("+String.valueOf(BuildConfig.VERSION_CODE)+")");

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