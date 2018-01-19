package com.example.zverek.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Main4RegistrationActivity extends AppCompatActivity {
    final int picture=5;
    Intent actionPick=null;
    Spinner spinner1 = null;
    Spinner spinner2 = null;
    Spinner spinner3 = null;
    EditText nameEdit = null;
    EditText lNameEdit = null;
    EditText emailEdit = null;
    EditText passwordEdit = null;

    ArrayAdapter<?> adapterState = null;
    ArrayAdapter<?> adapterSex = null;

    ArrayAdapter<?> adapterCity = null;
    ArrayAdapter<?> adapterCity2 = null;
    ArrayAdapter<?> adapterCity3 = null;
    ArrayAdapter<?> adapterCity4 = null;
    ArrayAdapter<?> adapterCity5 = null;
    ArrayAdapter<?> adapterCity6 = null;
    ArrayAdapter<?> adapterCity7 = null;

    TextView txtSex = null;
    TextView txtState = null;
    TextView cityText = null;
    TextView textView24 = null;
    int countState = 0;
    String pathToShrift = "fonts/tahomabd.ttf";
    Typeface typefacen;
    String stateStr=null;
    String cityStr=null;
    ImageButton imgB = null;
    BitmapFactory.Options options = null;
    Bitmap bitmap = null;
    ImageView imgB2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
        typefacen = Typeface.createFromAsset(getAssets(),pathToShrift);
        textView24 = (TextView) findViewById(R.id.tt);
        textView24.setTypeface(typefacen);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        nameEdit = (EditText) findViewById(R.id.nameedit);
        lNameEdit = (EditText) findViewById(R.id.lnameedit);
        emailEdit = (EditText) findViewById(R.id.emailedit);
        passwordEdit = (EditText) findViewById(R.id.passwordedit);
        txtSex = (TextView) findViewById(R.id.jynystxt);
        txtState = (TextView) findViewById(R.id.statetxt);
        cityText = (TextView) findViewById(R.id.cityText);
        imgB2 = (ImageView) findViewById(R.id.imgB2);
        options = new BitmapFactory.Options();
        adapterSex = ArrayAdapter.createFromResource(Main4RegistrationActivity.this, R.array.sex, android.R.layout.simple_list_item_1);
        adapterState = ArrayAdapter.createFromResource(Main4RegistrationActivity.this, R.array.state, android.R.layout.simple_list_item_1);

        adapterCity = ArrayAdapter.createFromResource(Main4RegistrationActivity.this, R.array.chui, android.R.layout.simple_list_item_1);
        adapterCity2 = ArrayAdapter.createFromResource(Main4RegistrationActivity.this, R.array.talas, android.R.layout.simple_list_item_1);
        adapterCity3 = ArrayAdapter.createFromResource(Main4RegistrationActivity.this, R.array.osh, android.R.layout.simple_list_item_1);
        adapterCity4 = ArrayAdapter.createFromResource(Main4RegistrationActivity.this, R.array.jalal_abad, android.R.layout.simple_list_item_1);
        adapterCity5 = ArrayAdapter.createFromResource(Main4RegistrationActivity.this, R.array.batken, android.R.layout.simple_list_item_1);
        adapterCity6 = ArrayAdapter.createFromResource(Main4RegistrationActivity.this, R.array.yssyk_kol, android.R.layout.simple_list_item_1);
        adapterCity7 = ArrayAdapter.createFromResource(Main4RegistrationActivity.this, R.array.naryn, android.R.layout.simple_list_item_1);


        adapterSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapterSex);
        adapterState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapterSex);
        spinner2.setAdapter(adapterState);
        spinner3.setAdapter(adapterCity);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sex = String.valueOf(adapterSex.getItem(i));
                txtSex.setText("" + sex);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String state = String.valueOf(adapterState.getItem(i));
                txtState.setText("" + state);
                switch (i) {
                    case 0: spinner3.setAdapter(adapterCity);
                        countState = 0;
                        stateStr = "jsons/chui/";
                    break;
                    case 1: spinner3.setAdapter(adapterCity2);
                         countState = 1;
                        stateStr = "jsons/talas/";
                        break;
                    case 2: spinner3.setAdapter(adapterCity3);
                         countState = 2;
                        stateStr = "jsons/osh/";
                        break;
                    case 3: spinner3.setAdapter(adapterCity4);
                        countState = 3;
                        stateStr = "jsons/jalalabad/";
                        break;
                    case 4: spinner3.setAdapter(adapterCity5);
                        countState = 4;
                        stateStr = "jsons/batken/";
                        break;
                    case 5: spinner3.setAdapter(adapterCity6);
                        countState = 5;
                        stateStr = "jsons/yssykkol/";
                        break;
                    case 6: spinner3.setAdapter(adapterCity7);
                        countState = 6;
                        stateStr = "jsons/naryn/";
                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String city = null;
                BufferedWriter bf = null;
                try {
                     bf = new BufferedWriter(new OutputStreamWriter(openFileOutput("cityname.txt", MODE_PRIVATE), "UTF-8"));

                } catch (FileNotFoundException e) {
                } catch (IOException e) {
                }
                switch (countState) {
                    case 0: city = adapterCity.getItem(i).toString();
                        cityText.setText("" + city);
                        cityStr=stateStr+city;
                        try {
                            bf.write(city);
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        break;
                    case 1:
                        city = adapterCity2.getItem(i).toString();
                        cityText.setText("" + city);
                        cityStr=stateStr+city;
                        try {
                            bf.write(city);
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        city = adapterCity3.getItem(i).toString();
                        cityText.setText("" + city);
                        cityStr=stateStr+city;
                        try {
                            bf.write(city);
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        city = adapterCity4.getItem(i).toString();
                        cityText.setText("" + city);
                        cityStr=stateStr+city;
                        try {
                            bf.write(city);
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        city = adapterCity5.getItem(i).toString();
                        cityText.setText("" + city);
                        cityStr=stateStr+city;
                        try {
                            bf.write(city);
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 5:
                        city = adapterCity6.getItem(i).toString();
                        cityText.setText("" + city);
                        cityStr=stateStr+city;
                        try {
                            bf.write(city);
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 6:
                        city = adapterCity7.getItem(i).toString();
                        cityText.setText("" + city);
                        cityStr=stateStr+city;
                        try {
                            bf.write(city);
                            bf.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void mainActivity(View view){
        Intent intent = new Intent(Main4RegistrationActivity.this,Main2Activity.class);
        if(cityStr!=null){
            try {
                BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(openFileOutput("path.txt",MODE_PRIVATE),"UTF-8"));
                BufferedWriter bf2 = new BufferedWriter(new OutputStreamWriter(openFileOutput("name.txt",MODE_PRIVATE),"UTF-8"));
                BufferedWriter bf3 = new BufferedWriter(new OutputStreamWriter(openFileOutput("password.txt",MODE_PRIVATE),"UTF-8"));
                bf.write(cityStr);
                bf2.write(nameEdit.getText().toString());
                bf3.write(passwordEdit.getText().toString());
                bf.close();
                bf2.close();
                bf3.close();
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    public void selectImageUser(View view){
        imgB = (ImageButton) findViewById(R.id.imgB);
        actionPick  = new Intent(Intent.ACTION_PICK);
        actionPick.setType("image/*");
        startActivityForResult(actionPick, picture);
        actionPick = null;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch(requestCode) {
            case picture:
                if(resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    try {
                        InputStream input = getContentResolver().openInputStream(uri);
                        options.inSampleSize = 4;
                        bitmap =  BitmapFactory.decodeStream(input, null,options);
                        File imageFile2 = new File(getFilesDir()+"/my_img_for_icon");
                        imageFile2.mkdir();
                        Thread thread = new Thread(new Runnable() {
                            FileOutputStream out = null;
                            @Override
                            public void run() {
                                try {
                                    out = new FileOutputStream(getFilesDir()+"/my_img_for_icon/img_user.png");
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        if (out != null) {
                                            out.close();
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });

                        thread.start();
                        imgB2.setImageBitmap(bitmap);
                        imgB.setVisibility(imgB.GONE);
                        imgB2.setVisibility(imgB2.VISIBLE);
                        input.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


        }

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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
