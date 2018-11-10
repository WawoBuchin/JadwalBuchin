package com.example.buchin.jadwalbuchin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    //Activity
    private final Activity activity = RegisterActivity.this;
    //Layout
    private LinearLayout linearLayout;
    //TextInputLayout
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    //TextInputEditText
    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    //AppCompatButton
    private AppCompatButton appCompatButtonRegister;
    private AppCompatButton appCompatButtonLoginNow;
    //Objects
    private TimeTableDbHelper dbHelper;
    private InputValidation inputValidation;
    private UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        initListeners();
        initObjects();

    }

    private void initViews(){
        //Layout
        linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        //TextInputLayout
        textInputLayoutName = (TextInputLayout)findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout)findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout)findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout)findViewById(R.id.textInputLayoutConfirmPassword);
        //TextInputEditText
        textInputEditTextName = (TextInputEditText)findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText)findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText)findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText)findViewById(R.id.textInputEditTextConfirmPassword);
        //AppCompatButton
        appCompatButtonRegister = (AppCompatButton)findViewById(R.id.appCompatButtonRegister);
        appCompatButtonLoginNow = (AppCompatButton)findViewById(R.id.appCompatButtonLoginNow);
    }

    private void initListeners(){
        appCompatButtonRegister.setOnClickListener(this);
        appCompatButtonLoginNow.setOnClickListener(this);
    }

    private void initObjects(){
        dbHelper = new TimeTableDbHelper(activity, null);
        inputValidation = new InputValidation(activity);
        user = new UserModel();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;
            case R.id.appCompatButtonLoginNow:
                finish();
                break;

        }
    }

    private void postDataToSQLite(){
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))){
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))){
            return;
        }
        if(!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword, textInputLayoutConfirmPassword, getString(R.string.error_password_match))){
            return;
        }

        if(!dbHelper.checkUser(textInputEditTextEmail.getText().toString().trim())){
            user.setUserName(textInputEditTextName.getText().toString().trim());
            user.setUserEmail(textInputEditTextEmail.getText().toString().trim());
            user.setUserPassword(textInputEditTextPassword.getText().toString().trim());
            Intent i = new Intent(activity, MainActivity.class);
            i.putExtra("NAME", textInputEditTextName.getText().toString().trim());
            dbHelper.insertUser(user);
            Snackbar.make(linearLayout, getString(R.string.success_message), Snackbar.LENGTH_SHORT).show();
            emptyInputEdiText();
            finish();
        }else{
            Snackbar.make(linearLayout, getString(R.string.error_email_exists), Snackbar.LENGTH_SHORT).show();

        }
    }

    private void emptyInputEdiText(){
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}
