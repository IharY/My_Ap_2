package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedCat";
    public static final String TABLE_RESULTS = "results";
    public static final String COL1 = "ID";
    public static final String COL2 = "date";
    public static final String COL3 = "score";
    public static final String COL4 = "user";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_RESULTS + "(" + COL1 + " integer PRIMARY KEY," + COL2 + " TEXT," + COL3 + " TEXT," + COL4 + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);
        onCreate(db);
    }

    public void addData(String date, String score, String user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, date);
        contentValues.put(COL3, score);
        contentValues.put(COL4, user);
        db.insert(DBHelper.TABLE_RESULTS, null, contentValues);
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_RESULTS;
        Cursor data = db.rawQuery(query, null);

        return data;
    }

    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESULTS, null, null);
    }
}

