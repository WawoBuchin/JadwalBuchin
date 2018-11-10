package com.example.buchin.jadwalbuchin.FragmentHari;

import android.app.TimePickerDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import java.util.Calendar;
import java.util.List;

public class Edit_Schedule extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    TextView from,to;
    Spinner spiner_Teacher;
    String teacher,id,teacher_name,string_From,string_To;
    EditText et_SubjectName,et_SubjectRoom;
    FloatingActionButton fabUpdateSchedule;
    Session session;
    TimeTableDbHelper dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule);

        session = new Session(Edit_Schedule.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);

        id = getIntent().getStringExtra("schedule_id");
        from = (TextView) findViewById(R.id.update_subject_from);
        to = (TextView) findViewById(R.id.update_subject_to);
        et_SubjectName = (EditText) findViewById(R.id.update_subject_name);
        et_SubjectRoom = (EditText) findViewById(R.id.update_subject_room);
        fabUpdateSchedule = findViewById(R.id.fab_simpan);
        fabUpdateSchedule.setOnClickListener(this);

        dbAdapter = new TimeTableDbHelper(this,null);
        Schedule_Model schedule = dbAdapter.getDataSchedule(id);


        teacher_name = schedule.getSubject_Teacher();
        string_From = schedule.getSubject_Time().split("-")[0].trim();
        string_To = schedule.getSubject_Time().split("-")[1].trim();
        et_SubjectName.setText(schedule.getSubject_Name());
        et_SubjectRoom.setText(schedule.getSubject_Room());
        from.setText(string_From);
        to.setText(string_To);
        // Spinner element
        spiner_Teacher = (Spinner) findViewById(R.id.update_subject_teacher);

        // Spinner click listener
        spiner_Teacher.setOnItemSelectedListener(this);
        loadSpinnerData();

        // perform click event listener on edit text
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = Integer.parseInt(string_From.split(":")[0].trim());
                int minute = Integer.parseInt(string_From.split(":")[1].trim());
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Edit_Schedule.this, new TimePickerDialog.OnTimeSetListener() {
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
                int hour = Integer.parseInt(string_To.split(":")[0].trim());
                int minute = Integer.parseInt(string_To.split(":")[1].trim());
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Edit_Schedule.this, new TimePickerDialog.OnTimeSetListener() {
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
        int selectionPosition= dataAdapter.getPosition(teacher_name);
        spiner_Teacher.setSelection(selectionPosition);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fab_simpan){
            //Toast.makeText(getBaseContext(),"Data Saved" + et_SubjectName.getText().toString() + teacher + et_SubjectRoom.getText().toString(),Toast.LENGTH_SHORT).show();
            if(!et_SubjectName.getText().toString().equals("") && !teacher.equals(" please select ") && !et_SubjectRoom.getText().toString().equals("")
                    && !to.getText().equals("") && !from.getText().equals("")){
                dbAdapter = new TimeTableDbHelper(getBaseContext(), null);
                String name = et_SubjectName.getText().toString();
                String room = et_SubjectRoom.getText().toString();
                String time = from.getText().toString() + " - " + to.getText().toString();
                Schedule_Model schedule = new Schedule_Model(id,name,teacher,room,time);
                dbAdapter.updateSchedule(schedule);
                onBackPressed();
                dbAdapter.close();

            } else{
                Toast.makeText(getBaseContext(),"please fill in the empty field",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        teacher = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
