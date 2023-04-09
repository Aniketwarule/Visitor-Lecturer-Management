package com.example.uvaa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener {

    public CardView c1, c2, c3, c4, c5;

    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        c1 = (CardView) findViewById(R.id.card1);
        c2 = (CardView) findViewById(R.id.card2);
        c3 = (CardView) findViewById(R.id.card3);
        c4 = (CardView) findViewById(R.id.card4);
        c5 = (CardView) findViewById(R.id.card5);
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.card1:
                i = new Intent(this, MainActivity2.class);
                startActivity(i);
                break;
            /*case R.id.card2:
                i=new Intent(this,MainActivity2.class);
                startActivity(i);
                break;*/
            case R.id.card3:
                i = new Intent(this, MainActivity8.class);
                startActivity(i);
                break;
            case R.id.card4:
                i = new Intent(this, MainActivity7.class);
                startActivity(i);
                break;
            case R.id.card5:
                i = new Intent(this, MainActivity6.class);
                startActivity(i);
                break;

        }
    }
}