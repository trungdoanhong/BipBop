package com.example.bipbpcaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void moveHome(View v) {

        Intent myIntent = new Intent(Main3Activity.this, MainActivity.class);
        Main3Activity.this.startActivity(myIntent);
    }
}
