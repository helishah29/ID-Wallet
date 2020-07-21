package com.example.myinstamojo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private WebView wv1;
    ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbar = (ProgressBar)findViewById(R.id.progressBar1);
        wv1=(WebView)findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());

        final String url = "http://lueworld.000webhostapp.com/HighwayLevy/verifyUser.php";

        final String url2 = "http://lueworld.000webhostapp.com/AppHighwayLevy/success.php";

        final String insta = "http://lueworld.000webhostapp.com/AppHighwayLevy/instaMojoTest.php";

        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        try{
            //String postData = "email=" + URLEncoder.encode("abc@test.com", "UTF-8") + "&password=" + URLEncoder.encode("123", "UTF-8");
            String postData = "tollplaza=" + URLEncoder.encode("Toll A", "UTF-8")
                    + "&amount=" + URLEncoder.encode("310", "UTF-8")
                    + "&contactNo=" + URLEncoder.encode("+919998525136", "UTF-8")
                    + "&userName=" + URLEncoder.encode("abc", "UTF-8")
                    + "&email=" + URLEncoder.encode("abc@test.com", "UTF-8");
            wv1.postUrl(insta,postData.getBytes());
        }catch (Exception e){}

        wv1.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url1) {

                // do your stuff here

                //Toast.makeText(MainActivity.this, "Completed" + url1, Toast.LENGTH_SHORT).show();
                if(url1.length()>url2.length())
                if(url1.substring(0,url2.length()).equals(url2)){
                    //Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,Success.class );
                    intent.putExtra("URL", url1);
                    startActivity(intent);
                    finish();
                }
                pbar.setVisibility(View.GONE);
            }

        });

        //for dialog box genterated by javascript
        wv1.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url2, String message, JsResult result) {
                //Required functionality here
                return super.onJsAlert(view, url2, message, result);
            }
        });


    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    /* In case of android activity changes use this code
    public class MyWebChromeClient extends WebChromeClient {

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result)
        {
            final JsResult finalRes = result;
            new AlertDialog.Builder(view.getContext())
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok,
                            new AlertDialog.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finalRes.confirm();
                                }
                            })
                    .setCancelable(false)
                    .create()
                    .show();
            return true;
        }
    }*/


    //for going back
    protected void showAppExitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Please confirm!!!");
        builder.setMessage("Want to Cancel Payment?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do something when user want to exit the app
                // Let allow the system to handle the event, such as exit the app
                MainActivity.super.onBackPressed();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do something when want to stay in the app
                Toast.makeText(MainActivity.this,"thank you",Toast.LENGTH_LONG).show();
            }
        });

        // Create the alert dialog using alert dialog builder
        AlertDialog dialog = builder.create();

        // Finally, display the dialog when user press back button
        dialog.show();
    }

    @Override
    public void onBackPressed(){
        if(wv1.canGoBack()){
            // If web view have back history, then go to the web view back history
            wv1.goBack();
            Toast.makeText(this, "back press", Toast.LENGTH_SHORT).show();
        }else {
            // Ask the user to exit the app or stay in here
            showAppExitDialog();
        }
    }
// going back code ends here


}
