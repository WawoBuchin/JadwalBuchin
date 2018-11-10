package com.example.buchin.jadwalbuchin.Teacher;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buchin.jadwalbuchin.R;
import com.example.buchin.jadwalbuchin.TimeTableDbHelper;

public class EditTeacher extends AppCompatActivity implements View.OnClickListener {
    EditText txtName, txtPost, txtPhone, txtEmail, txtOffice,txtOfficeHour;
    FloatingActionButton bSimpan;
    TimeTableDbHelper dbHelper;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teacher);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        id = getIntent().getStringExtra("id");
        txtName = (EditText) findViewById(R.id.etName);
        txtPost = (EditText) findViewById(R.id.etPost);
        txtPhone = (EditText) findViewById(R.id.etPhone);
        txtEmail = (EditText) findViewById(R.id.etEmail);
        txtOffice = (EditText) findViewById(R.id.etOffice);
        txtOfficeHour = (EditText) findViewById(R.id.etOfficehour);

        dbHelper = new TimeTableDbHelper(this,null);
        TeacherModel teacher = dbHelper.getDataTeacher(id);
        bSimpan = findViewById(R.id.fab_simpan);
        bSimpan.setOnClickListener(this);

        txtName.setText(teacher.getName());
        txtPost.setText(teacher.getPost());
        txtPhone.setText(teacher.getPhone());
        txtEmail.setText(teacher.getEmail());
        txtOffice.setText(teacher.getOffice());
        txtOfficeHour.setText(teacher.getOfficeHours());

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fab_simpan){
            //Toast.makeText(this,"ini"+ getIntent().getStringExtra("id"),Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(this,InsertTeacher.class));
            String nama = txtName.getText().toString();
            String post = txtPost.getText().toString();
            String phone = txtPhone.getText().toString();
            String email = txtEmail.getText().toString();
            String office = txtOffice.getText().toString();
            String officehour = txtOfficeHour.getText().toString();

            TeacherModel teacher = new TeacherModel(id,nama,post,phone,email,office,officehour);
            dbHelper.updateTeacher(teacher);
            Toast.makeText(this,"Berhasil Di update",Toast.LENGTH_SHORT).show();
            onBackPressed();
            //startActivity(new Intent(this, TeacherActivity.class));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
