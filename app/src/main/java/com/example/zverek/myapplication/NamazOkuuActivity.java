package com.example.zverek.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class NamazOkuuActivity extends AppCompatActivity {
    ImageView imgNamaz = null;
    View customAlertForImage =null;
    AlertDialog.Builder builderImgAlert = null;
    AlertDialog alertImage = null;
    Intent intent = null;
    String pathToShrift = "fonts/tahomabd.ttf";
    Typeface typefacen;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.namaz_okuu);
        typefacen = Typeface.createFromAsset(getAssets(),pathToShrift);
        TextView textView24 = (TextView) findViewById(R.id.textView4);
        textView24.setTypeface(typefacen);
        builderImgAlert = new AlertDialog.Builder(NamazOkuuActivity.this);
        customAlertForImage = getLayoutInflater().inflate(R.layout.custom_alert_image, null);
        builderImgAlert.setView(customAlertForImage);
        imgNamaz =(ImageView) customAlertForImage.findViewById(R.id.imageView3);
        alertImage = builderImgAlert.create();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    public void clickunImage(View view){

        switch (view.getId()){
            case R.id.profile_image :imgNamaz.setImageBitmap(
                    decodeSampledBitmapFromResource(getResources(), R.drawable.j1, 480, 250));
                break;
            case R.id.profile_image2 :imgNamaz.setImageBitmap(
                    decodeSampledBitmapFromResource(getResources(), R.drawable.j2, 480, 250));
                break;
            case R.id.profile_image3 :imgNamaz.setImageBitmap(
                    decodeSampledBitmapFromResource(getResources(), R.drawable.j3, 480, 250));
                break;
            case R.id.profile_image4 :imgNamaz.setImageBitmap(
                    decodeSampledBitmapFromResource(getResources(), R.drawable.j4, 480, 250));
                break;
            case R.id.profile_image5 :imgNamaz.setImageBitmap(
                    decodeSampledBitmapFromResource(getResources(), R.drawable.j5, 480, 250));
                break;
            case R.id.profile_image6 :imgNamaz.setImageBitmap(
                    decodeSampledBitmapFromResource(getResources(), R.drawable.j6, 480, 250));
                break;
            case R.id.profile_image7 :imgNamaz.setImageBitmap(
                    decodeSampledBitmapFromResource(getResources(), R.drawable.j7, 480, 250));
                break;
            case R.id.profile_image8 :imgNamaz.setImageBitmap(
                    decodeSampledBitmapFromResource(getResources(), R.drawable.j8, 480, 250));
               break;
        }

        alertImage.show();
    }
    public void intentBagymdatActivity(View view){
        intent = new Intent(NamazOkuuActivity.this,NamazBagymdatActivity.class);
        startActivity(intent);
        intent = null;
    }
    public void intentBeshimActivity(View view){
        intent = new Intent(NamazOkuuActivity.this,NamazBeshimActivity.class);
        startActivity(intent);
        intent = null;
    }
    public void intentDigerActivity(View view){
        intent = new Intent(NamazOkuuActivity.this,NamazDigerActivity.class);
        startActivity(intent);
        intent = null;
    }
    public void intentKuptanActivity(View view){
        intent = new Intent(NamazOkuuActivity.this,NamazKuptanActivity.class);
        startActivity(intent);
        intent = null;
    }
    public void intentShamActivity(View view){
        intent = new Intent(NamazOkuuActivity.this,NamazShamActivity.class);
        startActivity(intent);
        intent = null;
    }
    public  int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                  int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
