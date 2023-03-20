package com.example.uvaa;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {
    EditText e,e1,e2;
    TextView t,t1;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        e=findViewById(R.id.editTextTextPersonName6);
        t=findViewById(R.id.textView9);
        t1=findViewById(R.id.textView10);

        e2=findViewById(R.id.editTextTextPersonName7);
        e1=findViewById(R.id.editTextTextPersonName8);

    }
    TextWatcher txt=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence1, int i, int i1, int i2) {


            if (charSequence1.toString().equals("1234")) {
                t.setEnabled(false);
                t1.setEnabled(false);
                e1.setEnabled(false);
                e2.setEnabled(false);
            }
        }



        public void onTextChanged(CharSequence charSequence,int i, int i1, int i2){
            t1.setEnabled(true);
            t.setEnabled(true);
            e1.setEnabled(true);
            e2.setEnabled(true);

        }

        @Override
        public void afterTextChanged(Editable editable){

        }


    };

}