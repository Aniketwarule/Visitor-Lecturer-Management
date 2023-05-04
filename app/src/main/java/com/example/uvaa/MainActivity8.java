package com.example.uvaa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity8 extends AppCompatActivity {

    Spinner s1,s2;
    String[] year={"2022-23","2023-24","2024-25","2025-26","2026-27","2027-28","2028-29","2029-30"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        s1 =findViewById(R.id.spinner);


        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(MainActivity8.this,android.R.layout.simple_spinner_item,year);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter1);

    }
}