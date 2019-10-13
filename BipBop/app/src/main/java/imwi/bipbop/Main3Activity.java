package imwi.bipbop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import com.android.volley.toolbox.Volley;

import java.util.Timer;
import java.util.TimerTask;

public class Main3Activity extends AppCompatActivity {

    EditText etPrice;
    Button btCloseDeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        etPrice = (EditText)findViewById(R.id.etPrice);
        btCloseDeal = (Button) findViewById(R.id.btCloseDeal);
    }

    Timer t;

    public void Deal(View v) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://deltaxrobot.com/setsignal.php?vl=" + etPrice.getText().toString();

        Log.i("tag", url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("tag", response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("tag", "error");
            }
        });
        queue.add(stringRequest);

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
                                if (response.equals("4"))
                                {
                                    btCloseDeal.setText("Thỏa thuận xong!");

                                }
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
}
