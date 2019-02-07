package com.example.akshay.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Human.db";
    private static final String TABLE_NAME = "Student";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "FirstName";
    private static final String COL_3 = "LastName";
    private static final String COL_4 = "EmailID";
    private static final String COL_5 = "City";

    public DataBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+TABLE_NAME+"( "+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_2+" TEXT,"+COL_3+" TEXT,"+COL_4+" TEXT,"+COL_5+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists "+TABLE_NAME);
    }

    public boolean insertData(String First,String Last,String Email,String City)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_2,First);
        cv.put(COL_3,Last);
        cv.put(COL_4,Email);
        cv.put(COL_5,City);

         Long isInserted = db.insert(TABLE_NAME,null,cv);
         if(isInserted == -1)
             return false;
         else
             return true;
    }

    public Cursor getAllDatabaseData()
    {
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from "+TABLE_NAME,null);
        return  c;
    }

    public boolean updateData(int ID,String fname,String lname,String email,String city)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2,fname);
        cv.put(COL_3,lname);
        cv.put(COL_4,email);
        cv.put(COL_5,city);
        db.update(TABLE_NAME,cv,"ID = ?",new String[] { ID+"" });
        return true;
    }

    public int deleteData(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id+""});
    }
}