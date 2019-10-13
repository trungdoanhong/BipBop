package imwi.bipbop;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
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

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import org.json.JSONException;
import org.json.JSONObject;

public class LogService extends Service {

    private static final String CHANNEL_ID = "imwi.bipbop.NOTIFICATION_CHANNEL_ID";
    //Declaring your implementation of Runnable
    public int counter = 0;
    public boolean isNoti = false;
    public String Respone = "";

    @Override
    public void onCreate() {
        super.onCreate();
        Notification notification = new Notification();
        //startForeground(1488, notification);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        startTimer();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void getData()
    {
        RequestQueue requestQueue;

// Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

// Start the queue
        requestQueue.start();

        String url ="https://deltaxrobot.com/data.txt";

        Log.i("tag", url);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i("tag", response);
                            Respone = response;
                            addNotification();
                        //textView.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("tag", "error");
                //textView.setText("Error");
            }
        });
        requestQueue.add(stringRequest);
    }

    public void getUrlString()
    {

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

            Log.i("tag", url);

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            Log.i("tag", response);
                            if (response.equals("1") && isNoti == false)
                            {
                                isNoti = true;
                                getData();
                            }
                            if (!response.equals("1"))
                            {
                                isNoti = false;
                            }
                            //textView.setText("Response: " + response.toString());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("tag", "error");
                    //textView.setText("Error");
                }
            });
            requestQueue.add(stringRequest);

    }

    private Timer timer;
    private TimerTask timerTask;

    //bat dau dem timer
    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, to wake up every 1 second
        timer.schedule(timerTask, 1000, 1000); //
    }

    //Ham su kien timer
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                getUrlString();
            }
        };
    }

    //dugn timer
    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public JSONObject jsonRoot;
    public String stuffType;

    //thong bao ra mang hinh
    public void addNotification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "BipBop Noti";
            String description = "Nhôm đồng gang sắt thép vụn";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = (android.app.NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }

        //Gọi giao dien Khi nhan nut nhan
        Intent intentnhan = new Intent(this, MainActivity.class);
        intentnhan.putExtra("stufftype", stuffType);
        PendingIntent PIntentnhan = PendingIntent.getActivity(this, 0, intentnhan,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //Gọi giao dien Khi nhan nut bo
        Intent intentbo = new Intent(this, Main2Activity.class);
        PendingIntent PIntentbo = PendingIntent.getActivity(this, 0, intentbo,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //Gọi giao dienj khi co thong bao
//        Intent FullScreenPendingIntent = new Intent(this, MainActivity.class);
//        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(this, 0, FullScreenPendingIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);


        try {
            jsonRoot = new JSONObject(Respone);
            stuffType = jsonRoot.getString("stufftype");
        } catch (JSONException e) {
            e.printStackTrace();
        }



        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Có người cần bán")
                        .setContentText("")
                        .setWhen(System.currentTimeMillis())
                        .addAction(R.mipmap.ic_launcher, "Xem", PIntentnhan) // #0;
                        .addAction(R.mipmap.ic_launcher, "Bỏ qua", PIntentbo); // #0;
                        //.setFullScreenIntent(fullScreenPendingIntent, true);

        notificationBuilder.build().flags |= Notification.FLAG_AUTO_CANCEL;

//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);

        //notificationBuilder.setContentIntent(contentIntent);
        //notificationBuilder.setFullScreenIntent(contentIntent, true);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(0, notificationBuilder.build());
    }
}
