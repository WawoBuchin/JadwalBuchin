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
import com.example.buchin.jadwalbuchin.TimeTableDbHelper;

import java.util.Calendar;

public class InsertHomework extends AppCompatActivity implements View.OnClickListener {
    EditText txtTitle, txtType, txtDesc;
    FloatingActionButton fabSimpan;
    String date;
    TextView dt;
    Calendar c;
    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_homework);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);

        dt = (TextView) findViewById(R.id.Date);
        txtTitle = (EditText) findViewById(R.id.txtTitle);
        txtType = (EditText)findViewById(R.id.txtType);
        txtDesc= (EditText) findViewById(R.id.txtDesc);

        c = Calendar.getInstance();

        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

        month = month + 1;

        dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(InsertHomework.this, new DatePickerDialog.OnDateSetListener() {
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
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void kosongkanData(){
        txtTitle.setText("");
        txtType.setText("");
        txtDesc.setText("");
        dt.setText("");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab_simpan) {
            if (!txtTitle.getText().toString().equals("") && !txtType.getText().toString().equals("") && !txtDesc.getText().toString().equals("")
                    && !dt.getText().toString().equals("")) {

                TimeTableDbHelper dbAdapter = new TimeTableDbHelper(getBaseContext(), null);
                String tittle = txtTitle.getText().toString();
                String type = txtType.getText().toString();
                String description = txtDesc.getText().toString();
                date = dt.getText().toString();
                String status= "False";

                HomeWorkModel homework = new HomeWorkModel(tittle,type,description,date,status);
                if(dbAdapter.insertHomework(homework) != -1){
                    Toast.makeText(getBaseContext(),"Data Saved",Toast.LENGTH_SHORT).show();
                    //kosongkanData();
                    onBackPressed();
                } else{
                    Toast.makeText(getBaseContext(),"Data Error",Toast.LENGTH_SHORT).show();
                }
                dbAdapter.close();
            } else{
                Toast.makeText(getBaseContext(),"please fill in the empty field",Toast.LENGTH_SHORT).show();
            }
            }
        }
    }
