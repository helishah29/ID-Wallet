package com.ksb.myhighwaylevy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ksb.myhighwaylevy.Fragments.BuyTicket;

import java.net.URLEncoder;

import API.ApiClient;
import API.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ksb.myhighwaylevy.MainActivity.HLSHAREDPREFERENCES;

/**
 * Created by Bharavi on 15-02-2018.
 */

public class InstamojoActivity extends Activity
{
    SharedPreferences sharedPreferences;

    String userId,selectedToll,tripType,selectedCar,checkStatus;
    int tollAmount;

    private WebView wv1;
    ProgressBar pbar;

    String postData;
    ApiInterface apiInterface;
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instamojo_layout);

        sharedPreferences=getSharedPreferences(HLSHAREDPREFERENCES, MODE_PRIVATE);

        userId = sharedPreferences.getString("userId","");
        tollAmount = sharedPreferences.getInt("tollAmount",0);
        selectedToll = sharedPreferences.getString("tollName","");
        selectedCar = sharedPreferences.getString("carNo","");
        tripType = sharedPreferences.getString("tripType","");
        checkStatus = sharedPreferences.getString("checkStatus","");

        //Toast.makeText(this, "amt "+tollAmount, Toast.LENGTH_SHORT).show();

        pbar = (ProgressBar)findViewById(R.id.progressBar1);
        wv1=(WebView)findViewById(R.id.webView);

        wv1.setWebViewClient(new MyBrowser());

        final String url2 = "https://bharavijoshi01.000webhostapp.com/HL_App/finalSuccess.php";

        final String insta = "https://bharavijoshi01.000webhostapp.com/HL_App/buyTicket.php";

        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        try{
            //String postData = "email=" + URLEncoder.encode("abc@test.com", "UTF-8") + "&password=" + URLEncoder.encode("123", "UTF-8");
            postData = "userId=" + URLEncoder.encode(userId, "UTF-8")
                    // + "&amount=" + URLEncoder.encode(String.valueOf(tollAmount), "UTF-8")
                    + "&tollAmount=" + URLEncoder.encode(String.valueOf(tollAmount), "UTF-8")
                    + "&tripType=" + URLEncoder.encode(tripType, "UTF-8")
                    + "&selectedToll=" + URLEncoder.encode(selectedToll, "UTF-8")
                    + "&selectedCar=" + URLEncoder.encode(selectedCar, "UTF-8")
                    + "&checkStatus=" + URLEncoder.encode(checkStatus, "UTF-8");
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

                if(url1.equals("https://bharavijoshi01.000webhostapp.com/HL_App/finalSuccess.php"))
                {
                    Toast.makeText(InstamojoActivity.this, "Payment Successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(InstamojoActivity.this, HomeActivity.class);
                    intent.putExtra("URL", url1);
                    //sendSMS();
                    startActivity(intent);
                    finish();
                }


                //Toast.makeText(MainActivity.this, "Completed" + url1, Toast.LENGTH_SHORT).show();
//                if(url1.length()>url2.length())
//                {
//                    if (url1.substring(0, url2.length()).equals(url2))
//                    {
//                        //Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(Instamojo.this, SuccessPayment.class);
//                        intent.putExtra("URL", url1);
//                        startActivity(intent);
//                        finish();
//                    }
//                }
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
        AlertDialog.Builder builder = new AlertDialog.Builder(InstamojoActivity.this);

        builder.setTitle("Please confirm!!!");
        builder.setMessage("Want to Cancel Payment?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do something when user want to exit the app
                // Let allow the system to handle the event, such as exit the app
                //InstamojoActivity.super.onBackPressed();
                startActivity(new Intent(InstamojoActivity.this,HomeActivity.class));
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do something when want to stay in the app
                Toast.makeText(InstamojoActivity.this,"thank you",Toast.LENGTH_LONG).show();
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


    void sendSMS()
    {
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);

        dialog=new ProgressDialog(InstamojoActivity.this);
        dialog.setMessage("Loading..");

        Call<String> call=apiInterface.sendSms(userId,selectedCar,selectedToll);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(InstamojoActivity.this, "DONE", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.d("TAG",t+"\n"+call);

                AlertDialog.Builder builder = new AlertDialog.Builder(InstamojoActivity.this);
                //  builder.setTitle(R.string.app_name);
                builder.setMessage("\nAn Error Occured.. Please try again ")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
