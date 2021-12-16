package com.rupyz.myapplication11;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {
    private ArrayList<PersonDetails> courseModalArrayList;
    private Context context;
    DBHandler dbHandler;

    public CourseRVAdapter(ArrayList<PersonDetails> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
        dbHandler = new DBHandler(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonDetails modal = courseModalArrayList.get(position);
        holder.userName.setText(modal.getName());
        holder.userEmail.setText(modal.getEmail());
        holder.userPhone.setText(modal.getPhone());
        holder.userDob.setText(modal.getDob());
        holder.userGender.setText(modal.getGender());

// below line is to add on click listener for our recycler view item.
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                i.putExtra("name", modal.getName());
                i.putExtra("email", modal.getEmail());
                i.putExtra("phone", modal.getPhone());
                i.putExtra("dob", modal.getDob());
                i.putExtra("gender",modal.getGender());
                context.startActivity(i);
                ((Activity)context).finish();
            }
        });*/

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(context, holder.imgEdit);
                popup.getMenuInflater().inflate(R.menu.main_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        //Toast.makeText(context," : " + item.getTitle(), Toast.LENGTH_SHORT).show();

                        if (item.getItemId() == R.id.update_btn) {
                            Intent i = new Intent(context, MainActivity.class);
                            i.putExtra("name", modal.getName());
                            i.putExtra("email", modal.getEmail());
                            i.putExtra("phone", modal.getPhone());
                            i.putExtra("dob", modal.getDob());
                            i.putExtra("gender",modal.getGender());
                            context.startActivity(i);
                            ((Activity)context).finish();
                        } else if (item.getItemId() == R.id.delete_btn) {
                           /* Toast.makeText(context, "Call clicked", Toast.LENGTH_SHORT).show();*/
                            dbHandler.deleteCourse(modal.getName());

                            notifyItemRemoved(position);
                        }

                        return true;
                    }
                });
                popup.show();//showing popup menu


            }


        });
    }

    @Override
    public int getItemCount() {
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userName, userEmail, userPhone, userDob, userGender;
        private ImageView imgEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.edt_name);
            userEmail = itemView.findViewById(R.id.edt_email);
            userPhone = itemView.findViewById(R.id.edt_phone);
            userDob = itemView.findViewById(R.id.edt_dob);
            userGender = itemView.findViewById(R.id.edt_gender);
            imgEdit = itemView.findViewById(R.id.img_edit);
        }
    }

}