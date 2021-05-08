package com.akshatvg.assessment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<String> list = new ArrayList<>();
    Context context;
    public RecyclerAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.data.setText(list.get(position));
        if(position%2!=0){
            holder.background.setBackgroundColor(ContextCompat.getColor(context, R.color.darkblue));
            holder.data.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View background;
        TextView data;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.data);
            background=itemView.findViewById(R.id.background);
        }
    }
}
