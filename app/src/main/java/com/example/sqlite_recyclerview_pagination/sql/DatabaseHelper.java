package com.example.sqlite_recyclerview_pagination.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sqlite_recyclerview_pagination.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE = "database.db";
    public static String TABLE = "mytable";
    public static String BOOK_NAME = "name";
    public static String AUTHOR = "author";

    String br;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //  br= "CREATE TABLE mytable(name TEXT,company TEXT,city TEXT,country TEXT);";
        br = "CREATE TABLE " + TABLE + "(" + BOOK_NAME + " Text, " + AUTHOR + " Text);";
        db.execSQL(br);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE + " ;");
    }


    public void insertdata(String name, String author) {
        System.out.print("Hello " + br);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(BOOK_NAME, name);
        contentValues.put(AUTHOR, author);

        db.insert(TABLE, null, contentValues);


    }

    public void removePlace(String name, String author) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, "Book name='" + name + "'and Author='" + author + "'", null);
        db.close();
    }

    public List<DataModel> getdata() {
        // DataModel dataModel = new DataModel();
        List<DataModel> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE + " ;", null);
        StringBuffer stringBuffer = new StringBuffer();
        DataModel dataModel = null;
        while (cursor.moveToNext()) {
            dataModel = new DataModel();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
            dataModel.setBook_name(name);
            dataModel.setAuthor(author);
            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            data.add(dataModel);
        }

        for (DataModel mo : data) {

            Log.i("Hellomo", "" + mo.getBook_name());
        }
        return data;
    }


}