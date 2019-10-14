package com.example.bipbpcaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 3;
    EditText etStuffType;
    ImageView img1;
    ImageView img2;
    ImageView ivHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitVariables();
    }

    private void InitVariables() {

        etStuffType =(EditText) findViewById(R.id.etStuffType);
        img1 = (ImageView) findViewById(R.id.img1);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMAGE);
            }
        });

        ivHome = (ImageView) findViewById(R.id.ivHome);

    }

    public void resetHome(View v) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://deltaxrobot.com/setsignal.php?vl=0";

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
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                img1.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(MainActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

    public void selectMaterialType(View v)
    {
        Button bt = (Button)v;

        Drawable img = (Drawable)bt.getBackground();
        Drawable imgON = v.getResources().getDrawable(R.drawable.bt2);

        if (img.getConstantState() == imgON.getConstantState())
        {
            bt.setBackgroundResource(R.drawable.bt);
            String currentDes = etStuffType.getText().toString();
            currentDes = currentDes.replace(", " + bt.getText().toString(), "");
            etStuffType.setText(currentDes);
        }
        else
        {
            bt.setBackgroundResource(R.drawable.bt2);
            String currentDes = etStuffType.getText().toString();
            currentDes += ", " + bt.getText().toString();
            etStuffType.setText(currentDes);
        }
    }

    public void nextPage(View v) {

        Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
        myIntent.putExtra("stufftype", etStuffType.getText().toString());
        MainActivity.this.startActivity(myIntent);
    }

}
