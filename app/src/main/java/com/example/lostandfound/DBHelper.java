package com.example.lostandfound;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;

public class DBHelper extends SQLiteOpenHelper {

    //constructor to initialise the database, setting name and version
    public DBHelper(Context context) {
        super(context, "LostFoundDB", null, 1);
    }

    //create database
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table structure with columns
        db.execSQL("CREATE TABLE items(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "type TEXT," +
                "name TEXT," +
                "phone TEXT," +
                "description TEXT," +
                "date TEXT," +
                "location TEXT)");
    }

    //called when the database version is upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop the existing table if it exists
        db.execSQL("DROP TABLE IF EXISTS items");
        //recreate the table
        onCreate(db);
    }
    //method to insert item into items table
    public boolean insertItem(String type, String name, String phone, String desc, String date, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type", type);
        values.put("name", name);
        values.put("phone", phone);
        values.put("description", desc);
        values.put("date", date);
        values.put("location", location);
        long result = db.insert("items", null, values);
        return result != -1;
    }

    //method to retrieve items from items table
    public Cursor getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM items", null);
    }

    //method to delete item based off the id
    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("items", "id=?", new String[]{String.valueOf(id)});
    }
}