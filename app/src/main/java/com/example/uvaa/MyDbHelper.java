package com.example.uvaa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "VLMDB";
    public static final int DB_VERSION = 1;
    public static final String TABLE_ADMIN = "admins";
    public static final String TABLE_VL = "visitors";
    public static final String AD_ID = "id";
    public static final String AD_NAME = "name";
    public static final String AD_CON = "contact";
    public static final String AD_DEP = "dep";
    public static final String AD_USERNAME = "username";
    public static final String AD_PASSWD = "passwd";


    public MyDbHelper(Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {

        String tb1 = "CREATE TABLE "+TABLE_ADMIN+"("+AD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+AD_NAME+
                " TEXT,"+AD_CON+" TEXT,"+AD_DEP+" TEXT,"+AD_USERNAME+" TEXT,"+AD_PASSWD+" TEXT )";
        db.execSQL(tb1);
    }

    public void addadmin(String uname,String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(AD_USERNAME,uname);
        values1.put(AD_PASSWD,pass);
        db.insert(TABLE_ADMIN,null,values1);
    }

    public boolean checkuspass(String usname, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_ADMIN+" WHERE "+AD_USERNAME+"=? and "+AD_PASSWD+"=?", new String[] {usname,pass});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public int getid(String uname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+AD_ID+" FROM "+TABLE_ADMIN+" WHERE "+AD_USERNAME+"=?", new String[] {uname});
        return cursor.getColumnIndex("id");
    }

    public void update_admin(String name,String mob,String dep,String user,String pass,String id_user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(AD_NAME,name);
        values.put(AD_CON,mob);
        values.put(AD_DEP,dep);
        values.put(AD_USERNAME,user);
        values.put(AD_PASSWD,pass);

        db.update(TABLE_ADMIN,values,AD_ID+"=?",new String[]{id_user});
    }
    public boolean checkuser(String user)
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
        onCreate(db);
    }
}
