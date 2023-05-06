package com.example.uvaa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    TextView t1,t3,t5,t6;
    Spinner s4,s5,s7;
    String[] type={"Lec","Prac"};
    String[] slot={"10:30-11:30","11:30-12:30","12:30-1:30","2:00-3:00","3:00-4:00","4:15-5:15","5:15-6:15"};
    String[] slot1={"10:30-12:30","11:30-1:30","2:00-4:00","4:15-6:15"};
    Dialog d;
    EditText txt1,txt2,txt3,txt4,txt5;
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
        t5=findViewById(R.id.textView37);
        t6=findViewById(R.id.textView);

        s5=findViewById(R.id.spinner5);
        s7=findViewById(R.id.spinner7);
        s4=findViewById(R.id.spinner4);

        txt3=findViewById(R.id.editTextTextPersonName20);
        txt5=findViewById(R.id.editTextTextPersonName21);


        String[] subjects = db.get_sub(user);
        s7=findViewById(R.id.spinner7);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(MainActivity6.this,android.R.layout.simple_spinner_item,subjects);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s7.setAdapter(adapter1);


        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(MainActivity6.this,android.R.layout.simple_spinner_item,type);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s5.setAdapter(adapter2);

        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(MainActivity6.this,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s4.setAdapter(adapter3);

        s5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Update the options of the ArrayAdapter for Spinner 2 based on the selected value of Spinner 1
                String selectedOption = (String) parent.getItemAtPosition(position);
                if (selectedOption.equals("Lec")) {
                    adapter3.clear();
                    adapter3.addAll(slot);
                } else if (selectedOption.equals("Prac")) {
                    adapter3.clear();
                    adapter3.addAll(slot1);
                }
                adapter3.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });


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
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month++;
                                txt4.setText(day+"-"+month+"-"+year);
                            }
                        },
                year,month,day);
                dPD.show();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sub = s7.getSelectedItem().toString().trim();
                String type = s5.getSelectedItem().toString().trim();
                String slot = s4.getSelectedItem().toString().trim();
                String atte = txt3.getText().toString().trim();
                String date= txt4.getText().toString();
                db.make_entry(sub,type,slot,atte,user,date);
            }
        });

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
        t5.setVisibility(View.VISIBLE);
        t6.setVisibility(View.VISIBLE);
        txt3.setVisibility(View.VISIBLE);
        txt5.setVisibility(View.VISIBLE);
        s7.setVisibility(View.VISIBLE);
        s4.setVisibility(View.VISIBLE);
        s5.setVisibility(View.VISIBLE);

    }
    public void click(View v)
    {
        Toast.makeText(this, "Make new entry again ", Toast.LENGTH_SHORT).show();
    }
}