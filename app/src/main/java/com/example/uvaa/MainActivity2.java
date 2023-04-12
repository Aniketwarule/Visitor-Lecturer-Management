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

    Dialog dilog;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dilog = new Dialog(MainActivity2.this);
        dilog.setContentView(R.layout.custom_dialog);
        dilog.setCancelable(false);
        Button b = dilog.findViewById(R.id.button9);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dilog.dismiss();
            }
        });

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