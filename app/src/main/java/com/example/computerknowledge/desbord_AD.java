package com.example.computerknowledge;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class desbord_AD extends RecyclerView.Adapter<desbord_AD.desbordview> {

    List<desbord_list> list;
    public desbord_AD(List<desbord_list> list) {
        this.list = list;
    }




    @NonNull
    @Override
    public desbordview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dasbord_model,parent,false);
        return new desbordview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull desbordview holder, int position) {

        String pic=list.get(position).getPic();
        int count=list.get(position).getCout();
        String name=list.get(position).getName();
        String  gmail=list.get(position).getGmail();
        int level=list.get(position).getLevel();
//
//
//        var i=0
//        var tem:Int
//        while (i<ra.size-1)
//        {
//            var j:Int=0
//            while (j<ra.size-1)
//            {
//                if(ra[i]>=ra[j])
//                {
//                    tem=ra[i]
//                    ra[i]=ra[j]
//                    ra[j]=tem
//                }
//                j++
//            }
//
//            i++
//        }


        if(pic.equals("null"))
       {

           holder.imageView.setImageResource(R.drawable.userx);
       }
       else {
           Uri uri=Uri.parse(pic);
           Picasso.get().load(uri).into(holder.imageView);
       }
        holder.count.setText(count+"");
        holder.name.setText(name);
        holder.gmail.setText(gmail);
        holder.level.setText("Rank:"+level);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class desbordview extends RecyclerView.ViewHolder
    {
        TextView name,gmail,level,count;
        ImageView imageView;

        public desbordview(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.namedasbord);
            gmail=itemView.findViewById(R.id.gmail_dasbord);
            level=itemView.findViewById(R.id.level);
            count=itemView.findViewById(R.id.count_desbord);
            imageView=itemView.findViewById(R.id.image_desbord);
        }
    }
}
