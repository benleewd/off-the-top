package com.github.hbtruong2017.oft;

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

import com.github.hbtruong2017.oft.api.model.Appointment;
import com.github.hbtruong2017.oft.api.model.Barber;
import com.github.hbtruong2017.oft.ui.appointment.ServiceActivity;

import java.util.Arrays;
import java.util.List;

public class HistoryMainAdapter extends RecyclerView.Adapter<HistoryMainAdapter.ViewHolder> {
    private Context mContext;
    private List<Appointment> appointmentList;

    public HistoryMainAdapter(Context mContext, List<Appointment> appointmentList) {
        this.mContext = mContext;
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item_history,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText("Date: " + appointmentList.get(position).getDate() + " - " + appointmentList.get(position).getTime());
        holder.location.setText("Location: " + appointmentList.get(position).getAddress());
        holder.barber.setText("Serviced by " + appointmentList.get(position).getBarberName());
        holder.service.setText(appointmentList.get(position).getServiceName());
    }

    @Override
    public int getItemCount() {
        return this.appointmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView location;
        TextView barber;
        TextView service;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            location = (TextView) itemView.findViewById(R.id.location);
            barber = (TextView) itemView.findViewById(R.id.barber);
            service = (TextView) itemView.findViewById(R.id.service);
        }
    }

}
