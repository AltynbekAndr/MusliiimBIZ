package com.example.zverek.myapplication.services;

import android.app.AlarmManager;import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.text.format.DateFormat;
import android.util.Log;

import com.example.zverek.myapplication.Main2Activity;
import com.example.zverek.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class NamazTimeNotificationService extends Service {
    public final String TAG = "SERVICE_CLASS";
    public static final int NOTIFY_ID= 1;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(!t.isAlive()){
            t.start();
        }

        Log.e(TAG,"сервис стартовал");
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
        // TODO Auto-generated method stub
        Log.e(TAG,"onTaskRemoved");
        Intent restartService = new Intent(getApplicationContext(),
                this.getClass());
        restartService.setPackage(getPackageName());
        PendingIntent restartServicePI = PendingIntent.getService(
                getApplicationContext(), 1, restartService,
                PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmService.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + 60000, restartServicePI);
        Log.e(TAG,"onTaskRemoved");
    }
    class MyAsyncTask extends AsyncTask<Void,Void,Void>{
        String signalVariant=null;
        int idNotif = 0;
        @Override
        protected Void doInBackground(Void... voids) {
            BufferedReader bf = null;
            try {
                bf = new BufferedReader(new InputStreamReader(getApplicationContext().openFileInput("sound.txt"), "UTF-8"));
                signalVariant = bf.readLine();
            } catch (UnsupportedEncodingException e) {
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }finally {
                try {
                    bf.close();
                } catch (IOException e) {
                }
            }
            if(signalVariant.equals("Азан Медина")){
                idNotif = 1;
            }else if(signalVariant.equals("Азан Мекке")){
                idNotif =2;
            }else if(signalVariant.equals("Вибрация")){
                idNotif = 3;
            }else{
                idNotif = 0;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(idNotif==3){
                vibrate();
                return;
            }else if(idNotif==2){
                mekke();
                return;
            }else if(idNotif==1){
                medina();
                return;
            }else {
                raw();
            }
        }
    }
    public void raw() {
        Uri uri = Uri.parse("android.resource://"+getApplicationContext().getPackageName()+"/raw/namaz_raw");
        Intent notificationIntent = new Intent(this, Main2Activity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = this.getResources();

        // до версии Android 8.0 API 26
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentIntent(contentIntent)
                // обязательные настройки
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                //.setContentTitle(res.getString(R.string.notifytitle)) // Заголовок уведомления
                .setContentTitle(namazName)
                //.setContentText(res.getString(R.string.notifytext))
                .setContentText("Намаз убагы") // Текст уведомления
                // необязательные настройки
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher_foreground)) // большая
                // картинка
                //.setTicker(res.getString(R.string.warning)) // текст в строке состояния
                .setTicker(namazName)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setSound(uri) ; // автоматически закрыть уведомление после нажатия

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Альтернативный вариант
        // NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFY_ID, builder.build());
        this.onDestroy();
    }
    public void medina() {
        Uri uri = Uri.parse("android.resource://"+getApplicationContext().getPackageName()+"/raw/medina");
        Intent notificationIntent = new Intent(this, Main2Activity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = this.getResources();

        // до версии Android 8.0 API 26
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentIntent(contentIntent)
                // обязательные настройки
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                //.setContentTitle(res.getString(R.string.notifytitle)) // Заголовок уведомления
                .setContentTitle(namazName)
                //.setContentText(res.getString(R.string.notifytext))
                .setContentText("Намаз убагы") // Текст уведомления
                // необязательные настройки
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher_foreground)) // большая
                // картинка
                //.setTicker(res.getString(R.string.warning)) // текст в строке состояния
                .setTicker(namazName)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setSound(uri) ; // автоматически закрыть уведомление после нажатия

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Альтернативный вариант
        // NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFY_ID, builder.build());
        this.onDestroy();
    }
    public void mekke() {
        Uri uri = Uri.parse("android.resource://"+getApplicationContext().getPackageName()+"/raw/mekke");
        Intent notificationIntent = new Intent(this, Main2Activity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = this.getResources();

        // до версии Android 8.0 API 26
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentIntent(contentIntent)
                // обязательные настройки
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                //.setContentTitle(res.getString(R.string.notifytitle)) // Заголовок уведомления
                .setContentTitle(namazName)
                //.setContentText(res.getString(R.string.notifytext))
                .setContentText("Намаз убагы") // Текст уведомления
                // необязательные настройки
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher_foreground)) // большая
                // картинка
                //.setTicker(res.getString(R.string.warning)) // текст в строке состояния
                .setTicker(namazName)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setSound(uri) ; // автоматически закрыть уведомление после нажатия

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Альтернативный вариант
        // NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFY_ID, builder.build());
        this.onDestroy();
    }
    public void vibrate() {
        long[] vibrate = new long[] { 1000, 1000, 1000, 1000, 1000 };
        Intent notificationIntent = new Intent(this, Main2Activity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = this.getResources();

        // до версии Android 8.0 API 26
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentIntent(contentIntent)
                // обязательные настройки
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                //.setContentTitle(res.getString(R.string.notifytitle)) // Заголовок уведомления
                .setContentTitle(namazName)
                //.setContentText(res.getString(R.string.notifytext))
                .setContentText("Намаз убагы") // Текст уведомления
                // необязательные настройки
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher_foreground)) // большая
                // картинка
                //.setTicker(res.getString(R.string.warning)) // текст в строке состояния
                .setTicker(namazName)
                .setVibrate(vibrate)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true); // автоматически закрыть уведомление после нажатия

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Альтернативный вариант
        // NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFY_ID, builder.build());
        this.onDestroy();
    }
    Date date = new Date();
    String hour = null;
    String min = null;
    String monthNumber = null;
    String day = null;
    int iday = 0;
    int imon = 0;
    int ihh = 0;

    JSONObject year = null;
    JSONObject months = null;
    JSONObject dayJs = null;

    String strJson=null;
    String strJson2=null;
    String strJson3=null;
    String strJson4=null;
    String strJson5=null;
    String strJson6=null;

    String path = null;
    String json = null;
    String hrs = null;
    String crntDay = null;
    String namazName = null;

    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            Log.e(TAG,"run стартовал");
            Log.e(TAG,"серви спит 59 сек");
            try {
                TimeUnit.SECONDS.sleep(59);
            } catch (InterruptedException e) {
            }
            boolean bool = true;
            while(bool) {
                date = new Date();
                monthNumber = (String) DateFormat.format("MM", date);
                day = (String) DateFormat.format("dd", date);
                hour = (String) DateFormat.format("kk", date);
                min = (String) DateFormat.format("mm", date);
                iday = Integer.valueOf(day);
                imon = Integer.valueOf(monthNumber);
                ihh = Integer.valueOf(hour);

                Log.e(TAG,"====while====");
                if ((json == null)||(!crntDay.equals(monthNumber))) {
                    Log.e(TAG,"json null");
                    try {
                        BufferedReader bf = new BufferedReader(new InputStreamReader(getApplicationContext().openFileInput("path.txt"), "UTF-8"));
                        path = bf.readLine();
                        InputStream is = getApplicationContext().getAssets().open(path + ".json");
                        int size = is.available();
                        byte[] buffer = new byte[size];
                        is.read(buffer);
                        json = new String(buffer, "UTF-8");
                        is.close();
                        year = new JSONObject(json);
                        months = year.getJSONObject("month" + imon);
                        dayJs = months.getJSONObject("day_" + iday);
                        Log.e(TAG,"json (String,JSONObject) заполнен");
                        strJson = dayJs.getString("bagymdat");
                        strJson2 = dayJs.getString("kundun_chyguusu");
                        strJson3 = dayJs.getString("beshim");
                        strJson4 = dayJs.getString("asr");
                        strJson5 = dayJs.getString("sham");
                        strJson6 = dayJs.getString("kuptan");
                        crntDay = monthNumber;
                        Log.e(TAG,"JSON.getString() сработал");
                    } catch (IOException ex) {
                    }catch (JSONException e) {
                    }
                }

                Log.e(TAG,"hrs"+hrs+" , monthNumber"+imon+" , day"+iday);
                Log.e(TAG,strJson+" "+strJson2+" "+strJson3+" "+strJson4+" "+strJson5+" "+strJson6);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                hrs = ihh + ":" + min;

                if (hrs.equals(strJson)) {
                    namazName = "Багымдат";
                    Log.e(TAG,"Багымдат сработал");
                        new MyAsyncTask().execute();
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
                if (hrs.equals(strJson2)) {
                    namazName = "Кундун чыгуусу";
                    Log.e(TAG,"Кундун чыгуусу сработал");
                        new MyAsyncTask().execute();
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
                if (hrs.equals(strJson3)) {
                    namazName = "Бешим";
                    Log.e(TAG,"Бешим сработал");
                        new MyAsyncTask().execute();

                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
                if (hrs.equals(strJson4)) {
                    namazName = "Аср";
                    Log.e(TAG,"Аср сработал");
                        new MyAsyncTask().execute();
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
                if (hrs.equals(strJson5)) {
                    namazName = "Шам";
                    Log.e(TAG,"Шам сработал");
                        new MyAsyncTask().execute();
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
                if (hrs.equals(strJson6)) {
                    namazName = "Куптан";
                    Log.e(TAG,"Куптан сработал");
                        new MyAsyncTask().execute();
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                    }
                }
            }



        }
    });


}