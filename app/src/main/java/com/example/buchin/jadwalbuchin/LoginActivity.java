package com.example.buchin.jadwalbuchin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etLoginEmail, etLoginPassword;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button bRegister = (Button)findViewById(R.id.btn_register_now);
        Button bLogin = (Button)findViewById(R.id.btn_login);
        etLoginEmail = (EditText)findViewById(R.id.email);
        etLoginPassword = (EditText)findViewById(R.id.password);
        bRegister.setOnClickListener(this);
        bLogin.setOnClickListener(this);

        session = new Session(LoginActivity.this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register_now :
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btn_login:
                TimeTableDbHelper db = new TimeTableDbHelper(this, null);
                String loginUserEmail = etLoginEmail.getText().toString().trim();
                String loginUserPassword = etLoginPassword.getText().toString().trim();
                if(loginUserEmail.isEmpty() || loginUserPassword.isEmpty()){
                    Toast.makeText(this, "Please Fill in the blank field", Toast.LENGTH_SHORT).show();
                }else{
                    sessionLogin(loginUserEmail, loginUserPassword);
                }
                break;
        }
    }

    private void sessionLogin(String loginUserEmail, String loginUserPassword) {
        UserModel user = new UserModel(loginUserPassword, loginUserEmail);
        if (loginUserEmail.equalsIgnoreCase(user.getUserEmail())&& loginUserPassword.equalsIgnoreCase(user.getUserPassword())){
            session.loginUserId(user.getUserEmail(), user.getUserPassword(), true);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            this.finish();
        }else{
            Toast.makeText(this, "Wrong Email or Password!", Toast.LENGTH_SHORT).show();
        }
        /*if(loginUserEmail.equalsIgnoreCase(session.getKeyEmail()) && loginUserPassword.equalsIgnoreCase(session.getKeyPassword())){
            session.loginUserId(loginUserEmail, loginUserPassword, true);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            this.finish();
        }else{
            Toast.makeText(this, "Wrong Email or Password!", Toast.LENGTH_SHORT).show();
        }*/
    }

}
