package com.example.buchin.jadwalbuchin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.buchin.jadwalbuchin.Homework.HomeWorkModel;
import com.example.buchin.jadwalbuchin.Reminder.ReminderModel;
import com.example.buchin.jadwalbuchin.FragmentHari.Schedule_Model;
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
    private static final String TABLE_SCHEDULE = "schedule";
    private static final String TABLE_PROPOSAL = "proposal";
    private static final String TABLE_REMINDER = "reminder";

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



    // Teacher Table - column names
    private static final String COL_ID_TEACHER = "id_teacher";
    private static final String COL_NAME = "name";
    private static final String COL_POST = "post";
    private static final String COL_PHONE = "phone";
    private static final String COL_EMAIL = "email";
    private static final String COL_OFFICE = "office";
    private static final String COL_OFFICEHOURS = "officehours";
    private static final String COL_USER = "user_email";

    // Schedule Table - column names
    private static final String COL_SCHEDULE_ID = "subject_id";
    private static final String COL_SCHEDULE_NAME = "subject_name";
    private static final String COL_SCHEDULE_TEACHER = "subject_teacher";
    private static final String COL_SCHEDULE_ROOM = "subject_room";
    private static final String COL_SCHEDULE_TIME = "subject_time";
    private static final String COL_SCHEDULE_DAY = "subject_day";

    // user table create statement
    private static final String CREATE_TABLE_TEACHER = " CREATE TABLE "
            + TABLE_TEACHER + "(" + COL_ID_TEACHER + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME
            + " TEXT,"+ COL_POST + " TEXT," + COL_PHONE + " TEXT," + COL_EMAIL + " TEXT," + COL_OFFICE
            + " TEXT,"+ COL_OFFICEHOURS + " TEXT ," +COL_USER+" TEXT )";

    // Schedule table create statement
    private static final String CREATE_TABLE_SCHEDULE = " CREATE TABLE "
            + TABLE_SCHEDULE + "(" + COL_SCHEDULE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_SCHEDULE_NAME
            + " TEXT,"+ COL_SCHEDULE_TEACHER + " TEXT," + COL_SCHEDULE_ROOM + " TEXT," + COL_SCHEDULE_TIME + " TEXT," + COL_SCHEDULE_DAY
            + " TEXT,"+ COL_USER +" TEXT )";

    // Reminder Table - column names
    private static final String COL_ID_REMINDER = "id_reminder";
    //private static final String COL_USERNAME = "username";
    private static final String COL_TITLE = "title";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_DATE = "date";
    private static final String COL_TIME = "time";
    //private static final String COL_ID_COLOR = "id_color";


    // reminder table create statement
    private static final String CREATE_TABLE_REMINDER = " CREATE TABLE "
            + TABLE_REMINDER + "(" + COL_ID_REMINDER + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_TITLE
            + " TEXT,"+ COL_DESCRIPTION + " TEXT," + COL_DATE + " TEXT," + COL_TIME + " TEXT )";


    public TimeTableDbHelper(Context context, SQLiteDatabase.CursorFactory factory){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TABLE_TEACHER);
        db.execSQL(CREATE_TABLE_SCHEDULE);
        db.execSQL(CREATE_TABLE_REMINDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOMEWORK);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDER);
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
        values.put(COL_USER, teacher.getUser_Email());

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
            username = cursor.getString(0);
