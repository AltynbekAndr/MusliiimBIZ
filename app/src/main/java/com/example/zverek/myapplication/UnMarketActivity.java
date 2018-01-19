package com.example.zverek.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class UnMarketActivity extends AppCompatActivity {
    String pathToShrift = "fonts/tahomabd.ttf";
    Typeface typefacen;
    ListView listViewUnMarket = null;
    CustomArrayAdapter3 customArrayAdapter3 = null;
    List<String> listUnMarket = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_market);
        typefacen = Typeface.createFromAsset(getAssets(),pathToShrift);
        String titleActivity = getIntent().getStringExtra("titleUnMarketActivity");
        TextView textView24 = (TextView) findViewById(R.id.textView24);
        textView24.setText(titleActivity);
        textView24.setTypeface(typefacen);
        listViewUnMarket = (ListView) findViewById(R.id.listViewUnMarket);
        listUnMarket = (List<String>) Arrays.asList(getResources().getStringArray(R.array.unmarket));
        customArrayAdapter3 = new CustomArrayAdapter3(UnMarketActivity.this,listUnMarket);
        listViewUnMarket.setAdapter(customArrayAdapter3);


        listViewUnMarket.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(UnMarketActivity.this,"Меню маркета",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
