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

public class MyDBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 12;
    private static final String DATABASE_NAME = "familyMemberDB";
    private static final String TABLE_FAMILIES = "families";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "fname";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + TABLE_FAMILIES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " VARCHAR2(30) " +
                ");";
        sqLiteDatabase.execSQL(query);

        //dbMembers = new MyDBMembers(context, null, null, 1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FAMILIES);
        onCreate(sqLiteDatabase);
    }

    public void addFamily(Family thisFamily) {

        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("INSERT INTO " + TABLE_FAMILIES + " ("
                + COLUMN_NAME + ") " + "VALUES (?)");
        stmt.bindString(1, thisFamily.getName());
        stmt.execute();
        stmt.close();

        db.close();

    }

    //Delete a member from the family database
    public void deleteFamily(String famName) {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("DELETE FROM " + TABLE_FAMILIES + " WHERE fname = ?");
        stmt.bindString(1, famName);
        stmt.execute();
        stmt.close();
        db.close();

    }

    public List getFamiliesString() {
        List familyNames = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_NAME + " from " + TABLE_FAMILIES +
                " ORDER BY " + COLUMN_ID + " ASC";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            familyNames.add(cursor.getString(1));
        }
        db.close();

        return familyNames;
    }


    public int getId(String famName) {

        int familyID = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_FAMILIES +
                " WHERE " + COLUMN_NAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(famName)});
        while (cursor.moveToNext()) {

            familyID = cursor.getInt(0);
        }
        db.close();

        return familyID;
    }
}
