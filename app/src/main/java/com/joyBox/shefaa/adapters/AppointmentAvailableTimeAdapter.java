package com.joyBox.shefaa.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.viewHolders.AppointmentAvailableTimeViewHolder;

import java.util.List;


public class AppointmentAvailableTimeAdapter extends RecyclerView.Adapter<AppointmentAvailableTimeViewHolder> {

    public Context context;
    public List<String> availabletimelist;
    public CheckBox selectedcheckbox;
    public String selectedtime = "";


    public AppointmentAvailableTimeAdapter(Context context, List<String> availabletimelist) {
        this.context = context;
        this.availabletimelist = availabletimelist;
        selectedtime = "";
    }

    @NonNull
    @Override
    public AppointmentAvailableTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new AppointmentAvailableTimeViewHolder(inflater.inflate(R.layout.appointment_available_time_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final @NonNull AppointmentAvailableTimeViewHolder holder, int position) {
        final String tm = availabletimelist.get(position);
        holder.availabletimetext.setText(tm);

        if (tm.contains("No")) {
            holder.selecttimecheckbox.setVisibility(View.GONE);
        }
        holder.selecttimecheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (selectedcheckbox != null)
                        selectedcheckbox.setChecked(false);
                    holder.selecttimecheckbox.setChecked(true);
                    selectedcheckbox = holder.selecttimecheckbox;
                    selectedtime = tm;
                } else {
                    selectedtime = "";
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return availabletimelist == null ? 0 : availabletimelist.size();
    }


}
