package com.example.uvaa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    EditText vl_name,vl_mob,vl_user,vl_pass,sub_name,sub_yr,sub_lect,sub_prac;
    Dialog dilog;
    Button b1,b3;
    String[][] v=new String[100][4];
    int a=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        vl_name = (EditText)findViewById(R.id.editTextTextPersonName3);
        vl_mob = (EditText)findViewById(R.id.editTextTextPersonName4);
        vl_user = (EditText)findViewById(R.id.editTextTextPersonName5);
        vl_pass = (EditText)findViewById(R.id.editTextTextPassword);

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
        b3=dilog.findViewById(R.id.button8);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dilog.hide();
                sub_name=(EditText)dilog.findViewById(R.id.editTextTextPersonName16);
                sub_yr=(EditText) dilog.findViewById(R.id.editTextTextPersonName);
                sub_lect=(EditText) dilog.findViewById(R.id.editTextTextPersonName17);
                sub_prac=(EditText) dilog.findViewById(R.id.editTextTextPersonName18);
                String subname=sub_name.getText().toString();
                String subyr=sub_yr.getText().toString();
                String sublect=sub_lect.getText().toString();
                String subprac=sub_prac.getText().toString();
                for(int i=0;i<=4;i++)
                {
                    v[i][a]=subname;
                }

            }
        });


    }
}