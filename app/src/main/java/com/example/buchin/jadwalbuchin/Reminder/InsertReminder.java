package com.example.buchin.jadwalbuchin.Reminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.buchin.jadwalbuchin.R;
import com.example.buchin.jadwalbuchin.TimeTableDbHelper;

import java.util.Calendar;
import java.util.Date;

public class InsertReminder extends AppCompatActivity implements View.OnClickListener {
    EditText txtTitle, txtDesc;
    TextView txtDate, txtTime;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog timePickerDialog;
    FloatingActionButton fabSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_reminder);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);

        txtTitle = (EditText) findViewById(R.id.itTitle);
        txtDesc = (EditText) findViewById(R.id.itDesc);
        txtDate = (TextView) findViewById(R.id.tvDate);
        txtTime = (TextView) findViewById(R.id.tvTime);
        //txtColor = (TextView) findViewById(R.id.tvColor);
        fabSimpan = findViewById(R.id.fab_simpan);
        fabSimpan.setOnClickListener(this);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        InsertReminder.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog();
            }
        });
    }
    private void showTimeDialog() {
        //Calendar untuk mendapatkan waktu saat ini
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                String time = hourOfDay + "." + minute;
                txtTime.setText(time);
            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab_simpan) {
            if (!txtTitle.getText().toString().equals("") && !txtDesc.getText().toString().equals("") && !txtDate.getText().toString().equals("")
                    && !txtTime.getText().toString().equals("")) {
                TimeTableDbHelper dbAdapter = new TimeTableDbHelper(getBaseContext(), null);
                String title = txtTitle.getText().toString();
                String desc = txtDesc.getText().toString();
                String date = txtDate.getText().toString();
                String time = txtTime.getText().toString();
                //String color = txtColor.getText().toString();

                ReminderModel reminder = new ReminderModel(title, desc, date, time);
                if (dbAdapter.insertReminder(reminder) != -1) {
                    Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                    //kosongkanData();
                    //startActivity(new Intent(this, TeacherActivity.class));
                    onBackPressed();
                } else {
                    Toast.makeText(getBaseContext(), "Data Error", Toast.LENGTH_SHORT).show();
                }
                dbAdapter.close();
            } else {
                Toast.makeText(getBaseContext(), "please fill in the empty field", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void kosongkanData () {
        txtTitle.setText("");
        txtDesc.setText("");
        txtDate.setText("");
        txtTime.setText("");
        //txtColor.setText("");
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}