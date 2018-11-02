package com.example.buchin.jadwalbuchin;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TimeTableDbHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "TimeTable";

    // Table Names
    private static final String TABLE_USER = "user";
    private static final String TABLE_TEACHER = "teacher";
    private static final String TABLE_HARI = "hari";
    private static final String TABLE_HOMEWORK = "homework";
    private static final String TABLE_JADWAL = "jadwal";
    private static final String TABLE_PROPOSAL = "proposal";
    private static final String TABLE_REMINDER = "reminder";
    private static final String TABLE_TEST = "test";
    private static final String TABLE_HOLIDAY = "holiday";

    // Table Create Statements
    // Todo table create statement
    /*private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TODO
            + " TEXT," + KEY_STATUS + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";
    */


    public TimeTableDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
