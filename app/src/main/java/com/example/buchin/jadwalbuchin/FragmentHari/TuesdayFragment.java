package com.example.buchin.jadwalbuchin.FragmentHari;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buchin.jadwalbuchin.R;
import com.example.buchin.jadwalbuchin.TimeTableDbHelper;


public class TuesdayFragment extends Fragment {
    RecyclerView rv;
    FloatingActionButton bu;
    TimeTableDbHelper dbHelper;
    Context context;
    ScheduleRecyclerViewAdapter adapter;
    String day;
    public TuesdayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();
        return inflater.inflate(R.layout.fragment_tuesday, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbHelper = new TimeTableDbHelper(context,null);
        rv = getActivity().findViewById(R.id.list_schedule);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);
        day = "Tuesday";
        adapter = new ScheduleRecyclerViewAdapter(new TimeTableDbHelper(context,null).getAllSchedule(dbHelper.getColUserEmail(),"Tuesday"));
        rv.setAdapter(adapter);
        bu = getActivity().findViewById(R.id.fab);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Insert_Schedule.class);
                getActivity().startActivity(i);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        rv = getActivity().findViewById(R.id.list_schedule);

        dbHelper = new TimeTableDbHelper(context,null);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);
        adapter = new ScheduleRecyclerViewAdapter(new TimeTableDbHelper(context,null).getAllSchedule(dbHelper.getColUserEmail(),day));
        rv.setAdapter(adapter);
        bu = getActivity().findViewById(R.id.fab);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Insert_Schedule.class);
                i.putExtra("day",day);
                getActivity().startActivity(i);

            }
        });

    }
}
