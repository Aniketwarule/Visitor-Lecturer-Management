package com.example.uvaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity6 extends AppCompatActivity {
    Button b;
    TextView t1,t3,t4;
    Spinner s2,s3,s4;
    String[] num={"1","2","3","4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        b=findViewById(R.id.button15);
        t1=findViewById(R.id.textView29);

        t3=findViewById(R.id.textView32);
        t4=findViewById(R.id.textView33);

        s2=findViewById(R.id.spinner5);
        s3=findViewById(R.id.spinner6);
        s4=findViewById(R.id.spinner7);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity6.this,android.R.layout.simple_spinner_item,num);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter);
        s3.setAdapter(adapter);

    }
    public void display(View v)
    {
        Intent i=new Intent(this,MainActivity7.class);
        startActivity(i);
    }
    public void onClick(View v)
    {
        b.setVisibility(View.VISIBLE);
        t1.setVisibility(View.VISIBLE);

        t3.setVisibility(View.VISIBLE);
        t4.setVisibility(View.VISIBLE);

        s2.setVisibility(View.VISIBLE);
        s3.setVisibility(View.VISIBLE);
        s4.setVisibility(View.VISIBLE);
    }
    public void click(View v)
    {
        Toast.makeText(this, "Make new entry again ", Toast.LENGTH_SHORT).show();
    }
}