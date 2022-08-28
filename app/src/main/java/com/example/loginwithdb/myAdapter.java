package com.example.loginwithdb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myviewholder> {
    ArrayList<model> dataholder;

    public myAdapter(ArrayList<model> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.dname.setText(dataholder.get(position).getName());
        holder.dmobno.setText(dataholder.get(position).getMobno());
        holder.demail.setText(dataholder.get(position).getEmail());
        holder.dimg.setImageBitmap(dataholder.get(position).getBitmap());
    }


    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView dname,dmobno,demail;
        ImageView dimg;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            dname = itemView.findViewById(R.id.displayname);
            dmobno = itemView.findViewById(R.id.displaymobno);
            demail = itemView.findViewById(R.id.displayemail);
            dimg = itemView.findViewById(R.id.dimg);
        }
    }
}
