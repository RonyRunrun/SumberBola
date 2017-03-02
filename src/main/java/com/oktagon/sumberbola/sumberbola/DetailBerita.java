package com.oktagon.sumberbola.sumberbola;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Rony R on 3/2/2017.
 */

public class DetailBerita extends AppCompatActivity {

    private TextView t_judul;
    private TextView t_tgl;
    private TextView t_isi;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.isi_berita);

        //insialisasi konten

        t_judul = (TextView) findViewById(R.id.tv_judul);
        t_tgl = (TextView) findViewById(R.id.tv_tgl_post);
        t_isi = (TextView) findViewById(R.id.tv_konten);


    }
}
