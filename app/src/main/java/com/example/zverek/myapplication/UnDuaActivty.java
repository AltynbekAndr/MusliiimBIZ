package com.example.zverek.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;



public class UnDuaActivty extends AppCompatActivity {
    String pathToShrift = "fonts/tahomabd.ttf";
    Typeface typefacen;
    String pathToShrift2 = "fonts/calibri.ttf";
    Typeface typefacen2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_alert_dua);
        TextView txttt  = (TextView) findViewById(R.id.textView19);
        TextView txttt2  = (TextView) findViewById(R.id.textView20);
        txttt.setText(getIntent().getStringExtra("listDua"));
        txttt2.setText(getIntent().getStringExtra("listDuaText"));
        typefacen = Typeface.createFromAsset(getAssets(),pathToShrift);
        typefacen2 = Typeface.createFromAsset(getAssets(),pathToShrift2);

        txttt2.setTypeface(typefacen2);
        txttt.setTypeface(typefacen);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
