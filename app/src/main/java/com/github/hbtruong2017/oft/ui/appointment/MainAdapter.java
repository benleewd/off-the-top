package com.github.hbtruong2017.oft.ui.appointment;

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

import java.util.Arrays;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private Context mContext;
    private List<Barber> barberList;

    public MainAdapter(Context mContext, List<Barber> barberList) {
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //get old intent

                    Intent oldIntent = ((Activity) mContext).getIntent();

                    appointment = (Appointment) oldIntent.getSerializableExtra("appointment");
                    appointment.setBarberName(barberName.getText().toString());
                    appointment.setBarberImage(barberImage.getId());

                    Intent intent = new Intent(v.getContext(), ServiceActivity.class);
                    intent.putExtra("appointment", appointment);

                    Log.i("Intent", "data: " +  intentToString(intent));

                    v.getContext().startActivity(intent);
                }
            });
        }
    }


    // TO PRINT

    public static String intentToString(Intent intent) {
        if (intent == null) {
            return null;
        }

        return intent.toString() + " " + bundleToString(intent.getExtras());
    }

    public static String bundleToString(Bundle bundle) {
        StringBuilder out = new StringBuilder("Bundle[");

        if (bundle == null) {
            out.append("null");
        } else {
            boolean first = true;
            for (String key : bundle.keySet()) {
                if (!first) {
                    out.append(", ");
                }

                out.append(key).append('=');

                Object value = bundle.get(key);

                if (value instanceof int[]) {
                    out.append(Arrays.toString((int[]) value));
                } else if (value instanceof byte[]) {
                    out.append(Arrays.toString((byte[]) value));
                } else if (value instanceof boolean[]) {
                    out.append(Arrays.toString((boolean[]) value));
                } else if (value instanceof short[]) {
                    out.append(Arrays.toString((short[]) value));
                } else if (value instanceof long[]) {
                    out.append(Arrays.toString((long[]) value));
                } else if (value instanceof float[]) {
                    out.append(Arrays.toString((float[]) value));
                } else if (value instanceof double[]) {
                    out.append(Arrays.toString((double[]) value));
                } else if (value instanceof String[]) {
                    out.append(Arrays.toString((String[]) value));
                } else if (value instanceof CharSequence[]) {
                    out.append(Arrays.toString((CharSequence[]) value));
                } else if (value instanceof Parcelable[]) {
                    out.append(Arrays.toString((Parcelable[]) value));
                } else if (value instanceof Bundle) {
                    out.append(bundleToString((Bundle) value));
                } else {
                    out.append(value);
                }

                first = false;
            }
        }

        out.append("]");
        return out.toString();
    }
}
