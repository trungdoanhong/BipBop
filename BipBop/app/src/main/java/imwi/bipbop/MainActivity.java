package imwi.bipbop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    String StuffType = "{\"stufftype\":\"Pháº¿ liá»‡u\",\"xlocation\":16.0743494,\"xlocation\":108.2238438}";

    public void Accept(View v) {
        Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
        myIntent.putExtra("stufftype", StuffType);
        MainActivity.this.startActivity(myIntent);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://deltaxrobot.com/setsignal.php?vl=2";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
