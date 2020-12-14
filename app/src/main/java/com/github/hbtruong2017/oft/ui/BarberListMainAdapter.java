package com.github.hbtruong2017.oft.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hbtruong2017.oft.R;
import com.github.hbtruong2017.oft.api.model.Appointment;
import com.github.hbtruong2017.oft.api.model.Barber;
import com.github.hbtruong2017.oft.ui.appointment.ServiceActivity;

import java.util.Arrays;
import java.util.List;

public class BarberListMainAdapter extends RecyclerView.Adapter<BarberListMainAdapter.ViewHolder> {
    private Context mContext;
    private List<Barber> barberList;

    public BarberListMainAdapter(Context mContext, List<Barber> barberList) {
        this.mContext = mContext;
        this.barberList = barberList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.barberName.setText(barberList.get(position).getName());
        holder.barberDescription.setText(barberList.get(position).getDescription());
        holder.barberImage.setImageResource(barberList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return this.barberList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView barberName;
        TextView barberDescription;
        ImageView barberImage;
        Appointment appointment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            barberName = (TextView) itemView.findViewById(R.id.barberName);
            barberDescription = (TextView) itemView.findViewById(R.id.barberDescription);
            barberImage = (ImageView) itemView.findViewById(R.id.barberImage);
        }
    }

}
