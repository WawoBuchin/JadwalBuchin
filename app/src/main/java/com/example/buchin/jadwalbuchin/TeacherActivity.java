package com.example.buchin.jadwalbuchin;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtName, txtPost, txtPhone, txtEmail, txtOffice,txtOfficeHour;
    Button btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);


        txtName = (EditText) findViewById(R.id.etName);
        txtPost = (EditText) findViewById(R.id.etPost);
        txtPhone = (EditText) findViewById(R.id.etPhone);
        txtEmail = (EditText) findViewById(R.id.etEmail);
        txtOffice = (EditText) findViewById(R.id.etOffice);
        txtOfficeHour = (EditText) findViewById(R.id.etOfficehour);
        btnSimpan = (Button) findViewById(R.id.btnSimpanTeacher);
        btnSimpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnSimpanTeacher){
            if(!txtName.getText().toString().equals("") && !txtPost.getText().toString().equals("") && !txtPhone.getText().toString().equals("")
                    && !txtEmail.getText().toString().equals("") && !txtOffice.getText().toString().equals("") && !txtOfficeHour.getText().toString().equals("")){
                TimeTableDbHelper dbAdapter = new TimeTableDbHelper(getBaseContext(), null);
                String name = txtName.getText().toString();
                String post = txtPost.getText().toString();
                String phone = txtPhone.getText().toString();
                String email = txtEmail.getText().toString();
                String office = txtOffice.getText().toString();
                String officehour = txtOfficeHour.getText().toString();

                TeacherModel teacher = new TeacherModel(name,post,phone,email,office,officehour);
                if(dbAdapter.insertTeacher(teacher) != -1){
                    Toast.makeText(getBaseContext(),"Data Saved",Toast.LENGTH_SHORT).show();
                    kosongkanData();
                } else{
                    Toast.makeText(getBaseContext(),"Data Error",Toast.LENGTH_SHORT).show();
                }
                dbAdapter.close();
            } else{
                Toast.makeText(getBaseContext(),"please fill in the empty field",Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void kosongkanData(){
        txtName.setText("");
        txtPost.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtOffice.setText("");
        txtOfficeHour.setText("");
    }

}
