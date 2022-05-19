package com.example.ticketsystem;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterForSingleUser extends RecyclerView.Adapter<AdapterForSingleUser.viewholder> {

    ArrayList<String> v;

    public AdapterForSingleUser(ArrayList<String> v) {
        this.v = v;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.t.setText(v.get(position));

    }

    @Override
    public int getItemCount() {
        return v.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView t;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            t=itemView.findViewById(R.id.itemTextView);

            t.setGravity(Gravity.CENTER_HORIZONTAL);
            t.setGravity(Gravity.CENTER_VERTICAL);
            t.setAlpha(0.8F);
        }
    }
}
