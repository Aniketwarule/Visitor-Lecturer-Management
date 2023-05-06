package com.example.uvaa;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MyDbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "VLMDB";
    public static final int DB_VERSION = 1;
    public static final String TABLE_ADMIN = "admins";
    public static final String AD_ID = "id";
    public static final String AD_NAME = "name";
    public static final String AD_CON = "contact";
    public static final String AD_DEP = "dep";
    public static final String AD_USERNAME = "username";
    public static final String AD_PASSWD = "passwd";

    public static final String TABLE_VL = "visitors";
    public static final String VL_ID = "vlid";
    public static final String VL_NAME = "vlname";
    public static final String VL_MOB = "vlcon";
    public static final String VL_USER = "vluser";
    public static final String VL_PASS = "vlpass";
    public static final String SU_YEAR = "subyear";
    public static final String SU_SUBNAME = "subname";
    public static final String SU_PRAC = "subprac";
    public static final String SU_LEC = "sublec";

    public static final String TABLE_ME = "entries";
    public static final String ME_ID = "meid";
    public static final String ME_SUB = "mesub";
    public static final String ME_TYPE = "metype";
    public static final String ME_SLOT = "meslot";
    public static final String ME_ATTE = "meatte";
    public static final String LEC_NAME = "lecturername";
    public static final String LEC_DATE="lecturedate";


    public MyDbHelper(Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {

        String tb1 = "CREATE TABLE "+TABLE_ADMIN+"("+AD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+AD_NAME+
                " TEXT,"+AD_CON+" TEXT,"+AD_DEP+" TEXT,"+AD_USERNAME+" TEXT,"+AD_PASSWD+" TEXT )";
        db.execSQL(tb1);

        String tb2 = "CREATE TABLE "+TABLE_VL+"("+VL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+VL_NAME+" TEXT,"+VL_MOB+" TEXT,"+
                VL_USER+" TEXT,"+VL_PASS+" TEXT,"+SU_YEAR+ " TEXT,"+SU_SUBNAME+" TEXT,"+SU_PRAC+" TEXT,"+SU_LEC+" TEXT )";
        db.execSQL(tb2);

        String tb3 = "CREATE TABLE "+TABLE_ME+"("+ME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+LEC_NAME+" TEXT,"+ME_SUB+
                " TEXT,"+ME_TYPE+" TEXT,"+ME_SLOT+" TEXT,"+ME_ATTE+" TEXT,"+LEC_DATE+" TEXT )";
        db.execSQL(tb3);
    }


    public void addadmin(String uname,String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(AD_USERNAME,uname);
        values1.put(AD_PASSWD,pass);
        db.insert(TABLE_ADMIN,null,values1);
    }

    public String[] get_sub(String uname){

        ArrayList<String> dynamicArray = new ArrayList<String>();
        int i=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_VL+" WHERE "+VL_USER+"=?", new String[] {uname});


        while(cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(SU_SUBNAME));
            dynamicArray.add(name);
        }
        String[] s = dynamicArray.toArray(new String[0]);
        return s;
    }

    public String[] get_visitors()
    {
        ArrayList<String> dynamicArray = new ArrayList<String>();
        int i=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT "+VL_NAME+" FROM "+TABLE_VL,null);


        while(cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(VL_NAME));
            dynamicArray.add(name);
        }
        String[] s = dynamicArray.toArray(new String[0]);
        return s;
    }

    public void make_entry(String sub,String type,String slot,String atte,String user,String lecdate)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(ME_SUB,sub);
        values1.put(LEC_NAME,user);
        values1.put(ME_TYPE,type);
        values1.put(ME_SLOT,slot);
        values1.put(ME_ATTE,atte);
        values1.put(LEC_DATE,lecdate);

        db.insert(TABLE_ME,null,values1);
    }

    public boolean checkuspass(String uname, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_ADMIN+" WHERE "+AD_USERNAME+"=? and "+AD_PASSWD+"=?", new String[] {uname,pass});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkuspass1(String uname, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_VL+" WHERE "+VL_USER+"=? and "+VL_PASS+"=?", new String[] {uname,pass});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public String gedit(String uname)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+AD_ID+" FROM "+TABLE_ADMIN+" WHERE "+AD_USERNAME+"='"+uname+"'",null);
        int id = cursor.getColumnIndex(AD_ID);
        return cursor.getString(id);
    }

    public void update_admin(String name,String mob,String dep,String usern,String pass,String user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(AD_NAME,name);
        values.put(AD_CON,mob);
        values.put(AD_DEP,dep);
        values.put(AD_USERNAME,usern);
        values.put(AD_PASSWD,pass);

        db.update(TABLE_ADMIN,values,AD_USERNAME+"=?",new String[]{user});
    }

    public void update_visitor(String vluser,String vlpass,String u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(VL_USER,vluser);
        values.put(VL_PASS,vlpass);

        db.update(TABLE_VL,values,VL_USER+"=?",new String[]{u});
    }

    public boolean checker(String user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_ADMIN+" WHERE "+AD_USERNAME+"=?", new String[] {});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


    public void addVisitor(String vlname,String vlmob,String vluser,String vlpass,String s1,String s2,String s3,String s4)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(VL_NAME,vlname);
        values1.put(VL_MOB,vlmob);
        values1.put(VL_USER,vluser);
        values1.put(VL_PASS,vlpass);
        values1.put(SU_SUBNAME,s1);
        values1.put(SU_YEAR,s2);
        values1.put(SU_LEC,s3);
        values1.put(SU_PRAC,s4);
        db.insert(TABLE_VL,null,values1);
    }

    public String[][] get_entry(String mon,int index)
    {
        int gap=0;
        int day=1;
        String[][] s = new String[12][10];
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        Date date = new Date(2023,index,0);
        String weekday = sdf.format(date);
        if(weekday.equals("Monday"))
            gap=0;
        else if (weekday.equals("Tuesday"))
            gap=1;
        else if (weekday.equals("Wednesday"))
            gap=2;
        else if (weekday.equals("Thursday"))
            gap=3;
        else if (weekday.equals("Friday"))
            gap=4;
        else if (weekday.equals("Saturday"))
            gap=5;

        index++;

        for(int i=0;i<10;i+=2,day++)
        {
            for(int j=gap*2;j<12;j+=2) {
                String d = day + "-" + index + "-2023";
                SQLiteDatabase db = this.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ME + " WHERE " + LEC_DATE + "=? AND " + ME_TYPE + "=?", new String[]{d, "Lec"});
                if (cursor.getCount() > 0) {
                    s[j][i] = Integer.toString(cursor.getCount());
                }
                else
                {
                    s[j][i]="";
                }

                Cursor cursor1 = db.rawQuery("SELECT * FROM "+TABLE_ME+" WHERE "+LEC_DATE+"=? AND "+ME_TYPE+"=?", new String[] {d,"Prac"});
                if(cursor1.getCount()>0)
                    s[j][i+1]=Integer.toString(cursor1.getCount());
                else
                    s[j][i+1]="";

                day++;
            }
            gap=0;
        }
        return s;
    }
        @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_VL);
        onCreate(db);
    }
}
