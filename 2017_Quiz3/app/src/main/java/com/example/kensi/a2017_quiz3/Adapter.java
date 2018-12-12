package com.example.kensi.a2017_quiz3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater mInflater;
    Context context;

    public Adapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    //TODO 9.5 onBindViewHolder binds the data to each card according to its position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder charaViewHolder, int i) {
        // i is the position in the recyclerview
        charaViewHolder.textViewName.setText("aaa");
        charaViewHolder.textViewDescription.setText("bbbbb" + String.valueOf(i));
    }

    @Override
    public int getItemCount() {

        int numberOfRows = 15;
        return numberOfRows;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewDescription;

        public ViewHolder(View view) {
            super(view);
            textViewName = view.findViewById(R.id.cardViewTextName);
            textViewDescription = view.findViewById(R.id.cardViewTextDescription);
        }
    }
}