//            do{
//                username = cursor.getString(0);
//            }while (cursor.moveToNext());
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

    public ArrayList<TeacherModel> getAllTeacher(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<TeacherModel> listTeacher = new ArrayList<>();
        String sql = " SELECT * FROM " + TABLE_TEACHER + " WHERE " + COL_USER + "  =  '" + user +"' ";
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

    public List<String> getAllTeachers(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> listTeachers = new ArrayList<>();
        String sql = " SELECT "+ COL_ID_TEACHER + " , " + COL_NAME +" FROM " + TABLE_TEACHER + " WHERE " + COL_USER + "  =  '" + user +"' ";
        Cursor cursor = db.rawQuery(sql,null);
        Log.d("haha", "kosong" + cursor.getCount());
        if(cursor != null && cursor.moveToFirst()){

            cursor.moveToFirst();
            listTeachers.add(" please select ");
            do{

                listTeachers.add(cursor.getString(1));
            }while(cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        return listTeachers;
    }


    public void deleteTeacher(String id_Teacher){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TEACHER,COL_ID_TEACHER + " = ?", new String[]{id_Teacher});
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

        db.update(TABLE_TEACHER, values,COL_ID_TEACHER + " = ?" , new String[]{teacher.getID_Teacher()});
        db.close();
    }

    public TeacherModel getDataTeacher(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_TEACHER + " WHERE " + COL_ID_TEACHER + " = " + id ;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        return new TeacherModel(cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4), cursor.getString(5),cursor.getString(6));

    }

    public long insertSchedule(Schedule_Model schedule) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_SCHEDULE_NAME, schedule.getSubject_Name());
        values.put(COL_SCHEDULE_TEACHER, schedule.getSubject_Teacher());
        values.put(COL_SCHEDULE_ROOM, schedule.getSubject_Room());
        values.put(COL_SCHEDULE_TIME, schedule.getSubject_Time());
        values.put(COL_SCHEDULE_DAY, schedule.getSubject_Day());
        values.put(COL_USER, schedule.getUser_Email());

        // insert row
        long sequence = db.insert(TABLE_SCHEDULE, null, values);


        return sequence;
    }

    public ArrayList<Schedule_Model> getAllSchedule(String user,String day){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Schedule_Model> listSchedule = new ArrayList<>();
        String sql = " SELECT * FROM " + TABLE_SCHEDULE + " WHERE " + COL_USER + "  =  '" + user +"' AND " + COL_SCHEDULE_DAY + " = '" + day +"'  ";
        Cursor cursor = db.rawQuery(sql,null);
        Log.d("haha", "kosong" + cursor.getCount());
        if(cursor != null && cursor.moveToFirst()){

            cursor.moveToFirst();
            do{
                listSchedule.add(new Schedule_Model(cursor.getString(1),cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getString(0)));
            }while(cursor.moveToNext());
        }
        return listSchedule;
    }



    public void deleteSchedule(String schedule_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCHEDULE,COL_SCHEDULE_ID +" = ? ", new String[]{schedule_id});
    }

    public void updateSchedule(Schedule_Model schedule){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_SCHEDULE_NAME, schedule.getSubject_Name());
        values.put(COL_SCHEDULE_TEACHER, schedule.getSubject_Teacher());
        values.put(COL_SCHEDULE_ROOM, schedule.getSubject_Room());
        values.put(COL_SCHEDULE_TIME, schedule.getSubject_Time());

        db.update(TABLE_SCHEDULE, values,COL_SCHEDULE_ID + " = ?" , new String[]{schedule.getSubject_Id()});
        db.close();
    }

    public Schedule_Model getDataSchedule(String schedule_id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_SCHEDULE + " WHERE " + COL_SCHEDULE_ID + " = " + schedule_id ;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        return new Schedule_Model(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

    }
    //REMINDER CRUD
    public long insertReminder(ReminderModel reminder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TITLE, reminder.getTittle());
        values.put(COL_DESCRIPTION, reminder.getDescription());
        values.put(COL_DATE, reminder.getDate());
        values.put(COL_TIME, reminder.getTime());
        //values.put(COL_ID_COLOR, reminder.getID_Color());

        // insert row
        long sequence = db.insert(TABLE_REMINDER, null, values);


        return sequence;
    }


    public ArrayList<ReminderModel> getAllReminder(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ReminderModel> listReminder = new ArrayList<>();
        String sql = " SELECT * FROM " + TABLE_REMINDER;
        Cursor cursor = db.rawQuery(sql,null);
        Log.d("haha", "kosong" + cursor.getCount());
        if(cursor != null && cursor.moveToFirst()){

            cursor.moveToFirst();
            do{
                listReminder.add(new ReminderModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                        cursor.getString(3), cursor.getString(4)));
            }while(cursor.moveToNext());
        }
        return listReminder;
    }



    public void deleteReminder(String id_Reminder){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REMINDER,"id_reminder = ?", new String[]{id_Reminder});
    }

    public void updateReminder(ReminderModel reminder){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_TITLE, reminder.getTittle());
        values.put(COL_DESCRIPTION, reminder.getDescription());
        values.put(COL_DATE, reminder.getDate());
        values.put(COL_TIME, reminder.getTime());
        //values.put(COL_ID_COLOR, reminder.getID_Color());

        db.update(TABLE_REMINDER, values,"id_reminder = ?" , new String[]{reminder.getID_Reminder()});
        db.close();
    }

    public ReminderModel getDataReminder(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_REMINDER + " WHERE " + COL_ID_REMINDER + " = " + id ;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        return new ReminderModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                cursor.getString(3), cursor.getString(4));

    }
    //HOMEWORK
    public long insertHomework(HomeWorkModel homework) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_TITLE, homework.getTittle());
        values.put(COL_TYPE, homework.getType());
        values.put(COL_DESCRIPT, homework.getDescription());
        values.put(COL_DATE, homework.getDate());
        values.put(COL_STATUS, homework.getStatus());

        // insert row
        long sequence = db.insert(TABLE_HOMEWORK, null, values);


        return sequence;
    }
    public ArrayList<HomeWorkModel> getAllHomework(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HomeWorkModel> listHomework = new ArrayList<>();
        String sql = " SELECT * FROM " + TABLE_HOMEWORK;
        Cursor cursor = db.rawQuery(sql,null);
        Log.d("haha", "kosong" + cursor.getCount());
        if(cursor != null && cursor.moveToFirst()){

            cursor.moveToFirst();
            do{
                listHomework.add(new HomeWorkModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                        cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }while(cursor.moveToNext());
        }
        return listHomework;
    }

    public void deleteHomework(String id_Homework){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HOMEWORK,"id_homework = ?", new String[]{id_Homework});
    }

    public void updateHomework(HomeWorkModel homework){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_TITLE, homework.getTittle());
        values.put(COL_TYPE, homework.getType());
        values.put(COL_DESCRIPT, homework.getDescription());
        values.put(COL_DATE, homework.getDate());
        values.put(COL_STATUS, homework.getStatus());

        db.update(TABLE_HOMEWORK, values,"id_homework = ?" , new String[]{homework.getID_HomeWork()});
        db.close();
    }

    public HomeWorkModel getDataHomework(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_HOMEWORK + " WHERE " + COL_ID_Homework + " = " + id ;
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        return new HomeWorkModel(cursor.getString(1),cursor.getString(2),cursor.getString(3),
                cursor.getString(4));

    }

}
