package com.example.buchin.jadwalbuchin.FragmentHari;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buchin.jadwalbuchin.R;
import com.example.buchin.jadwalbuchin.TimeTableDbHelper;

import java.util.ArrayList;

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ViewHolder>  {

    private ArrayList<Schedule_Model> listSchedule;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvTeacher,tvRoom,tvTime,tool_schedule;
        String id;
        Schedule_Model schedule_model;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id="";
            schedule_model = new Schedule_Model();
            tvName = itemView.findViewById(R.id.subject_name);
            tvTeacher = itemView.findViewById(R.id.subject_teacher);
            tvRoom = itemView.findViewById(R.id.subject_room);
            tvTime = itemView.findViewById(R.id.subject_time);
            tool_schedule = itemView.findViewById(R.id.tool_schedule);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);
        return new ScheduleRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final TimeTableDbHelper dbhelper = new TimeTableDbHelper(context,null);

        viewHolder.id = listSchedule.get(position).getSubject_Id();
        viewHolder.tvName.setText(listSchedule.get(position).getSubject_Name());
        viewHolder.tvTeacher.setText(listSchedule.get(position).getSubject_Teacher());
        viewHolder.tvRoom.setText(listSchedule.get(position).getSubject_Room());
        viewHolder.tvTime.setText(listSchedule.get(position).getSubject_Time());

        final TextView tvtool = viewHolder.tool_schedule;
        viewHolder.tool_schedule.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, tvtool);
                popup.inflate(R.menu.menu_schedule);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit_schedule:
                                Intent intent1 = new Intent (context, Edit_Schedule.class);
                                intent1.putExtra("schedule_id",viewHolder.id);
                                context.startActivity(intent1);
                                //Toast.makeText(context,"edit" +viewHolder.teacher.getName()  ,Toast.LENGTH_SHORT).show();

                                return true;

                            case R.id.add_homework_schedule:
                                Intent intent2 = new Intent (context, Edit_Schedule.class);
                                intent2.putExtra("schedule_name",viewHolder.tvName.toString());
                                context.startActivity(intent2);
                                //Toast.makeText(context,"delete",Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.set_reminder_schedule:
                                Intent intent3 = new Intent (context, Edit_Schedule.class);
                                intent3.putExtra("schedule_name",viewHolder.tvName.toString());
                                context.startActivity(intent3);
                                //Toast.makeText(context,"delete",Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.delete_schedule:
                                dbhelper.deleteSchedule(viewHolder.id);
                                deleteItem(viewHolder.getAdapterPosition());
                                Toast.makeText(context,"delete",Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listSchedule != null ? listSchedule.size() : 0;
    }

    public ScheduleRecyclerViewAdapter(ArrayList<Schedule_Model> listSchedule) {
        this.listSchedule = listSchedule;

    }

    public void deleteItem(int position){
        listSchedule.remove(position);
        notifyDataSetChanged();
    }


}
