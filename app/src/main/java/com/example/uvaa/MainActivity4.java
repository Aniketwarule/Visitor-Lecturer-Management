package com.example.uvaa;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


    }
    public void put(View v)
    {
        Intent i=new Intent(this,MainActivity2.class);
        startActivity(i);
    }
    public void get(View v)
    {
        Intent i=new Intent(this,MainActivity5.class);
        startActivity(i);
    }

}