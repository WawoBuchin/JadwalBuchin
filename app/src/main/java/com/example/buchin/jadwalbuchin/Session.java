package com.example.buchin.jadwalbuchin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Session {
    SharedPreferences preferences;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "Jadwal";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NAME = "name";

    public Session(Context context){
        this._context = context;
        preferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void storeUser(String name, String email, String password){
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }

    public void loginUserId(String email, String password, boolean login){
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putBoolean(IS_LOGIN, login);
        editor.commit();
    }

    public String getKeyName(){
        return preferences.getString(KEY_NAME, "");
    }

    public String getKeyEmail(){
        return preferences.getString(KEY_EMAIL, "");
    }

    public String getKeyPassword(){
        return preferences.getString(KEY_PASSWORD, "");
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);

        }

    }

    public void logoutUserId(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginActivity.class);
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return preferences.getBoolean(IS_LOGIN, false);
    }
}
