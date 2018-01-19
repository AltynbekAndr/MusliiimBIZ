package com.example.zverek.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zverek.myapplication.services.NamazTimeNotificationService;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    LinearLayout mainLinearContainer = null;
    LayoutInflater inflater =  null;
/////////////////////////
    Intent intent = null;
/////////////////////////
    Toolbar toolbar = null;
    CoordinatorLayout coordinatorLayout = null;
    /////////////////////////////
    TextView name_user = null;
    /////////////////////////////
    String cityStr = "1";
    CircleImageView circleImageView = null;
    TextView t0 = null;
    TextView t1 = null;
    TextView t2 = null;
    TextView t3 = null;
    TextView t4 = null;
    TextView t5 = null;
    TextView ttttt = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if (savedInstanceState != null) {
            finish();
        }
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mainLinearContainer =  (LinearLayout) findViewById(R.id.mainLinear);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(openFileInput("drawable.txt"),"UTF-8"));
            cityStr = bf.readLine();
        } catch (FileNotFoundException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
        if(cityStr.equals("1")){
            Drawable drawable = getResources().getDrawable(R.drawable.bg1);
            coordinatorLayout.setBackground(drawable);
        }if(cityStr.equals("2")){
            Drawable drawable = getResources().getDrawable(R.drawable.bg2);
            coordinatorLayout.setBackground(drawable);
        }if(cityStr.equals("3")){
            Drawable drawable = getResources().getDrawable(R.drawable.bg3);
            coordinatorLayout.setBackground(drawable);
        }if(cityStr.equals("4")){
            Drawable drawable = getResources().getDrawable(R.drawable.bg4);
            coordinatorLayout.setBackground(drawable);
        }if(cityStr.equals("5")){
            Drawable drawable = getResources().getDrawable(R.drawable.bg5);
            coordinatorLayout.setBackground(drawable);
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Main2Activity.this,ProphileActivity.class);
                startActivity(intent);

            }
        });
         t0 = (TextView) findViewById(R.id.tx0);
         t1 = (TextView) findViewById(R.id.tx1);
         t2 = (TextView) findViewById(R.id.tx2);
         t3 = (TextView) findViewById(R.id.tx3);
         t4 = (TextView) findViewById(R.id.tx4);
         t5 = (TextView) findViewById(R.id.tx5);
         ttttt = (TextView) findViewById(R.id.txxx);

        try{
            BufferedReader bufferedReaderName = null;
            InputStream inputStream = new FileInputStream(getFilesDir()+"/my_img_for_icon/img_user.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            circleImageView = header.findViewById(R.id.profile_image3);
            name_user = header.findViewById(R.id.name_user);
            circleImageView.setImageBitmap(bitmap);
            bufferedReaderName = new BufferedReader(new InputStreamReader(getApplicationContext().openFileInput("name.txt"), "UTF-8"));
            String userName = bufferedReaderName.readLine();
            bufferedReaderName.close();
            inputStream.close();
            name_user.setText(userName);

        }catch (Exception e){
            e.printStackTrace();
        }
        //startService(new Intent(Main2Activity.this,NamazTimeNotificationService.class));
        BufferedReader bufferedReaderCity = null;

        try {
            bufferedReaderCity = new BufferedReader(new InputStreamReader(getApplicationContext().openFileInput("cityname.txt"), "UTF-8"));
            String cityName = bufferedReaderCity.readLine();
            getSupportActionBar().setTitle(cityName);
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }finally {
            try {
                bufferedReaderCity.close();
            } catch (IOException e) {
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        new HandlerAsyncTimeNamaz().execute();
        BufferedReader bufferedReaderCity = null;
        BufferedReader bufferedReaderName = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(getFilesDir()+"/my_img_for_icon/img_user.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        try {
            bufferedReaderCity = new BufferedReader(new InputStreamReader(getApplicationContext().openFileInput("cityname.txt"), "UTF-8"));
            bufferedReaderName = new BufferedReader(new InputStreamReader(getApplicationContext().openFileInput("name.txt"), "UTF-8"));
            String cityName = bufferedReaderCity.readLine();
            String userName = bufferedReaderName.readLine();
            getSupportActionBar().setTitle(cityName);
            if(userName!=null&&userName!=""&&userName.length()!=0){
                name_user.setText(userName);
            }
            circleImageView.setImageBitmap(bitmap);
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }finally {
            try {
                bufferedReaderCity.close();
            } catch (IOException e) {
            }
        }
    }
    private class HandlerAsyncTimeNamaz extends AsyncTask<Void,Void,Void> {
    JSONObject dayJs = null;
    Date date = null;
    String path =  null;
    String json =  null;
    String hour = null;
    String min = null;

        @Override
        protected void onPreExecute() {
            date = new Date();
            hour = (String) DateFormat.format("kk", date);
            min = (String) DateFormat.format("mm", date);
            ttttt.setText(hour+":" +min);
        }

        @Override
    protected Void doInBackground(Void... voids) {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(getApplicationContext().openFileInput("path.txt"), "UTF-8"));
            path = bf.readLine();
            InputStream is = getApplicationContext().getAssets().open(path + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            json = new String(buffer, "UTF-8");
            is.close();
            String monthNumber = (String) DateFormat.format("M", date);
            String day = (String) DateFormat.format("d", date);
            JSONObject year = new JSONObject(json);
            JSONObject months = year.getJSONObject("month" + monthNumber);
            dayJs = months.getJSONObject("day_" + day);

        }catch (Exception e){
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        try{
        t0.setText(dayJs.getString("bagymdat"));
        t1.setText(dayJs.getString("kundun_chyguusu"));
        t2.setText(dayJs.getString("beshim"));
        t3.setText(dayJs.getString("asr"));
        t4.setText(dayJs.getString("sham"));
        t5.setText(dayJs.getString("kuptan"));
        ttttt.setText(hour+":" +min);
    }catch (Exception e){
    }
    }
}

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mecheti_and_nk) {
            intent = new Intent(Main2Activity.this,MechetiNamazKanaActivity.class);
            startActivity(intent);
            intent = null;
        } else if(id == R.id.dua) {
            intent = new Intent(this,DuaActivity.class);
            startActivity(intent);
            intent = null;
        } else if (id == R.id.kuran) {
            intent = new Intent(this,KuranListActivity.class);
            startActivity(intent);
            intent = null;
        } else if (id == R.id.market) {
            intent = new Intent(this,MarketActivity.class);
            startActivity(intent);
            intent = null;
        } else if(id == R.id.news){
            intent = new Intent(Main2Activity.this,NewsActivity.class);
            startActivity(intent);
            intent = null;
        } else if (id == R.id.my_media) {
            intent = new Intent(Main2Activity.this,MediaActivity.class);
            startActivity(intent);
            intent = null;
        } else if (id == R.id.reklama) {
            intent = new Intent(Main2Activity.this,ReclamaActivity.class);
            startActivity(intent);
            intent = null;
        } else if (id == R.id.namaz) {
            intent = new Intent(Main2Activity.this,NamazOkuuActivity.class);
            startActivity(intent);
            intent = null;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
            try {
                BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(openFileOutput("drawable.txt",MODE_PRIVATE),"UTF-8"));
                if(cityStr.equals("1")){
                    bf.write("2");
                }
                if(cityStr.equals("2")){
                    bf.write("3");
                }
                if(cityStr.equals("3")){
                    bf.write("4");
                }
                if(cityStr.equals("4")){
                    bf.write("5");
                }
                if(cityStr.equals("5")){
                    bf.write("1");
                }
                bf.close();
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main2, menu);
        //MenuItem searchItem = menu.findItem(R.id.action_search);
        // SearchView searchView = (SearchView) MenuItemCompat
        //    .getActionView(searchItem);
        /*SearchView searchView = (SearchView) searchItem.getActionView();
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    // do something with s, the entered string
                    String query = s;
                    Toast.makeText(getApplicationContext(),
                            "String entered is " + s, Toast.LENGTH_SHORT).show();
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }*/
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            intent = new Intent(Main2Activity.this,ProphileActivity.class);
            startActivity(intent);
            intent = null;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
