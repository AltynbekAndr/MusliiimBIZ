package com.example.zverek.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class DuaActivity extends AppCompatActivity {
    CustomArrayAdapter3 customArrayAdapter3 = null;
    List<String> listDua = null;
    List<String> listDuaText = null;
    ListView listViewDua = null;
    Intent intent = null;
    String pathToShrift = "fonts/tahomabd.ttf";
    Typeface typefacen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dua_layout);
        listViewDua = (ListView) findViewById(R.id.listDua);
        typefacen = Typeface.createFromAsset(getAssets(),pathToShrift);
        TextView textView24 = (TextView) findViewById(R.id.textView24);
        textView24.setTypeface(typefacen);

        listDua = (List<String>) Arrays.asList(getResources().getStringArray(R.array.dua_title));
        listDuaText = (List<String>) Arrays.asList(getResources().getStringArray(R.array.dua_text));
        customArrayAdapter3 = new CustomArrayAdapter3(DuaActivity.this,listDua);
        listViewDua.setAdapter(customArrayAdapter3);


        listViewDua.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(DuaActivity.this,UnDuaActivty.class);
                intent.putExtra("listDua",listDua.get(i));
                intent.putExtra("listDuaText",listDuaText.get(i));
                startActivity(intent);
                intent = null;

            }
        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
