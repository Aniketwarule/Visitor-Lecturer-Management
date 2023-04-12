package com.example.uvaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    EditText username,password;
    Button button;
    MyDbHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.button3);
        db = new MyDbHelper(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals(""))
                {
                    Toast.makeText(MainActivity3.this,"Username Cannot be Empty",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(pass.equals(""))
                    {
                        Toast.makeText(MainActivity3.this,"Password cannot be Empty",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Boolean checkuserpass1 = db.checkuspass(user,pass);
                        if(checkuserpass1==true)
                        {
                            Toast.makeText(MainActivity3.this, "Sign in Successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity4.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(MainActivity3.this,"Invalid Credentials",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }
}