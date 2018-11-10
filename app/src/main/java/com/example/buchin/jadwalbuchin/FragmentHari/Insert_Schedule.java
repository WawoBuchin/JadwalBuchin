package com.example.buchin.jadwalbuchin.FragmentHari;

import android.app.TimePickerDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.buchin.jadwalbuchin.R;
import com.example.buchin.jadwalbuchin.Session;
import com.example.buchin.jadwalbuchin.TimeTableDbHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Insert_Schedule extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    TextView from,to;
    Spinner spiner_Teacher;
    String teacher,day;
    EditText et_SubjectName,et_SubjectRoom;
    FloatingActionButton fabSimpan;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_schedule);

        session = new Session(Insert_Schedule.this);
        day = getIntent().getStringExtra("day");
        from = (TextView) findViewById(R.id.insert_subject_from);
        to = (TextView) findViewById(R.id.insert_subject_to);
        et_SubjectName = (EditText) findViewById(R.id.insert_subject_name);
        et_SubjectRoom = (EditText) findViewById(R.id.insert_subject_room);
        fabSimpan = findViewById(R.id.fab_simpan);
        fabSimpan.setOnClickListener(this);

        // Spinner element
        spiner_Teacher = (Spinner) findViewById(R.id.insert_subject_teacher);

        // Spinner click listener
        spiner_Teacher.setOnItemSelectedListener(this);
        loadSpinnerData();


        // perform click event listener on edit text
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Insert_Schedule.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        from.setText(String.valueOf(selectedHour) + ":" + String.valueOf(selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("CHOOSE TIME");
                mTimePicker.show();
            }
        });

        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Insert_Schedule.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        to.setText(String.valueOf(selectedHour) + ":" + String.valueOf(selectedMinute));
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("CHOOSE TIME");
                mTimePicker.show();
            }
        });
    }


    private void loadSpinnerData(){
        // database handler
        TimeTableDbHelper db = new TimeTableDbHelper(this,null);

        // Spinner Drop down elements
        List<String> lables = db.getAllTeachers(session.getKeyEmail());

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spiner_Teacher.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fab_simpan){
            //Toast.makeText(getBaseContext(),"Data Saved" + et_SubjectName.getText().toString() + teacher + et_SubjectRoom.getText().toString(),Toast.LENGTH_SHORT).show();
            if(!et_SubjectName.getText().toString().equals("") && !teacher.equals(" please select ") && !et_SubjectRoom.getText().toString().equals("")
                    && !to.getText().equals("") && !from.getText().equals("")){
                TimeTableDbHelper dbAdapter = new TimeTableDbHelper(getBaseContext(), null);
                String name = et_SubjectName.getText().toString();
                String room = et_SubjectRoom.getText().toString();
                String time = from.getText().toString() + " - " + to.getText().toString();

                Schedule_Model schedule = new Schedule_Model(name,teacher,room,time,day,session.getKeyEmail());
                if(dbAdapter.insertSchedule(schedule) != -1){
                    Toast.makeText(getBaseContext(),"Data Saved",Toast.LENGTH_SHORT).show();
                    //kosongkanData();
                    //startActivity(new Intent(this, TeacherActivity.class));
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
         teacher = adapterView.getItemAtPosition(position).toString();
        //Toast.makeText(adapterView.getContext(), "You selected: " + teacher,
                //Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
