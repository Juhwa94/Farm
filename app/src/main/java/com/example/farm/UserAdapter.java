package com.example.farm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.RecycleViewHolder> {

    String[] main_name;
    String[] main_date;
    RecycleViewHolder recycleviewholder;

    //생성자
    public UserAdapter(String[] name, String[] date){
        this.main_name = name;
        this.main_date = date;
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder {

        public TextView name, date;


        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.plantName);
            this.date = itemView.findViewById(R.id.plantDate);
        }

    }


    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item,parent,false);
        recycleviewholder = new RecycleViewHolder(holderView);

        return recycleviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        recycleviewholder.name.setText(this.main_name[position]);
        recycleviewholder.date.setText(this.main_date[position]);

    }

    @Override
    public int getItemCount() {
        return main_date.length;
    }



}

