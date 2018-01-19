package com.example.zverek.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class KuranListActivity extends AppCompatActivity {
    CustomArrayAdapter customArrayAdapter = null;
    ListView listKuran = null;
    ArrayAdapter<?> arrayAdapterKuran = null;
    ArrayAdapter<?> arrayAdapterKuran2 = null;
    ArrayAdapter<?> arrayAdapterKuran3 = null;
    public  List<Integer> list5 = new ArrayList<Integer>(114);
    String pathToShrift = "fonts/tahomabd.ttf";
    Typeface typefacen;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initList5();
        setContentView(R.layout.kuran_layout);
        listKuran = (ListView) findViewById(R.id.listKuran);
        typefacen = Typeface.createFromAsset(getAssets(),pathToShrift);
        TextView textView24 = (TextView) findViewById(R.id.textView24);
        textView24.setTypeface(typefacen);
        arrayAdapterKuran = ArrayAdapter.createFromResource(KuranListActivity.this, R.array.kuran_name_kg_arab, android.R.layout.simple_list_item_1);
        arrayAdapterKuran2 = ArrayAdapter.createFromResource(KuranListActivity.this, R.array.kuran_name_arab, android.R.layout.simple_list_item_1);
        arrayAdapterKuran3 =  ArrayAdapter.createFromResource(KuranListActivity.this, R.array.kuran_name_kg, android.R.layout.simple_list_item_1);



        customArrayAdapter = new CustomArrayAdapter(KuranListActivity.this,arrayAdapterKuran,arrayAdapterKuran2,arrayAdapterKuran3,list5);
        listKuran.setAdapter(customArrayAdapter);
        final Intent intentKuran = new Intent(KuranListActivity.this,Main7UnUnKuranActivity.class);
        listKuran.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intentKuran.putExtra("title_kuran",arrayAdapterKuran.getItem(i).toString());
                intentKuran.putExtra("position",i);
                intentKuran.putExtra("currentLength", list5.get(i).toString());

                startActivity(intentKuran);
            }
        });
    }
    void initList5(){
        list5.add(7);
        list5.add(286);
        list5.add(199);
        list5.add(176);
        list5.add(114);
        list5.add(163);
        list5.add(200);
        list5.add(75);
        list5.add(129);
        list5.add(109);
        list5.add(123);
        list5.add(111);
        list5.add(43);
        list5.add(52);
        list5.add(99);
        list5.add(128);
        list5.add(108);
        list5.add(110);
        list5.add(96);
        list5.add(135);
        list5.add(112);
        list5.add(78);
        list5.add(118);
        list5.add(64);
        list5.add(77);
        list5.add(227);
        list5.add(92);
        list5.add(87);
        list5.add(69);
        list5.add(60);
        list5.add(31);
        list5.add(30);
        list5.add(73);
        list5.add(54);
        list5.add(45);
        list5.add(83);
        list5.add(180);
        list5.add(88);
        list5.add(75);
        list5.add(85);
        list5.add(54);
        list5.add(53);
        list5.add(66);
        list5.add(57);
        list5.add(37);
        list5.add(35);
        list5.add(38);
        list5.add(29);
        list5.add(18);
        list5.add(43);
        list5.add(60);
        list5.add(49);
        list5.add(62);
        list5.add(55);
        list5.add(78);
        list5.add(96);
        list5.add(29);
        list5.add(22);
        list5.add(24);
        list5.add(13);
        list5.add(14);
        list5.add(8);
        list5.add(11);
        list5.add(18);
        list5.add(12);
        list5.add(12);
        list5.add(30);
        list5.add(52);
        list5.add(52);
        list5.add(44);
        list5.add(28);
        list5.add(28);
        list5.add(20);
        list5.add(56);
        list5.add(40);
        list5.add(31);
        list5.add(50);
        list5.add(40);
        list5.add(46);
        list5.add(42);
        list5.add(29);
        list5.add(19);
        list5.add(36);
        list5.add(25);
        list5.add(22);
        list5.add(17);
        list5.add(19);
        list5.add(26);
        list5.add(30);
        list5.add(20);
        list5.add(15);
        list5.add(21);
        list5.add(11);
        list5.add(8);
        list5.add(8);
        list5.add(19);
        list5.add(5);
        list5.add(8);
        list5.add(8);
        list5.add(11);
        list5.add(11);
        list5.add(8);
        list5.add(3);
        list5.add(9);
        list5.add(5);
        list5.add(4);
        list5.add(7);
        list5.add(3);
        list5.add(6);
        list5.add(1);
        list5.add(5);
        list5.add(4);
        list5.add(5);
        list5.add(6);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
