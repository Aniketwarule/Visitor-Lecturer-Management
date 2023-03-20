package com.example.uvaa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void show(View v)
    {
        Intent i=new Intent(this,MainActivity3.class);
        startActivity(i);
    }
    public void Display(View v)
    {
        Intent i=new Intent(this,MainActivity4.class);
        startActivity(i);
    }


}