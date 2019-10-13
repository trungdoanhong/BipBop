package com.example.bipbpcaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etStuffType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitVariables();
    }

    private void InitVariables() {
        etStuffType =(EditText) findViewById(R.id.etStuffType);
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
