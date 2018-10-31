package com.durga.balaji66.ceatinglocaldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="Candidate.db";
    private static final int DATABASE_VERSION =2;
    private static final String TABLE_NAME = "candidate1";
    private static final String CANDIDATE_ID ="id";
    private static final String CANDIDATE_NAME ="name";
    private static final String CANDIDATE_PHONE ="phone";
    private static final String CANDIDATE_EMAIL ="email";
    private static final String CANDIDATE_PASSWORD="password";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = " CREATE TABLE " + TABLE_NAME + "(" +
                CANDIDATE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CANDIDATE_NAME + " TEXT, " +
                CANDIDATE_PHONE + " TEXT, " +
                CANDIDATE_EMAIL + " TEXT, " +
                CANDIDATE_PASSWORD + " TEXT " + ")" ;
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_CANDIDATE_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_CANDIDATE_TABLE);
        onCreate(db);
    }

    void AddNewUser(UserModelClass user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CANDIDATE_NAME,user.getName());
        contentValues.put(CANDIDATE_PHONE, user.getPhone());
        contentValues.put(CANDIDATE_EMAIL,user.getEmail());
        contentValues.put(CANDIDATE_PASSWORD, user.getPassword());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }
    boolean checkCandidateIsRegisteredOrNot(String phone)
    {
        String[] columns ={ CANDIDATE_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = CANDIDATE_PHONE + " = ?" ;
        String[] selectionArguments = {phone};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArguments,null,null,null);
        int cursorCount =- cursor.getCount();
        cursor.close();
        db.close();
        return cursorCount > 0;

    }
}
