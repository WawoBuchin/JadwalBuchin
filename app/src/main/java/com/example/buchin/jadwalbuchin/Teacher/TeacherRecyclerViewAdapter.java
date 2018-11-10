package com.example.buchin.jadwalbuchin.Teacher;

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

public class TeacherRecyclerViewAdapter extends RecyclerView.Adapter<TeacherRecyclerViewAdapter.ViewHolder>  {

    private ArrayList<TeacherModel> listTeacher;
    private OnItemClickListener listener;

    Context context;

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView tvNama,tvPost,tvemail,tvhp,tvoffice,tvofficehour,tool;
        String id;
        TeacherModel teacher;
        //pengisian variabel
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = "";

            teacher = new TeacherModel();
            tvNama = itemView.findViewById(R.id.tvnama);
            tvPost = itemView.findViewById(R.id.tvpost);
            tvemail = itemView.findViewById(R.id.tvemail);
            tvhp = itemView.findViewById(R.id.tvhp);
            tvoffice = itemView.findViewById(R.id.tvoffice);
            tvofficehour = itemView.findViewById(R.id.tvofficehour);
            tool = itemView.findViewById(R.id.tool);
        }
    }

    public TeacherRecyclerViewAdapter(ArrayList<TeacherModel> listTeacher) {
        this.listTeacher = listTeacher;
    }

    public void deleteItem(int position){
        listTeacher.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    //tempat item
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher, parent, false);
        return new ViewHolder(v);
    }

    @Override
    //set data ke tampilan
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        final TimeTableDbHelper dbhelper = new TimeTableDbHelper(context,null);

        viewHolder.tvNama.setText(listTeacher.get(position).getName());
        viewHolder.tvPost.setText(listTeacher.get(position).getPost());
        viewHolder.tvemail.setText(listTeacher.get(position).getEmail());
        viewHolder.tvhp.setText(listTeacher.get(position).getPhone());
        viewHolder.tvoffice.setText(listTeacher.get(position).getOffice());
        viewHolder.tvofficehour.setText(listTeacher.get(position).getOfficeHours());
        viewHolder.id = listTeacher.get(position).getID_Teacher();

        final TextView tvtool = viewHolder.tool;
        viewHolder.tool.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, tvtool);
                popup.inflate(R.menu.menu_teacher);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                Intent intent = new Intent (context, EditTeacher.class);
                                intent.putExtra("id",viewHolder.id);
                                context.startActivity(intent);
                                //dbhelper.updateTeacher(viewHolder.teacher);
                                //Toast.makeText(context,"edit" +viewHolder.teacher.getName()  ,Toast.LENGTH_SHORT).show();

                                return true;
                            case R.id.delete:
                                dbhelper.deleteTeacher(viewHolder.id);
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
        return listTeacher != null ? listTeacher.size() : 0;
    }


    public void setOnItemClickLister(OnItemClickListener listener){
        this.listener = listener;
    }


}
