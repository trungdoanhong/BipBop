package com.example.bipbpcaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.ads.formats.NativeAd;

import java.util.Timer;
import java.util.TimerTask;

public class Main3Activity extends AppCompatActivity {

    Timer t;
    TextView tvPrice;
    ImageView imgTick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvPrice = (TextView) findViewById(R.id.tvPrice);
        imgTick = (ImageView) findViewById(R.id.imgTich);

        t = new Timer();
        t.schedule(new TimerTask() {

            public void run()
            {
                Log.i("t", "+++");

                RequestQueue requestQueue;

// Instantiate the cache
                Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
                Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
                requestQueue = new RequestQueue(cache, network);

// Start the queue
                requestQueue.start();

                String url ="https://deltaxrobot.com/signal.txt";

// Formulate the request and handle the response.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (Integer.parseInt(response) > 4)
                                {
                                    Main3Activity.this.tvPrice.setText(response);

                                }

                                imgTick.setVisibility(View.VISIBLE);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                            }
                        });

// Add the request to the RequestQueue.
                requestQueue.add(stringRequest);
            }
        }, 5000,5000);
    }

    public void moveHome(View v) {

        Intent myIntent = new Intent(Main3Activity.this, MainActivity.class);
        Main3Activity.this.startActivity(myIntent);
    }
}
