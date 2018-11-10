package com.example.buchin.jadwalbuchin.Homework;

import android.app.DatePickerDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buchin.jadwalbuchin.R;
import com.example.buchin.jadwalbuchin.Teacher.TeacherModel;
import com.example.buchin.jadwalbuchin.TimeTableDbHelper;

import java.util.Calendar;

public class EditHomework extends AppCompatActivity implements View.OnClickListener {
    EditText txtTitle, txtType, txtDesc;
    FloatingActionButton fabSimpan;
    TextView dt;
    Calendar c;
    TimeTableDbHelper dbAdapter;
    int day, month, year;
    String id, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_homework);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        id = getIntent().getStringExtra("idh");
        dt = (TextView) findViewById(R.id.Date);
        txtTitle = (EditText) findViewById(R.id.txtTitle);
        txtType = (EditText) findViewById(R.id.txtType);
        txtDesc = (EditText) findViewById(R.id.txtDesc);
        Toast.makeText(this, "Berhasil Di update" +id, Toast.LENGTH_SHORT).show();
        c = Calendar.getInstance();

        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

        month = month + 1;

        dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(EditHomework.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        mMonth = mMonth + 1;
                        date = mYear + "." + mMonth + "." + mDay + ".";
                        dt.setText(date);
                    }
                }, year, month, day);
                dpd.show();
            }
        });
        fabSimpan = findViewById(R.id.fab_simpan);
        fabSimpan.setOnClickListener(this);

        dbAdapter = new TimeTableDbHelper(this,null);

        HomeWorkModel homework = dbAdapter.getDataHomework(id);

        txtTitle.setText(homework.getTittle());
        txtType.setText(homework.getType());
        txtDesc.setText(homework.getDescription());
        dt.setText(homework.getDate());

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab_simpan) {

            String tittle = txtTitle.getText().toString();
            String type = txtType.getText().toString();
            String description = txtDesc.getText().toString();
            date = dt.getText().toString();
            String status = "False";

            HomeWorkModel homework = new HomeWorkModel(id, tittle, type, description, date, status);
            dbAdapter.updateHomework(homework);
            Toast.makeText(this, "Berhasil Di update", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }
}


