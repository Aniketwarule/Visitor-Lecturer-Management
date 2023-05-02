package com.example.uvaa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity6 extends AppCompatActivity {
    Button b,b1,b2;
    TextView t1,t3,t4,t5,t6,t7;
    Spinner s2,s3,s4,s5,s6;
    String[] num={"1","2","3","4"};
    String[] slot={"10:30-11:30","11:30-12:30","12:30-1:30","2:00-3:00","3:00-4:00","4:15-5:15","5:15-6:15"};
    String[] slot1={"10:30-12:30","11:30-1:30","2:00-4:00","4:15-6:15"};
    Dialog d;
    EditText txt1,txt2,txt3,txt4;
    MyDbHelper db = new MyDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);


        Intent i = getIntent();
        String user = i.getStringExtra("username");

        b=findViewById(R.id.button15);
        t1=findViewById(R.id.textView29);

        t3=findViewById(R.id.textView32);
        t4=findViewById(R.id.textView33);
        t5=findViewById(R.id.textView37);
        t6=findViewById(R.id.textView);
        t7=findViewById(R.id.textView42);

        s2=findViewById(R.id.spinner5);
        s3=findViewById(R.id.spinner6);
        s4=findViewById(R.id.spinner7);
        s5=findViewById(R.id.spinner4);
        s6=findViewById(R.id.spinner8);

        txt3=findViewById(R.id.editTextTextPersonName20);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity6.this,android.R.layout.simple_spinner_item,num);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter);
        s3.setAdapter(adapter);

        ArrayAdapter<String> a=new ArrayAdapter<String>(MainActivity6.this,android.R.layout.simple_spinner_item,slot);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s5.setAdapter(a);

        ArrayAdapter<String> ad=new ArrayAdapter<String>(MainActivity6.this,android.R.layout.simple_spinner_item,slot1);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s6.setAdapter(ad);

        d= new Dialog(MainActivity6.this);
        d.setContentView(R.layout.custom_dilog2);
        d.setCancelable(false);
        b1=findViewById(R.id.button14);
        b1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                d.show();
            }
        });
        b2=d.findViewById(R.id.button7);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
                txt1=d.findViewById(R.id.editTextTextPersonName19);
                txt2=d.findViewById(R.id.editTextTextPersonName2);
                String username = txt1.getText().toString();
                String password = txt2.getText().toString();

                db.update_visitor(username,password,user);

            }
        });

        txt4 = findViewById(R.id.editTextTextPersonName21);

        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dPD = new DatePickerDialog(
                        MainActivity6.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                txt4.setText(dayOfMonth+"-"+month+"-"+year);
                            }
                        },
                year,month,day);
                dPD.show();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sub = s4.getSelectedItem().toString();
                String lec = s2.getSelectedItem().toString();
                String prac = s3.getSelectedItem().toString();
                String atte = txt3.getText().toString().trim();
                db.make_entry(sub,lec,prac,atte,user);
            }
        });

        String[] subjects = db.get_sub(user);
        s4=findViewById(R.id.spinner7);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(MainActivity6.this,android.R.layout.simple_spinner_item,subjects);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s4.setAdapter(adapter1);

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
        t5.setVisibility(View.VISIBLE);
        t6.setVisibility(View.VISIBLE);
        t7.setVisibility(View.VISIBLE);
        txt3.setVisibility(View.VISIBLE);

        s2.setVisibility(View.VISIBLE);
        s3.setVisibility(View.VISIBLE);
        s4.setVisibility(View.VISIBLE);
    }
    public void click(View v)
    {
        Toast.makeText(this, "Make new entry again ", Toast.LENGTH_SHORT).show();
    }
}