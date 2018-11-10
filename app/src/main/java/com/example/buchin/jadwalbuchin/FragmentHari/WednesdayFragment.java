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


public class WednesdayFragment extends Fragment {
//    RecyclerView rv;
//    FloatingActionButton bu;
//    Session session;
//    Context context;
//    ScheduleRecyclerViewAdapter adapter;
//    String day = "Wednesday";
    public WednesdayFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //context = container.getContext();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wednesday, container, false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        session = new Session(context);
//        rv = getActivity().findViewById(R.id.list_schedule);
//        rv.setHasFixedSize(true);
//        LinearLayoutManager llm = new LinearLayoutManager(context);
//        rv.setLayoutManager(llm);
//        adapter = new ScheduleRecyclerViewAdapter(new TimeTableDbHelper(context,null).getAllSchedule(session.getKeyEmail(),day));
//        rv.setAdapter(adapter);
//        bu = getActivity().findViewById(R.id.fab);
//        bu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), Insert_Schedule.class);
//                i.putExtra("day",day);
//                getActivity().startActivity(i);
//            }
//        });
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        session = new Session(context);
//        rv = getActivity().findViewById(R.id.list_schedule);
//        rv.setHasFixedSize(true);
//        LinearLayoutManager llm = new LinearLayoutManager(context);
//        rv.setLayoutManager(llm);
//        adapter = new ScheduleRecyclerViewAdapter(new TimeTableDbHelper(context,null).getAllSchedule(session.getKeyEmail(),day));
//        rv.setAdapter(adapter);
//        bu = getActivity().findViewById(R.id.fab);
//        bu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), Insert_Schedule.class);
//                i.putExtra("day",day);
//                getActivity().startActivity(i);
//
//            }
//        });
//
//    }

}
