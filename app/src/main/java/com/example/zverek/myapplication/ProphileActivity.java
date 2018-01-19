package com.example.zverek.myapplication;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class ProphileActivity extends AppCompatActivity {
    Spinner spinner_state = null;
    Spinner spinner_city = null;
    ArrayAdapter<?> adapterState = null;
    String pathToShrift = "fonts/tahomabd.ttf";
    Typeface typefacen;
    ArrayAdapter<?> adapterCity = null;
    ArrayAdapter<?> adapterCity2 = null;
    ArrayAdapter<?> adapterCity3 = null;
    ArrayAdapter<?> adapterCity4 = null;
    ArrayAdapter<?> adapterCity5 = null;
    ArrayAdapter<?> adapterCity6 = null;
    ArrayAdapter<?> adapterCity7 = null;
    TextView txtState = null;
    TextView cityText = null;
    int countState = 0;
    View customAzanView = null;
    AlertDialog.Builder builderAlert_azan = null;
    AlertDialog alertDua = null;
    String stateStr = null;
    String cityStr = null;
    final int picture = 7;
    ImageButton imgB = null;
    BitmapFactory.Options options = null;
    Bitmap bitmap = null;
    ImageView imgB2;
    Intent actionPick=null;
    TextView textView12 = null;
    EditText nameEdit = null;
    EditText passwordEdit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prophile_layout);
        textView12 = (TextView) findViewById(R.id.textView12);
        typefacen = Typeface.createFromAsset(getAssets(), pathToShrift);
        TextView textView24 = (TextView) findViewById(R.id.textView24);
        textView24.setTypeface(typefacen);
        nameEdit = (EditText) findViewById(R.id.nameedit);
        passwordEdit = (EditText) findViewById(R.id.passwordedit);
        customAzanView = getLayoutInflater().inflate(R.layout.azan_layout, null);
        builderAlert_azan = new AlertDialog.Builder(ProphileActivity.this);
        builderAlert_azan.setView(customAzanView);
        alertDua = builderAlert_azan.create();
        alertDua.setCancelable(false);
        spinner_state = (Spinner) findViewById(R.id.spinner_state);
        spinner_city = (Spinner) findViewById(R.id.spinner_city);
        txtState = (TextView) findViewById(R.id.state);
        cityText = (TextView) findViewById(R.id.city);
        options = new BitmapFactory.Options();
        imgB2 = (ImageView) findViewById(R.id.imgB2);
        adapterState = ArrayAdapter.createFromResource(ProphileActivity.this, R.array.state, android.R.layout.simple_list_item_1);

        adapterCity = ArrayAdapter.createFromResource(ProphileActivity.this, R.array.chui, android.R.layout.simple_list_item_1);
        adapterCity2 = ArrayAdapter.createFromResource(ProphileActivity.this, R.array.talas, android.R.layout.simple_list_item_1);
        adapterCity3 = ArrayAdapter.createFromResource(ProphileActivity.this, R.array.osh, android.R.layout.simple_list_item_1);
        adapterCity4 = ArrayAdapter.createFromResource(ProphileActivity.this, R.array.jalal_abad, android.R.layout.simple_list_item_1);
        adapterCity5 = ArrayAdapter.createFromResource(ProphileActivity.this, R.array.batken, android.R.layout.simple_list_item_1);
        adapterCity6 = ArrayAdapter.createFromResource(ProphileActivity.this, R.array.yssyk_kol, android.R.layout.simple_list_item_1);
        adapterCity7 = ArrayAdapter.createFromResource(ProphileActivity.this, R.array.naryn, android.R.layout.simple_list_item_1);


        spinner_state.setAdapter(adapterState);
        spinner_city.setAdapter(adapterCity);


        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String state = String.valueOf(adapterState.getItem(i));
                txtState.setText("" + state);
                switch (i) {
                    case 0:
                        spinner_city.setAdapter(adapterCity);
                        countState = 0;
                        stateStr = "jsons/chui/";
                        break;
                    case 1:
                        spinner_city.setAdapter(adapterCity2);
                        countState = 1;
                        stateStr = "jsons/talas/";
                        break;
                    case 2:
                        spinner_city.setAdapter(adapterCity3);
                        countState = 2;
                        stateStr = "jsons/osh/";
                        break;
                    case 3:
                        spinner_city.setAdapter(adapterCity4);
                        countState = 3;
                        stateStr = "jsons/jalalabad/";
                        break;
                    case 4:
                        spinner_city.setAdapter(adapterCity5);
                        countState = 4;
                        stateStr = "jsons/batken/";
                        break;
                    case 5:
                        spinner_city.setAdapter(adapterCity6);
                        countState = 5;
                        stateStr = "jsons/yssykkol/";
                        break;
                    case 6:
                        spinner_city.setAdapter(adapterCity7);
                        countState = 6;
                        stateStr = "jsons/naryn/";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    case 0:
                        city = adapterCity.getItem(i).toString();
                        cityText.setText("" + city);
                        cityStr = stateStr + city;
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
                        cityStr = stateStr + city;
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
                        cityStr = stateStr + city;
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
                        cityStr = stateStr + city;
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
                        cityStr = stateStr + city;
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
                        cityStr = stateStr + city;
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
                        cityStr = stateStr + city;
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
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new InputStreamReader(getApplicationContext().openFileInput("sound.txt"), "UTF-8"));
            String signalVariant = bf.readLine();
            textView12.setText(signalVariant);
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }finally {
            try {
                bf.close();
            } catch (IOException e) {
            }
        }
    }

    public void save(View view) {
        if (cityStr != null) {
            try {
                BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(openFileOutput("path.txt",MODE_PRIVATE),"UTF-8"));
                BufferedWriter bf2 = new BufferedWriter(new OutputStreamWriter(openFileOutput("name.txt",MODE_PRIVATE),"UTF-8"));
                BufferedWriter bf3 = new BufferedWriter(new OutputStreamWriter(openFileOutput("password.txt",MODE_PRIVATE),"UTF-8"));
                bf.write(cityStr);
                String nameUser = nameEdit.getText().toString();
                String passwordUser = passwordEdit.getText().toString();
                if(nameUser!=null&&nameUser!=""&&nameUser.length()!=0){
                    bf2.write(nameEdit.getText().toString());
                }
                if(passwordUser!=null&&passwordUser!=""&&passwordUser.length()!=0){
                    bf3.write(passwordEdit.getText().toString());
                }
                bf.close();
                bf2.close();
                bf3.close();
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
        }
        Toast.makeText(ProphileActivity.this, "Сакталды", Toast.LENGTH_SHORT).show();
    }

    public void alertAzan(View view) {
        alertDua.show();
        RadioGroup radioGroup = (RadioGroup) customAzanView.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.vibrate:
                        textView12.setText("Вибрация");
                        try {
                            BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(openFileOutput("sound.txt", MODE_PRIVATE), "UTF-8"));
                            bf.write("Вибрация");
                            bf.close();
                        } catch (FileNotFoundException e) {
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.raw:
                        textView12.setText("Звук");
                        try {
                            BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(openFileOutput("sound.txt", MODE_PRIVATE), "UTF-8"));
                            bf.write("Звук");
                            bf.close();
                        } catch (FileNotFoundException e) {
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.mekke:
                        textView12.setText("Азан Мекке");
                        try {
                            BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(openFileOutput("sound.txt", MODE_PRIVATE), "UTF-8"));
                            bf.write("Азан Мекке");
                            bf.close();
                        } catch (FileNotFoundException e) {
                        } catch (IOException e) {
                        }
                        break;
                    case R.id.medina:
                        textView12.setText("Азан Медина");
                        try {
                            BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(openFileOutput("sound.txt", MODE_PRIVATE), "UTF-8"));
                            bf.write("Азан Медина");
                            bf.close();
                        } catch (FileNotFoundException e) {
                        } catch (IOException e) {
                        }

                        break;

                    default:
                        break;
                }
                Button button4 = (Button) customAzanView.findViewById(R.id.button4);
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDua.dismiss();
                    }
                });
            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public void selectImageUser(View view) {
        imgB = (ImageButton) findViewById(R.id.imgB);
        actionPick = new Intent(Intent.ACTION_PICK);
        actionPick.setType("image/*");
        startActivityForResult(actionPick, picture);
        actionPick = null;
    }

    private String getRealPathFromUri(Uri contentUri) {
        String result = null;
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            result = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case picture:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    try {
                        InputStream input = getContentResolver().openInputStream(uri);
                        options.inSampleSize = 4;
                        bitmap =  BitmapFactory.decodeStream(input, null,options);
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
}