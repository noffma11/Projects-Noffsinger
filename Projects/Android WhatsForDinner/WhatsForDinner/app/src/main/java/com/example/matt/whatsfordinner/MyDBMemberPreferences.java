package com.example.matt.whatsfordinner;

/*
Matthew Noffsinger
CSCI 490
Final project "Whats for dinner" app
*/

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class MyDBMemberPreferences extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 12;
    private static final String DATABASE_NAME = "MemberPreferencesDB";
    private static final String TABLE_MEMBER_PREFERENCES = "memberpreferences";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MEMBID = "membid";
    private static final String COLUMN_FAVORITES = "favorites";
    private static final String COLUMN_ALLERGIES = "allergies";

    public MyDBMemberPreferences(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_MEMBER_PREFERENCES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MEMBID + " INTEGER ," +
                COLUMN_FAVORITES + " VARCHAR2(30) ," +
                COLUMN_ALLERGIES + " VARCHAR2(30) " +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER_PREFERENCES);
        onCreate(sqLiteDatabase);
    }

    public void addPreferenceFavorite(String faveName, int membIdToAddTo) {

        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("INSERT INTO " + TABLE_MEMBER_PREFERENCES + " ("
                + COLUMN_MEMBID + ", " + COLUMN_FAVORITES + ")  VALUES (?, ?)");
        stmt.bindLong(1, membIdToAddTo);
        stmt.bindString(2, faveName);
        stmt.execute();
        stmt.close();
        db.close();

    }

    public void addPreferenceAllergie(String allergieName, int membIdToAddTo) {

        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("INSERT INTO " + TABLE_MEMBER_PREFERENCES + " ("
                + COLUMN_MEMBID + ", " + COLUMN_ALLERGIES + ")  VALUES (?, ?)");
        stmt.bindLong(1, membIdToAddTo);
        stmt.bindString(2, allergieName);
        stmt.execute();
        stmt.close();
        db.close();

    }

    public void removePreferenceAllergie(String prefName) {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("DELETE FROM " + TABLE_MEMBER_PREFERENCES + " WHERE " + COLUMN_ID + "= ?");
        stmt.bindLong(1, getPreferenceId(prefName));
        stmt.execute();
        stmt.close();
        db.close();
    }

    public int getPreferenceId(String thisName) {
        int prefID = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_MEMBER_PREFERENCES +
                " WHERE " + COLUMN_ALLERGIES + "=? OR " + COLUMN_FAVORITES + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(thisName), String.valueOf(thisName)});

        while (cursor.moveToNext()) {
            prefID = cursor.getInt(0);
        }
        db.close();

        return prefID;
    }

    public List getPreferencesFavoritesString(int membId) {
        List prefNames = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_MEMBID + ", " + COLUMN_FAVORITES + " from " + TABLE_MEMBER_PREFERENCES + " WHERE " + COLUMN_MEMBID + " = " + membId +
                " ORDER BY " + COLUMN_MEMBID + " ASC";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            prefNames.add(cursor.getString(1));
        }
        db.close();

        return prefNames;
    }

    public List getPreferencesAllergiesString(int membId) {
        List prefNames = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_MEMBID + ", " + COLUMN_FAVORITES + " from " + TABLE_MEMBER_PREFERENCES + " WHERE " + COLUMN_MEMBID + " = " + membId +
                " ORDER BY " + COLUMN_MEMBID + " ASC";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            prefNames.add(cursor.getString(1));
        }
        db.close();

        return prefNames;
    }


}
