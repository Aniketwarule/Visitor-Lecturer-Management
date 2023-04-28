package com.example.uvaa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    EditText sub_name,sub_yr,sub_lect,sub_prac;
    EditText vl_name,vl_mob,vl_user,vl_pass;
    Dialog dilog;
    Button b1,b3,b4;

    MyDbHelper db = new MyDbHelper(this);
    ArrayList<String[]> dynamicArray = new ArrayList<String[]>();
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
                dilog.dismiss();
                sub_name=(EditText) dilog.findViewById(R.id.editTextTextPersonName16);
                sub_yr=(EditText) dilog.findViewById(R.id.editTextTextPersonName);
                sub_lect=(EditText) dilog.findViewById(R.id.editTextTextPersonName17);
                sub_prac=(EditText) dilog.findViewById(R.id.editTextTextPersonName18);
                String subname = sub_name.getText().toString();
                String subyr = sub_yr.getText().toString();
                String sublect = sub_lect.getText().toString().trim();
                String subprac = sub_prac.getText().toString().trim();
                sub_name.setText("");
                sub_yr.setText("");
                sub_lect.setText("");
                sub_prac.setText("");

                dynamicArray.add(new String[]{subname,subyr,sublect,subprac});

            }
        });

        b4 = findViewById(R.id.button2);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vlname = vl_name.getText().toString();
                String vlmob = vl_mob.getText().toString().trim();
                String vluser = vl_user.getText().toString().trim();
                String vlpass = vl_pass.getText().toString().trim();

                for(int i=0;i<dynamicArray.size();i++)
                {
                    String s1 = dynamicArray.get(i)[0];
                    String s2 = dynamicArray.get(i)[1];
                    String s3 = dynamicArray.get(i)[2];
                    String s4 = dynamicArray.get(i)[3];
                    db.addVisitor(vlname,vlmob,vluser,vlpass,s1,s2,s3,s4);
                }
            }
        });
    }
}