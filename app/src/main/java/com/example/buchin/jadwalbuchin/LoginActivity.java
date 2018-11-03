package com.example.buchin.jadwalbuchin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button bRegister = (Button)findViewById(R.id.btn_register_now);
        Button bLogin = (Button)findViewById(R.id.btn_login);
        bRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register_now :
                startActivity(new Intent(this, RegisterActivity.class));
                this.finish();
                break;
        }
    }
}
