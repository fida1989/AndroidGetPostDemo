package com.hungrydroid.httpapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.get)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Connectivity.isNetworkAvailable(MainActivity.this)) {
                   getData();
                } else {
                    Toast.makeText(MainActivity.this, "Network not available!", Toast.LENGTH_SHORT).show();

                }
            }
        });
        ((Button)findViewById(R.id.post)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Connectivity.isNetworkAvailable(MainActivity.this)) {
                    postData();
                } else {
                    Toast.makeText(MainActivity.this, "Network not available!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void getData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/http/get.php?data="+"Android", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setMessage("Loading...");
                pDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(MainActivity.this, new String(responseBody), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }


        });
    }

    public void postData() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("data", "Android");
        client.post("http://10.0.2.2/http/post.php",params,new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setMessage("Loading...");
                pDialog.show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(MainActivity.this, new String(responseBody), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }


        });
    }
}
