package com.example.zverek.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;


public class MarketActivity extends AppCompatActivity {
    String pathToShrift = "fonts/tahomabd.ttf";
    Typeface typefacen;
    ListView listViewMarket = null;
    CustomArrayAdapter3 customArrayAdapter3 = null;
    List<String> listMarket = null;
    Intent intent = null;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_layout);
        typefacen = Typeface.createFromAsset(getAssets(),pathToShrift);
        TextView textView24 = (TextView) findViewById(R.id.textView24);
        textView24.setTypeface(typefacen);
        listViewMarket = (ListView) findViewById(R.id.listViewMarket);
        listMarket = (List<String>) Arrays.asList(getResources().getStringArray(R.array.market));
        customArrayAdapter3 = new CustomArrayAdapter3(MarketActivity.this,listMarket);
        listViewMarket.setAdapter(customArrayAdapter3);
        listViewMarket.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(MarketActivity.this,UnMarketActivity.class);
                intent.putExtra("titleUnMarketActivity",listMarket.get(i));
                startActivity(intent);
                intent = null;

            }
        });
    }


}
