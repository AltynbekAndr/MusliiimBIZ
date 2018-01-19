package com.example.zverek.myapplication;


import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Main7UnUnKuranActivity extends AppCompatActivity {
     TextView textView = null;
     ListView list = null;
    public  List<Integer> list5 = new ArrayList<Integer>(114);


    int position;
    String sss = null;
    String pathToShrift = "fonts/tahomabd.ttf";
    Typeface typefacen;
    ProgressBar progress = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.un_un_kuran_layout);
        typefacen = Typeface.createFromAsset(getAssets(),pathToShrift);

        list = (ListView) findViewById(R.id.un_kuran_list);

        textView = (TextView) findViewById(R.id.kuranTitle);
        progress = (ProgressBar) findViewById(R.id.progressBarr);
        textView.setTypeface(typefacen);
        textView.setText(""+getIntent().getStringExtra("title_kuran"));
        sss = getIntent().getStringExtra("title_kuran");

        position = getIntent().getIntExtra("position",0);
        initList5();


        new MyAsyncTask2().execute();



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
class MyAsyncTask2 extends AsyncTask<Void,Void,Void>{
    List <String>kg_list = null;
    List <String> ru_list = null;
    List <String>arab_list =null;
    CustomArrayAdapter_un_kuran customArrayAdapter_un_kuran = null;
    InputStream inputStream = null;
    InputStream inputStream2 = null;
    InputStream inputStream3 = null;

    Document d = null;
    Document d2 = null;
    Document d3 = null;
    @Override
    protected void onPreExecute() {
        kg_list = new ArrayList(list5.get(position));
        ru_list = new ArrayList(list5.get(position));
        arab_list = new ArrayList(list5.get(position));
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            inputStream = getAssets().open("kg.xml");
            inputStream2 = getAssets().open("ru.xml");
            inputStream3 = getAssets().open("arab.xml");
            d = Jsoup.parse(inputStream, "UTF-8", "");
            d2 = Jsoup.parse(inputStream2, "UTF-8", "");
            d3 = Jsoup.parse(inputStream3, "UTF-8", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = d.select("item[position="+position+"]");
        Elements elements2 = d2.select("item[position="+position+"]");
        Elements elements3 = d3.select("item[position="+position+"]");
            for(Element e : elements){
                kg_list.add(e.text());
            }
            for(Element e : elements2){
                ru_list.add(e.text());
            }
            for(Element e : elements3){
                arab_list.add(e.text());
            }
        Log.e("KG_SIZE",kg_list.size()+"");
        Log.e("RU_SIZE",ru_list.size()+"");
        Log.e("ARAB_SIZE",arab_list.size()+"");
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progress.setVisibility(progress.GONE);
        customArrayAdapter_un_kuran = new CustomArrayAdapter_un_kuran(Main7UnUnKuranActivity.this,kg_list,ru_list,arab_list);
        list.setAdapter(customArrayAdapter_un_kuran);
        list.setVisibility(list.VISIBLE);
    }
}

}
