package com.example.buchin.jadwalbuchin;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.LinearLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    //Activity
    private final Activity activity = LoginActivity.this;
    //Layout
    private LinearLayout linearLayout;
    //TextInputLayout
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    //TextInputEditText
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    //AppCompatButton
    private AppCompatButton appCompatButtonLogin;
    private AppCompatButton appCompatButtonRegisterNow;
    //Objects
    private InputValidation inputValidation;
    private TimeTableDbHelper dbHelper;
    private UserModel user;
    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initListeners();
        initObjects();

        if (sessionManager.loggedIn()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

    }

    private void initViews(){
        //Layout
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        //TextInputLayout
        textInputLayoutEmail = (TextInputLayout)findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout)findViewById(R.id.textInputLayoutPassword);
        //TextInputEditText
        textInputEditTextEmail = (TextInputEditText)findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText)findViewById(R.id.textInputEditTextPassword);
        //AppCompatButton
        appCompatButtonLogin = (AppCompatButton)findViewById(R.id.appCompatButtonLogin);
        appCompatButtonRegisterNow = (AppCompatButton)findViewById(R.id.appCompatButtonRegisterNow);
    }

    private void initListeners(){
        appCompatButtonRegisterNow.setOnClickListener(this);
        appCompatButtonLogin.setOnClickListener(this);
    }

    private void initObjects(){
        dbHelper = new TimeTableDbHelper(activity, null);
        inputValidation = new InputValidation(activity);
        sessionManager = new SessionManager(activity);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.appCompatButtonRegisterNow:
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                break;
        }
    }

    private void verifyFromSQLite(){
        if(!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))){
            return;
        }

        if(dbHelper.checkUser(textInputEditTextEmail.getText().toString().trim(), textInputEditTextPassword.getText().toString().trim())){
            Intent i = new Intent(activity, MainActivity.class);
            i.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            emptyInputEditText();
            startActivity(i);
            sessionManager.setLoggedIn(true);
            this.finish();
        }else{
            Snackbar.make(linearLayout, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText(){
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }

}
