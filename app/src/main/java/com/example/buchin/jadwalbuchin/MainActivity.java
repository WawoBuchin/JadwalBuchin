package com.example.buchin.jadwalbuchin;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buchin.jadwalbuchin.Reminder.ReminderActivity;
import com.example.buchin.jadwalbuchin.Teacher.TeacherActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Activity activity = MainActivity.this;
    private DrawerLayout mDrawerLayout;

    SessionManager sessionManager;
    TimeTableDbHelper dbHelper;
    private TextView textViewEmail, textViewName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new TimeTableDbHelper(activity, null);
        dbHelper.getWritableDatabase();
        sessionManager = new SessionManager(this);
        Toast.makeText(activity, "", Toast.LENGTH_SHORT).show();
        if (!sessionManager.loggedIn()){
            Toast.makeText(activity, "You Are Not Logged In", Toast.LENGTH_SHORT).show();
            logOut();
        }else{
            Toast.makeText(activity, "You Are Logged In", Toast.LENGTH_SHORT).show();
        }


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TabItem tabMonday = findViewById(R.id.monday_tab);
        TabItem tabTuesday = findViewById(R.id.monday_tab);
        TabItem tabWednesday = findViewById(R.id.wednesday_tab);
        TabItem tabThursday = findViewById(R.id.thursday_tab);
        TabItem tabFriday = findViewById(R.id.friday_tab);
        TabItem tabSaturday = findViewById(R.id.saturday_tab);
        ViewPager viewPager = findViewById(R.id.view_pager);
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewEmail.setText(emailFromIntent);*/

        /*
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        switch (menuItem.getItemId()){

                        }
                        return true;
                    }
                }
        );*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                dbHelper = new TimeTableDbHelper(activity, null);
                //dbHelper.getWritableDatabase();
                mDrawerLayout.openDrawer(GravityCompat.START);

                textViewEmail = findViewById(R.id.header_title_2);

                textViewName = findViewById(R.id.header_title_1);

                textViewEmail.setText(dbHelper.getColUserEmail());
                textViewName.setText(dbHelper.getColUserName());


                return true;

        }
        return super.onOptionsItemSelected(item);
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()){
            case R.id.nav_teachers:
                startActivity(new Intent(this, TeacherActivity.class));
                break;
            case R.id.nav_holidays:
                startActivity(new Intent(this, HolidayActivity.class));
                break;
            case R.id.nav_reminders:
                startActivity(new Intent(this, ReminderActivity.class));
                break;
            case R.id.nav_homeworks:
                startActivity(new Intent(this, HomeWorkActivity.class));
                break;
            case R.id.nav_logout:
                logOut();
                break;
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logOut(){
        sessionManager.setLoggedIn(false);
        finish();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

}
