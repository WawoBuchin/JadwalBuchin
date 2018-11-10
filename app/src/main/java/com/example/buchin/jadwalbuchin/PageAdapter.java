package com.example.buchin.jadwalbuchin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.Toast;

import com.example.buchin.jadwalbuchin.FragmentHari.FridayFragment;
import com.example.buchin.jadwalbuchin.FragmentHari.MondayFragment;
import com.example.buchin.jadwalbuchin.FragmentHari.SaturdayFragment;
import com.example.buchin.jadwalbuchin.FragmentHari.ThursdayFragment;
import com.example.buchin.jadwalbuchin.FragmentHari.TuesdayFragment;
import com.example.buchin.jadwalbuchin.FragmentHari.WednesdayFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new MondayFragment();
            case 1:
                return new TuesdayFragment();
            case 2:
                return new WednesdayFragment();
            case 3:
                return new ThursdayFragment();
            case 4:
                return new FridayFragment();
            case 5 :
                return new SaturdayFragment();
        }
    return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position){
        /*String title = getItem(position).getClass().getName();
        title.subSequence(title.lastIndexOf(".")+1, title.length());
        return super.getPageTitle(position);*/
        switch (position){
            case 0:
                //Log.i("lala","senin");
                //Toast.makeText(getBaseContext(),"please fill in the empty field",Toast.LENGTH_SHORT).show();
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            case 5:
                return "Saturday";
        }
        return null;
    }
}
