package com.example.uvaa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity8 extends AppCompatActivity {

    Spinner s1,s2;
    String[] dept={"IT","CO","ET","EE","ME","CE","DDGM","AE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        s1 =findViewById(R.id.spinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity8.this,android.R.layout.simple_spinner_item,dept);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);

    }
}