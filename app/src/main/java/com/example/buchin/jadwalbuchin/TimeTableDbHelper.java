package com.example.buchin.jadwalbuchin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.buchin.jadwalbuchin.Teacher.TeacherModel;

import java.util.ArrayList;
import java.util.List;

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

    // Common column names String username, String password, String name, String email
    // User Table - column names
    private static final String COL_USER_ID = "id";
    private static final String COL_USER_NAME = "name";
    private static final String COL_USER_EMAIL = "email";
    private static final String COL_USER_PASSWORD = "password";

    // Table Create Statements
    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_USER_NAME + " TEXT, "
            + COL_USER_EMAIL + " TEXT, " + COL_USER_PASSWORD + " TEXT)";


    // Holiday Table - column names
    private static final String COL_ID_HOLIDAY = "id_holiday";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_FROM = "from";
    private static final String COL_TO = "email";
    private static final String COL_ID_COLOR = "id_color";


    // Teacher Table - column names
    private static final String COL_ID_TEACHER = "id_teacher";
    private static final String COL_NAME = "name";
    private static final String COL_POST = "post";
    private static final String COL_PHONE = "phone";
    private static final String COL_EMAIL = "email";
    private static final String COL_OFFICE = "office";
    private static final String COL_OFFICEHOURS = "officehours";
    private static final String COL_USERNAME = "username";

    // user table create statement
    private static final String CREATE_TABLE_TEACHER = " CREATE TABLE "
            + TABLE_TEACHER + "(" + COL_ID_TEACHER + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME
            + " TEXT,"+ COL_POST + " TEXT," + COL_PHONE + " TEXT," + COL_EMAIL + " TEXT," + COL_OFFICE
            + " TEXT,"+ COL_OFFICEHOURS + " TEXT )";

    public TimeTableDbHelper(Context context, SQLiteDatabase.CursorFactory factory){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TABLE_TEACHER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // create new tables
        onCreate(db);
    }

    public long insertTeacher(TeacherModel teacher) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, teacher.getName());
        values.put(COL_POST, teacher.getPost());
        values.put(COL_PHONE, teacher.getPhone());
        values.put(COL_EMAIL, teacher.getEmail());
        values.put(COL_OFFICE, teacher.getOffice());
        values.put(COL_OFFICEHOURS, teacher.getOfficeHours());

        // insert row
        long sequence = db.insert(TABLE_TEACHER, null, values);


        return sequence;
    }

    /*Method untuk membuat user*/
    public void insertUser( UserModel user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_NAME, user.getUserName());
        values.put(COL_USER_EMAIL, user.getUserEmail());
        values.put(COL_USER_PASSWORD, user.getUserPassword());
        db.insert(TABLE_USER, null, values);

    }

    /*Method untuk mengambil dan me-return semua list user*/
    public List<UserModel> getAllUser(){
        String[] col = {
                COL_USER_ID, COL_USER_NAME, COL_USER_EMAIL, COL_USER_PASSWORD
        };
        String sortOrder = COL_USER_NAME + " ASC";

        List<UserModel>userList = new ArrayList<UserModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER,
                col, null, null, null, null, sortOrder);
        if(cursor.moveToFirst()){
            do {
                UserModel user = new UserModel();
                user.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_USER_ID))));
                user.setUserName(cursor.getString(cursor.getColumnIndex(COL_USER_NAME)));
                user.setUserEmail(cursor.getString(cursor.getColumnIndex(COL_USER_EMAIL)));
                user.setUserPassword(cursor.getString(cursor.getColumnIndex(COL_USER_PASSWORD)));
                userList.add(user);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return userList;
    }

    /*Method untuk update user*/
    public void updateUser(UserModel user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_NAME, user.getUserName());
        values.put(COL_USER_EMAIL, user.getUserEmail());
        values.put(COL_USER_PASSWORD, user.getUserPassword());
        db.update(TABLE_USER, values, COL_USER_ID + " = ?", new String[]{String.valueOf(user.getUserId())});
        db.close();
    }

    /*Method untuk hapus user*/
    public void deleteUser(UserModel user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, COL_USER_ID + " = ?", new String[]{String.valueOf(user.getUserId())});
    }

    /*Method untuk memastkan user sudah ada atau belum*/
    public boolean checkUser(String email){
        String[] col ={COL_USER_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_USER_EMAIL + " = ?";
        String[]selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USER, col, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();

        if(cursorCount > 0){
            return true;
        }
        return false;
    }

    public String getColUserEmail(){
        String username = "";
        Cursor cursor = this.getReadableDatabase().query(TABLE_USER, new String[]{COL_USER_EMAIL}, null, null, null, null, null);
        int cursorCount = cursor.getCount();
        if (cursorCount > 0){
            cursor.moveToFirst();
            do{
                username = cursor.getString(0);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return username;
    }

    public String getColUserName(){
        String username = "";
        Cursor cursor = this.getReadableDatabase().query(TABLE_USER, new String[]{COL_USER_NAME}, null, null, null, null, null);
        int cursorCount = cursor.getCount();
        if (cursorCount > 0){
            cursor.moveToFirst();
            do{
                username = cursor.getString(0);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return username;
    }

    public boolean checkUser(String email, String password){
        String[] col ={COL_USER_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_USER_EMAIL + " = ?" + " AND " + COL_USER_PASSWORD + " = ?";
        String[]selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USER, col, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        if (cursorCount > 0){
            return true;
        }
        return false;

    }

    public ArrayList<TeacherModel> getAllTeacher(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<TeacherModel> listTeacher = new ArrayList<>();
        String sql = " SELECT * FROM " + TABLE_TEACHER;
        Cursor cursor = db.rawQuery(sql,null);
        Log.d("haha", "kosong" + cursor.getCount());
        if(cursor != null && cursor.moveToFirst()){

            cursor.moveToFirst();
            do{
                listTeacher.add(new TeacherModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            }while(cursor.moveToNext());
        }
        return listTeacher;
    }

    public void deleteTeacher(String id_Teacher){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TEACHER,"id_teacher = ?", new String[]{id_Teacher});
    }

    public void updateTeacher(TeacherModel teacher){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NAME, teacher.getName());
        values.put(COL_POST, teacher.getPost());
        values.put(COL_PHONE, teacher.getPhone());
        values.put(COL_EMAIL, teacher.getEmail());
        values.put(COL_OFFICE, teacher.getOffice());
        values.put(COL_OFFICEHOURS, teacher.getOfficeHours());

        db.update(TABLE_TEACHER, values,"id_teacher = ?" , new String[]{teacher.getID_Teacher()});
        db.close();
    }

    public TeacherModel getDataTeacher(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_TEACHER + " WHERE " + COL_ID_TEACHER + " = " + id ;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        return new TeacherModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getString(6));

    }


}
