package com.sumberbola;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Rony R on 3/15/2017.
 */

public class OpenPost extends AppCompatActivity {

    private TextView tes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_post);
        tes = (TextView) findViewById(R.id.tv_tes);

    }
}