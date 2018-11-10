package com.example.buchin.jadwalbuchin.Homework;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.se.omapi.Session;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buchin.jadwalbuchin.R;
import com.example.buchin.jadwalbuchin.Teacher.TeacherRecyclerViewAdapter;
import com.example.buchin.jadwalbuchin.TimeTableDbHelper;


public class UpcomingFragment extends Fragment {
    RecyclerView rv;
    HomeworkRecyclerViewAdapter adapter;
    FloatingActionButton fab;
    Context context;

    public UpcomingFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        rv = getActivity().findViewById(R.id.listHomework);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);
        adapter = new HomeworkRecyclerViewAdapter(new TimeTableDbHelper(context,null).getAllHomework());
        rv.setAdapter(adapter);

        fab = getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),InsertHomework.class);
                getActivity().startActivity(in);
            }
        });
    }


}
