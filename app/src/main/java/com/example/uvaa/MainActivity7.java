package com.example.uvaa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity7 extends AppCompatActivity {

    EditText up_name,up_mob,up_dep,up_user,up_pass;
    MyDbHelper db;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        up_name = (EditText) findViewById(R.id.editTextTextPersonName11);
        up_mob  = (EditText) findViewById(R.id.editTextTextPersonName12);
        up_dep  = (EditText) findViewById(R.id.editTextTextPersonName13);
        up_user = (EditText) findViewById(R.id.editTextTextPersonName14);
        up_pass = (EditText) findViewById(R.id.editTextTextPersonName15);
        btn = (Button) findViewById(R.id.button71);


        db = new MyDbHelper(this);
        setContentView(R.layout.activity_main7);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = up_name.getText().toString();
                String mob = up_mob.getText().toString();
                String dep = up_dep.getText().toString();
                String user = up_user.getText().toString();
                String pass = up_pass.getText().toString();

                if(name.equals(""))
                {
                    Toast.makeText(MainActivity7.this,"Name cannot be Empty",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(mob.equals(""))
                    {
                        Toast.makeText(MainActivity7.this,"Contact cannot be Empty",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if(dep.equals(""))
                        {
                            Toast.makeText(MainActivity7.this,"Please choose Department",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            if(user.equals(""))
                            {
                                Toast.makeText(MainActivity7.this,"Username Cannot be Empty",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                boolean chec = db.checkuser(user);
                                if(chec)
                                {
                                    Toast.makeText(MainActivity7.this,"Username is already taken",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    if(pass.equals(""))
                                    {
                                        Toast.makeText(MainActivity7.this,"Password cannot be Empty",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        db.update_admin(name,mob,dep,user,pass,"0");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}