package com.example.computerknowledge;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class course_AD extends RecyclerView.Adapter<course_AD.course_view>{

List<course_list> list;

    public course_AD(List<course_list> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public course_view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.course_model,parent,false);

        return new course_view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull course_view holder, int position) {

        String name=list.get(position).getName();
        String dis=list.get(position).getDis();
        Uri pic=list.get(position).getPic();
        String key=list.get(position).getKey();



        Picasso.get().load(pic).into(holder.picimag);
        holder.name_course.setText(name);
        holder.dis_course.setText(dis);
        holder.model_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),Start_AC.class);
                intent.putExtra("name",name+"");
                intent.putExtra("dis",dis+"");
                intent.putExtra("pic",pic+"");
                intent.putExtra("key",key);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class course_view extends RecyclerView.ViewHolder
    {

        TextView name_course,dis_course;
        ImageView picimag;
        LinearLayout model_lay;
        public course_view(@NonNull View itemView) {
            super(itemView);

            picimag= itemView.findViewById(R.id.image_course);
            name_course=itemView.findViewById(R.id.name_course);
            dis_course= itemView.findViewById(R.id.dis_course);
            model_lay=itemView.findViewById(R.id.model_lay);
        }
    }
}
