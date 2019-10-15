package com.example.matt.whatsfordinner;

/*
Matthew Noffsinger
CSCI 490
Final project "Whats for dinner" app
*/

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyDBDish extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 12;
    private static final String DATABASE_NAME = "dishDB";
    private static final String TABLE_DISH = "dish";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TYPE = "type";

    public MyDBDish(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_DISH + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " VARCHAR2(30) ," +
                COLUMN_TYPE + " VARCHAR2(30) " +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DISH);
        onCreate(sqLiteDatabase);
    }

    public void addDish(String dishName, String dishType){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("INSERT INTO " + TABLE_DISH + " ("
                + COLUMN_NAME + ", " + COLUMN_TYPE + ") " + "VALUES (?,?)");
        stmt.bindString(1,dishName);
        stmt.bindString(2,dishType);
        stmt.execute();
        stmt.close();

        db.close();
    }

    public void removeDish(String dishName){

    }

    public List getDishString(){
        List dishNames = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_NAME + " from " + TABLE_DISH +
                " ORDER BY " + COLUMN_ID + " ASC";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            dishNames.add(cursor.getString(1));
        }
        db.close();

        return dishNames;
    }

    public int getId(String dishName){
        int dishID = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_DISH +
                " WHERE " + COLUMN_NAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(dishName)});
        //Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            dishID = cursor.getInt(0);
        }
        db.close();

        return dishID;
    }

    public String getMainOrSide(String dishName){
        String type = "";

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_DISH +
                " WHERE " + COLUMN_ID + "=?";
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(getId(dishName))});

        while (cursor.moveToNext()){
            type = cursor.getString(0);
        }
        db.close();

        return type;
    }

    public int getSize(){
        int size = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_DISH;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            size++;
        }
        db.close();
        return size;
    }

}
