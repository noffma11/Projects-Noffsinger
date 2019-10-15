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

public class MyDBIngredients extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 12;
    private static final String DATABASE_NAME = "ingredientsDB";
    private static final String TABLE_INGREDIENTS = "ingredients";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DISHID = "dishid";


    public MyDBIngredients(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_INGREDIENTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DISHID + " INT ," +
                COLUMN_NAME + " VARCHAR2(30) " +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);
        onCreate(sqLiteDatabase);
    }

    public void addIngredient(String ingName, int dishIdToAddTo) {

    }

    public void removeIngredient(String ingName) {

    }

    //all ingredients in a dish returned as an ArrayList
    //public List getIngredientString(int dishId){
    //    return
    //}

    public int getIngredientID(String ingName) {

        return 1;

    }
}
