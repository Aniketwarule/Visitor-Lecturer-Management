package com.example.uvaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Spinner s;
    String role[]={"Admin","Visitor"};
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyDbHelper db = new MyDbHelper(this);

        SharedPreferences st = PreferenceManager.getDefaultSharedPreferences(this);
        if(!st.getBoolean("firstTime",false))
        {
            //Runs only one time
            MyDbHelper mdh = new MyDbHelper(this);
            mdh.addadmin("itadmin","itadmin");
            mdh.addadmin("coadmin","coadmin");
            mdh.addadmin("mechadmin","mechadmin");
            mdh.addadmin("civiladmin","civiladmin");
            mdh.addadmin("elecadmin","alecadmin");
            mdh.addadmin("entcadmin","antcadmin");
            mdh.addadmin("aeadmin","aeadmin");
            mdh.addadmin("ddgmadmin","ddgmadmin");
            mdh.addadmin("aimladmin","aimladmin");

            SharedPreferences.Editor editor = st.edit();
            editor.putBoolean("firstTime",true);
            editor.commit();
        }
        s=findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,role);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = s.getSelectedItem().toString();
                if(s1.equals("Admin"))
                {
                    Intent i=new Intent(getApplicationContext(),MainActivity3.class);
                    startActivity(i);
                }
                else if(s1.equals("Visitor"))
                {
                    Intent i1=new Intent(getApplicationContext(),MainActivity9.class);
                    startActivity(i1);
                }
            }
        });


        //db.createNewTable();

    }

}