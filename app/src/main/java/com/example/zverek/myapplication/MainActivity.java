package com.example.zverek.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        String path = getFilesDir().getAbsolutePath();
        File file = new File(path+"sound.txt");
        if(file.exists()){
        }else{
            try {
                BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(openFileOutput("sound.txt", MODE_PRIVATE), "UTF-8"));
                bf.write("Звук");
                bf.close();
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
        }
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(openFileInput("name.txt"),"UTF-8"));
            BufferedReader bf2 = new BufferedReader(new InputStreamReader(openFileInput("password.txt"),"UTF-8"));
            BufferedReader bf3 = new BufferedReader(new InputStreamReader(openFileInput("cityname.txt"),"UTF-8"));
            String name = bf.readLine();
            String password = bf2.readLine();
            String cityname = bf3.readLine();
            bf.close();bf2.close();
            if(name!=null&&password!=null&&cityname!=null){
                t.start();
            }else{
                t2.start();
            }
        } catch (FileNotFoundException e) {
            t2.start();
        } catch (IOException e) {
            t2.start();
        }
    }

    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            for(int i =0;i<3;i++){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    });
    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            for(int i =0;i<3;i++){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            Intent intent = new Intent(getApplicationContext(),Main3SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    });

}
