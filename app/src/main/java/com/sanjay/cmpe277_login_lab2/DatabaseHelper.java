package com.sanjay.cmpe277_login_lab2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "registeruser";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT, " + PASSWORD + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String userName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, userName);
        contentValues.put(PASSWORD, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return result;
    }

    public Boolean checkUserExists(String userName, String password) {

        String[] columns = {ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = USERNAME + "=?" + " and " + PASSWORD + "=?";
        String[] selectionArguments = {userName, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArguments, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0) {
            return true;
        }

        return false;
    }
}
