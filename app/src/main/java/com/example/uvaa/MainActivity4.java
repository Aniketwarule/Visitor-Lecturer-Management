package com.example.uvaa;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener {
    CardView c1, c2, c3, c4;
    MyDbHelper db;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new MyDbHelper(this);
        setContentView(R.layout.activity_main4);

        Intent i = getIntent();
        String user = i.getStringExtra("username");

        c1 = (CardView) findViewById(R.id.card1);
        c2 = (CardView) findViewById(R.id.card2);
        c3 = (CardView) findViewById(R.id.card3);
        c4 = (CardView) findViewById(R.id.card4);
        c1.setOnClickListener(this);
        /*c2.setOnClickListener(this);*/
        c3.setOnClickListener(this);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity7.class);
                i.putExtra("username",user);
                startActivity(i);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),generate_bill.class);
                startActivity(i);
            }
        });
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
            /*case R.id.card4:
                i = new Intent(this, MainActivity7.class);
                startActivity(i);
                break; */
        }
    }
}