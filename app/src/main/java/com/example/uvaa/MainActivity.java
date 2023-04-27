package com.example.uvaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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

        //db.createNewTable();

    }
    public void activity(View v)
    {
        Intent i=new Intent(this,MainActivity3.class);
        startActivity(i);
    }
}