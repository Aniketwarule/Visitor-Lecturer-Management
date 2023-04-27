package com.example.uvaa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity2 extends AppCompatActivity {

    EditText vl_name,vl_mob,vl_user,vl_pass;
    Dialog dilog;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        vl_name = (EditText) findViewById(R.id.editTextTextPersonName3);
        vl_mob = (EditText) findViewById(R.id.editTextTextPersonName4);
        vl_user = (EditText) findViewById(R.id.editTextTextPersonName5);
        vl_pass = (EditText) findViewById(R.id.editTextTextPassword);

        dilog = new Dialog(MainActivity2.this);
        dilog.setContentView(R.layout.custom_dialog);
        dilog.setCancelable(false);
        Button b = dilog.findViewById(R.id.button9);

        b1=findViewById(R.id.button6);
        b1.setOnClickListener(new View.OnClickListener()
        {
           public void onClick(View v)
           {
               dilog.show();
           }
        });


    }
}