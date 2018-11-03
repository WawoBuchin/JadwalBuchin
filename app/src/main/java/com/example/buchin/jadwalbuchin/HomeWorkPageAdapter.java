package com.example.buchin.jadwalbuchin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class HomeWorkPageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    public HomeWorkPageAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new UpcomingFragment();
            case 1:
                return new CompletedFragment();
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
                return "Upcoming";
            case 1:
                return "Completed";
        }
        return null;
    }
}
