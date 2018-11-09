package com.example.buchin.jadwalbuchin;

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

import com.example.buchin.jadwalbuchin.Teacher.TeacherActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new Session(MainActivity.this);
        session.checkLogin();

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
                TimeTableDbHelper db = new TimeTableDbHelper(this, null);
                session = new Session(MainActivity.this);
                TextView txtEmail = (TextView)findViewById(R.id.header_title_2);
                txtEmail.setText(session.getKeyEmail());
                mDrawerLayout.openDrawer(GravityCompat.START);

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
            case R.id.nav_grades:
                startActivity(new Intent(this, GradeActivity.class));
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
            case R.id.nav_tests:
                startActivity(new Intent(this, TestActivity.class));
                break;
            case R.id.nav_logout:
                session.logoutUserId();
                this.finish();
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
