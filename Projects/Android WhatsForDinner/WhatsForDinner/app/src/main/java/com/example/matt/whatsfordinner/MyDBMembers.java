package com.example.matt.whatsfordinner;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class MyDBMembers extends SQLiteOpenHelper{


    private static final int DATABASE_VERSION = 12;
    private static final String DATABASE_NAME = "MemberDB";
    private static final String TABLE_MEMBERS = "members";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FAMID = "famid";
    private static final String COLUMN_NAME = "name";

    public MyDBMembers(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_MEMBERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FAMID + " INT ," +
                COLUMN_NAME + " VARCHAR2(30) " +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBERS);
        onCreate(sqLiteDatabase);
    }

    //WORKING HERE! ITS NOT WORKING CORRECTLY YET
    public void addMember(String membName, int famIdToAddTo){

        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("INSERT INTO " + TABLE_MEMBERS + " ("
                + COLUMN_FAMID + ", " + COLUMN_NAME + ")  VALUES (?, ?)");
        stmt.bindLong(1,famIdToAddTo);
        stmt.bindString(2,membName);
        stmt.execute();
        stmt.close();
        db.close();

    }

    public void removeMemeber(String membName){
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("DELETE FROM " + TABLE_MEMBERS + " WHERE "+ COLUMN_ID + "= ?");
        stmt.bindLong(1, getMembId(membName));
        stmt.execute();
        stmt.close();
        db.close();
    }

    public int getMembId(String thisName){
        int memberID = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_MEMBERS +
                " WHERE " + COLUMN_NAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(thisName)});
        while (cursor.moveToNext()){
            memberID = cursor.getInt(0);
        }
        db.close();

        return memberID;
    }

    public List getMembersString(int famId){
        List memberNames = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_FAMID + ", " + COLUMN_NAME + " from " + TABLE_MEMBERS + " WHERE " + COLUMN_FAMID + " = " + famId +
                " ORDER BY " + COLUMN_FAMID + " ASC";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            memberNames.add(cursor.getString(1));
        }
        db.close();

        return memberNames;
    }
}
