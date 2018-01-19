package com.example.zverek.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zverek.myapplication.services.SingletonOkHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ReclamaActivity extends AppCompatActivity {
    Spinner spinner = null;
    TextView txtState = null;
    ArrayAdapter<?> arrayAdapterM = null;
    ImageButton imgBtnAnimal = null;
    BitmapFactory.Options options = null;
    Bitmap bitmap = null;
    InputStream input = null;
    Intent intent =null;
    final int Pic_image=2;
    ImageView img3;
    String pathToShrift = "fonts/tahomabd.ttf";
    Typeface typefacen;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reklama);
        if (savedInstanceState != null) {
            finish();
        }
        spinner = findViewById(R.id.spinner);
        txtState  = findViewById(R.id.textView18);
        options = new BitmapFactory.Options();
        img3 = (ImageView) findViewById(R.id.imageView4);
        spinner = findViewById(R.id.spinner);
        txtState  = findViewById(R.id.state0);
        typefacen = Typeface.createFromAsset(getAssets(),pathToShrift);
        TextView textView24 = (TextView) findViewById(R.id.textView18);
        textView24.setTypeface(typefacen);
        arrayAdapterM = ArrayAdapter.createFromResource(ReclamaActivity.this, R.array.state, android.R.layout.simple_list_item_1);


        arrayAdapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapterM);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stat = String.valueOf(arrayAdapterM.getItem(i));
                txtState.setText("" + stat);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
    public void selectImageAnimal(View view){
        imgBtnAnimal = (ImageButton) findViewById(R.id.imageButton);
        intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, Pic_image);
        intent = null;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch(requestCode) {
            case Pic_image:
                if(resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    uriToSend = uri;
                    try {
                        input = getContentResolver().openInputStream(uri);

                        options.inSampleSize = 8;
                        bitmap = BitmapFactory.decodeStream(input, null,options);

                        img3.setImageBitmap(bitmap);
                        imgBtnAnimal.setVisibility(imgBtnAnimal.GONE);
                        img3.setVisibility(img3.VISIBLE);


                    } catch (FileNotFoundException e) {

                        e.printStackTrace();
                    }  }


        }

    }
    Uri uriToSend=null;
    private static final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");
    public void sendBanner(View view){

        Thread threadt = new Thread(new Runnable() {

            @Override
            public void run() {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("name","name");
                    jsonObject.put("state","state");
                    jsonObject.put("text","text");
                    jsonObject.put("telNumber","telNumber");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                OkHttpClient client = SingletonOkHttp.getClient();
                File fffile = new File(getRealPathFromUri(uriToSend));
                try {
                    RequestBody formBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("file", fffile.getName(),
                                    RequestBody.create(MediaType.parse("text/plain"), fffile))
                            .addFormDataPart("name", "name")
                            .addFormDataPart("state", "state")
                            .addFormDataPart("text", "text")
                            .addFormDataPart("telnumber", "telnumber")
                            .build();
                    Request request = new Request.Builder().url("http://192.168.0.100:8080/getuserdata/save").post(formBody).build();
                    Response response = client.newCall(request).execute();
                    String resss = response.body().toString();
                    Log.e("RESULTATREQ",resss);
                   }catch (Exception e){
                    e.printStackTrace();
                }
        }

    });
    threadt.start();
    }
    private String getRealPathFromUri(Uri contentUri){
        String result=null;
        Cursor cursor = getContentResolver().query(contentUri,null,null,null,null);
        if(cursor == null){
            result = contentUri.getPath();
        }else{
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        Log.e("PATH_REGresult",result);
        return result;
    }
}
