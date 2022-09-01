package com.example.loginwithdb;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myviewholder> {
    ArrayList<model> dataholder;
    private selectListner listner;


    public myAdapter(ArrayList<model> dataholder,selectListner listner) {
        this.dataholder = dataholder;
        this.listner= listner;
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

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              listner.onItemClicked(dataholder.get(position));
            }
        });
        holder.acpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onAcptBtnClickList(dataholder.get(position));
                holder.acpt.setEnabled(false);
                holder.dlt.setEnabled(true);
            }
        });
        holder.dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onDltBtnClickList(dataholder.get(position));
                holder.dlt.setEnabled(false);
                holder.acpt.setEnabled(true);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView dname,dmobno,demail;
        ImageView dimg;
        Button acpt,dlt;
        CardView cardView;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            dname = itemView.findViewById(R.id.displayname);
            dmobno = itemView.findViewById(R.id.displaymobno);
            demail = itemView.findViewById(R.id.displayemail);
            dimg = itemView.findViewById(R.id.dimg);
            cardView = itemView.findViewById(R.id.main_container);
            acpt = itemView.findViewById(R.id.btnacpt);
            dlt = itemView.findViewById(R.id.btndlt);
        }
    }
}
