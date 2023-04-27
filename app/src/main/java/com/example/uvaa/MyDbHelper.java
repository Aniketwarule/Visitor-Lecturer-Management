package com.example.uvaa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

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
    public static final String VL_NAME = "vlname";
    public static final String VL_MOB = "vlcon";
    public static final String VL_USER = "vluser";
    public static final String VL_PASS = "vlpass";
    public static final String VL_SUB = "sub";

    public static final String TABLE_SUB = "subjects";
    public static final String SU_ID = "sid";
    public static final String SU_YEAR = "subyear";
    public static final String SU_SUBNAME = "subname";
    public static final String SU_PRAC = "subprac";
    public static final String SU_LEC = "sublec";


    public MyDbHelper(Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {

        String tb1 = "CREATE TABLE "+TABLE_ADMIN+"("+AD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+AD_NAME+
                " TEXT,"+AD_CON+" TEXT,"+AD_DEP+" TEXT,"+AD_USERNAME+" TEXT,"+AD_PASSWD+" TEXT )";
        db.execSQL(tb1);

        String tb2 = "CREATE TABLE "+TABLE_VL+"("+VL_NAME+" TEXT,"+VL_MOB+" TEXT,"+VL_USER+" TEXT,"+VL_PASS+" TEXT,"+VL_SUB+" TEXT )";
        db.execSQL(tb2);

        String tb3 ="CREATE TABLE "+TABLE_SUB+"("+SU_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+SU_YEAR+
                " TEXT,"+SU_SUBNAME+" TEXT,"+SU_PRAC+" TEXT,"+SU_LEC+" TEXT )";
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

    public boolean checkuspass(String uname, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_ADMIN+" WHERE "+AD_USERNAME+"=? and "+AD_PASSWD+"=?", new String[] {uname,pass});

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
    public boolean checker(String user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_ADMIN+" WHERE "+AD_USERNAME+"=?", new String[] {});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_VL);
        onCreate(db);
    }
}
