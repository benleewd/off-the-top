package com.github.hbtruong2017.oft.ui.appointment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hbtruong2017.oft.R;
import com.github.hbtruong2017.oft.api.model.Appointment;
import com.github.hbtruong2017.oft.api.model.Service;

import java.util.List;

public class ServiceMainAdapter extends RecyclerView.Adapter<ServiceMainAdapter.ViewHolder> {
    private Context mContext;
    private List<Service> serviceList;

    public ServiceMainAdapter(Context mContext, List<Service> serviceList) {
        this.mContext = mContext;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item_service,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.serviceName.setText(serviceList.get(position).getName());
        holder.serviceDescription.setText(serviceList.get(position).getDescription());
        holder.serviceImage.setImageResource(serviceList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return this.serviceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName;
        TextView serviceDescription;
        ImageView serviceImage;
        Appointment appointment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = (TextView) itemView.findViewById(R.id.serviceName);
            serviceDescription = (TextView) itemView.findViewById(R.id.serviceDescription);
            serviceImage = (ImageView) itemView.findViewById(R.id.serviceImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //get old intent

                    Intent oldIntent = ((Activity) mContext).getIntent();

                    appointment = (Appointment) oldIntent.getSerializableExtra("appointment");
                    appointment.setServiceName(serviceName.getText().toString());
                    appointment.setServiceDescription(serviceDescription.getText().toString());

                    Intent intent = new Intent(v.getContext(), ConfirmationActivity.class);
                    intent.putExtra("appointment", appointment);

                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
