package com.example.buchin.jadwalbuchin.Teacher;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.buchin.jadwalbuchin.MainActivity;
import com.example.buchin.jadwalbuchin.R;
import com.example.buchin.jadwalbuchin.Session;
import com.example.buchin.jadwalbuchin.TimeTableDbHelper;

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rv;
    FloatingActionButton bu;
    TeacherRecyclerViewAdapter adapter;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        session = new Session(TeacherActivity.this);
        Toast.makeText(this, session.getKeyEmail() + "-" + session.getKeyName() + "-" + session.getKeyPassword(), Toast.LENGTH_SHORT).show();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        rv = findViewById(R.id.listTeacher);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        adapter = new TeacherRecyclerViewAdapter(new TimeTableDbHelper(this,null).getAllTeacher(session.getKeyEmail()));
        rv.setAdapter(adapter);
        bu = findViewById(R.id.fab);
        bu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fab){
            startActivity(new Intent(this,InsertTeacher.class));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_teacher);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);

        rv = findViewById(R.id.listTeacher);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        adapter = new TeacherRecyclerViewAdapter(new TimeTableDbHelper(this,null).getAllTeacher(session.getKeyEmail()));
        rv.setAdapter(adapter);
        bu = findViewById(R.id.fab);
        bu.setOnClickListener(this);
    }


    /*
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_teacher, popup.getMenu());
        popup.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.edit:
                // do your code
                return true;
            case R.id.delete:
                // do your code
                return true;
            default:
                return false;
        }
    }*/




}
