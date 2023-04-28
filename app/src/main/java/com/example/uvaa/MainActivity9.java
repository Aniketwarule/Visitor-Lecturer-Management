package com.example.uvaa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity9 extends AppCompatActivity {

    Button b;
    EditText username,password;
    MyDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        username = (EditText) findViewById(R.id.EditText20);
        password = (EditText) findViewById(R.id.EditText22);

        b =  (Button) findViewById(R.id.button9);

        db = new MyDbHelper(this);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String id_user = String.valueOf(0);

                if(user.equals(""))
                {
                    Toast.makeText(MainActivity9.this,"Username Cannot be Empty",Toast.LENGTH_LONG).show();
                }
                else {
                    if (pass.equals("")) {
                        Toast.makeText(MainActivity9.this, "Password cannot be Empty", Toast.LENGTH_LONG).show();
                    } else {
                        Boolean chec = db.checkuspass1(user, pass);
                        if (chec == true) {
                            Toast.makeText(MainActivity9.this, "Sign in Successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity6.class);
                            i.putExtra("username", user);
                            startActivity(i);
                        } else {
                            Toast.makeText(MainActivity9.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }
}