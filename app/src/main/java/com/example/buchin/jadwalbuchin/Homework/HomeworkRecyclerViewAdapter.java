package com.example.buchin.jadwalbuchin.Homework;

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

import java.text.BreakIterator;
import java.util.ArrayList;

public class HomeworkRecyclerViewAdapter extends RecyclerView.Adapter<HomeworkRecyclerViewAdapter.ViewHolder> {
    private ArrayList<HomeWorkModel> listHomework;
    private OnItemClickListener listener;

    //untuk popupmenu
    Context context;


    public HomeworkRecyclerViewAdapter(ArrayList<HomeWorkModel> listHomework) {
        this.listHomework = listHomework;
    }

    public void deleteItem(int position) {
        listHomework.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeworkRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_homework, viewGroup, false);
        return new HomeworkRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        final TimeTableDbHelper dbhelper = new TimeTableDbHelper(context, null);

        viewHolder.txtTitle.setText(listHomework.get(position).getTittle());
        viewHolder.txtType.setText(listHomework.get(position).getType());
        viewHolder.txtDesc.setText(listHomework.get(position).getDescription());
        viewHolder.txtDate.setText(listHomework.get(position).getDate());
        viewHolder.id = listHomework.get(position).getID_HomeWork();
        viewHolder.status = listHomework.get(position).getStatus();
        //Toast.makeText(context,"ID " + listHomework.get(position).getID_HomeWork() + "-Title " + listHomework.get(position).getTittle() + "-Desc " + listHomework.get(position).getDescription() + "-date " + listHomework.get(position).getDate() + "-status " + listHomework.get(position).getStatus(),Toast.LENGTH_SHORT).show();




        final TextView tvtool = viewHolder.tool;
        viewHolder.tool.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, tvtool);
                popup.inflate(R.menu.menu_homework);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(context,"edit" +viewHolder.id  ,Toast.LENGTH_SHORT).show();
                        switch (item.getItemId()) {
                            case R.id.edit:
                                Intent intent = new Intent (context, EditHomework.class);

                                intent.putExtra("idh",viewHolder.id);
                                Toast.makeText(context,"edit" +viewHolder.id  ,Toast.LENGTH_SHORT).show();
                                context.startActivity(intent);
                                //dbhelper.updateTeacher(viewHolder.teacher);
                                //                                //Toast.makeText(context,"edit" +viewHolder.teacher.getName()  ,Toast.LENGTH_SHORT).show();

                                return true;
                            case R.id.mark:

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
        return listHomework != null ? listHomework.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtType, txtDesc, txtDate, tool;
        String id,status;
        HomeWorkModel homework;

        //pengisian variabel
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = "";

            homework = new HomeWorkModel();

            txtDate = itemView.findViewById(R.id.tvdate);
            txtTitle = itemView.findViewById(R.id.tvtitle);
            txtType = itemView.findViewById(R.id.tvtype);
            txtDesc = itemView.findViewById(R.id.tvdesc);
            tool = itemView.findViewById(R.id.tool);

        }

    }
    public void setOnItemClickLister(OnItemClickListener listener){
        this.listener = listener;
    }
}
