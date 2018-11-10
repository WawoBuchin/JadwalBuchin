package com.example.buchin.jadwalbuchin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etRegisterName, etRegisterEmail, etRegisterPassword;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button bLogin = (Button)findViewById(R.id.btn_login_now);
        Button bRegister = (Button)findViewById(R.id.btn_register);
        etRegisterName = findViewById(R.id.name);
        etRegisterEmail = findViewById(R.id.email);
        etRegisterPassword = findViewById(R.id.password);
        bLogin.setOnClickListener(this);
        bRegister.setOnClickListener(this);

        session = new Session(RegisterActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login_now :
                startActivity(new Intent(this, LoginActivity.class));
                this.finish();
                break;
            case R.id.btn_register:
                TimeTableDbHelper db = new TimeTableDbHelper(this, null);
                String userName = etRegisterName.getText().toString().trim();
                String userEmail = etRegisterEmail.getText().toString().trim();
                String userPassword = etRegisterPassword.getText().toString().trim();
                if(userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()){
                    Toast.makeText(this, "Please fill the empty fields", Toast.LENGTH_SHORT).show();
                }else{
                    //session.storeUser(userName, userEmail, userPassword);
                    UserModel user = new UserModel(userName, userEmail, userPassword);
                    db.insertUser(user);
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(RegisterActivity.this,"Successfully Registered please Sign in",Toast.LENGTH_SHORT).show();
                }
                this.finish();
                break;
        }
    }
}
