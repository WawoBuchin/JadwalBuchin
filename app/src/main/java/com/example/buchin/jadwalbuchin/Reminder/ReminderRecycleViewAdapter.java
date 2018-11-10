package com.example.buchin.jadwalbuchin.Reminder;

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

import com.example.buchin.jadwalbuchin.OnItemClickListener;
import com.example.buchin.jadwalbuchin.R;
import com.example.buchin.jadwalbuchin.TimeTableDbHelper;

import java.util.ArrayList;

public class ReminderRecycleViewAdapter extends RecyclerView.Adapter<ReminderRecycleViewAdapter.ViewHolder>  {

    private ArrayList<ReminderModel> listReminder;
    private OnItemClickListener listener;

    //untuk popupmenu
    Context context;



    public ReminderRecycleViewAdapter(ArrayList<ReminderModel> listReminder) {
        this.listReminder = listReminder;
    }

    public void deleteItem(int position){
        listReminder.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    //tempat item
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reminder, parent, false);
        return new ViewHolder(v);
    }

    @Override
    //set data ke tampilan
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        final TimeTableDbHelper dbhelper = new TimeTableDbHelper(context,null);

        viewHolder.tvtitle.setText(listReminder.get(position).getTittle());
        viewHolder.tvdesc.setText(listReminder.get(position).getDescription());
        viewHolder.tvdate.setText(listReminder.get(position).getDate());
        viewHolder.tvtime.setText(listReminder.get(position).getTime());
        viewHolder.id = listReminder.get(position).getID_Reminder();

        final TextView tool = viewHolder.tool;
        viewHolder.tool.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, tool);
                popup.inflate(R.menu.menu_teacher);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                Intent intent = new Intent (context, EditReminder.class);
                                intent.putExtra("id",viewHolder.id);
                                context.startActivity(intent);
                                //dbhelper.updateTeacher(viewHolder.teacher);
                                //Toast.makeText(context,"edit" +viewHolder.teacher.getName()  ,Toast.LENGTH_SHORT).show();

                                return true;
                            case R.id.delete:
                                dbhelper.deleteReminder(viewHolder.id);
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
        return listReminder != null ? listReminder.size() : 0;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView tvtitle,tvdesc,tvdate,tvtime, tool;
        String id,title,desc,date,time;
        ReminderModel reminder;
        //pengisian variabel
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = "";

            reminder = new ReminderModel();
            tvtitle = itemView.findViewById(R.id.tvtitle);
            tvdesc = itemView.findViewById(R.id.tvdesc);
            tvdate = itemView.findViewById(R.id.tvdate);
            tvtime = itemView.findViewById(R.id.tvtime);
            tool = itemView.findViewById(R.id.tool);
        }
    }
    public void setOnItemClickLister(OnItemClickListener listener){
        this.listener = listener;
    }


}