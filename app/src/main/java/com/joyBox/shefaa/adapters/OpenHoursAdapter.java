package com.joyBox.shefaa.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.entities.Clinic_open_hours;
import com.joyBox.shefaa.helpers.ClinicHourHelper;

import java.util.List;

/**
 * Created by Adhamkh on 2017-07-27.
 */

public class OpenHoursAdapter extends RecyclerView.Adapter<OpenHoursAdapter.ViewHolder> {

    public Context context;
    public List<Clinic_open_hours> Clinic_open_hourslist;

    public OpenHoursAdapter(Context context, List<Clinic_open_hours> Clinic_open_hourslist) {
        this.context = context;
        this.Clinic_open_hourslist = Clinic_open_hourslist;
    }

    @Override
    public OpenHoursAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new OpenHoursAdapter.ViewHolder(inflater.inflate(R.layout.clinic_open_hours_item, parent, false));
    }

    @Override
    public void onBindViewHolder(OpenHoursAdapter.ViewHolder holder, int position) {
        Clinic_open_hours clinicOpenHours = Clinic_open_hourslist.get(position);
        holder.day.setText(ClinicHourHelper.getDay(clinicOpenHours.day));
        holder.starthours.setText(ClinicHourHelper.getTime(clinicOpenHours.starthours));
        holder.endhours.setText(ClinicHourHelper.getTime(clinicOpenHours.endhours));
    }

    @Override
    public int getItemCount() {
        return Clinic_open_hourslist == null ? 0 : Clinic_open_hourslist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView day;
        public TextView starthours;
        public TextView endhours;

        public ViewHolder(View view) {
            super(view);
            day = (TextView) view.findViewById(R.id.day);
            starthours = (TextView) view.findViewById(R.id.starthours);
            endhours = (TextView) view.findViewById(R.id.endhours);
        }

    }

}
