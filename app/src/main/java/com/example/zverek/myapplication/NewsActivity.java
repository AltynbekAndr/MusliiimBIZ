package com.example.zverek.myapplication;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;

public class NewsActivity extends AppCompatActivity{

    String pathToShrift = "fonts/tahomabd.ttf";
    Typeface typefacen;
    private boolean isRedirected;
    WebView webView = null;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    @Override
    public void onPause() {
        super.onPause();

        try {
            Class.forName("android.webkit.WebView")
                    .getMethod("onPause", (Class[]) null)
                    .invoke(webView, (Object[]) null);

        } catch(ClassNotFoundException cnfe) {
        } catch(NoSuchMethodException nsme) {
        } catch(InvocationTargetException ite) {
        } catch (IllegalAccessException iae) {
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);
        typefacen = Typeface.createFromAsset(getAssets(),pathToShrift);
        TextView textView24 = (TextView) findViewById(R.id.tt);
        textView24.setTypeface(typefacen);
        webView = (WebView) findViewById(R.id.myWebView);
        String url = "https://www.facebook.com/dla.musulman/";
        startWebView(webView,url);
    }
    private void startWebView(WebView webView, String url) {

        webView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                isRedirected = true;
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                isRedirected = false;
            }

            public void onLoadResource (WebView view, String url) {
                if (!isRedirected) {
                    if (progressDialog == null) {
                        progressDialog = new ProgressDialog(NewsActivity.this);
                        progressDialog.setMessage("жүктөлүүдө...");
                        progressDialog.show();
                    }
                }

            }
            public void onPageFinished(WebView view, String url) {
                try{
                    isRedirected=true;

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }



                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }

        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
