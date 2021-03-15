package com.h.mortaja.productdb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    ArrayList<User>arrayList =new ArrayList<User>();
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
holder.tvname.setText(arrayList.get(position).getName());
holder.tvphone.setText(arrayList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
TextView tvname,tvphone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.textView);
            tvphone=itemView.findViewById(R.id.textView2);
        }
    }
}


