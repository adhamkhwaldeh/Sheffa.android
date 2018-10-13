package com.joyBox.shefaa.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.App;
import com.joyBox.shefaa.entities.AppointmentEntity;
import com.joyBox.shefaa.enums.AppointmentPlace;
import com.joyBox.shefaa.enums.AppointmentStatus;
import com.joyBox.shefaa.helpers.IntentHelper;
import com.joyBox.shefaa.viewHolders.AppointmentPatientViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AppointmentPatientRecyclerViewAdapter extends RecyclerView.Adapter<AppointmentPatientViewHolder> {

    private Context context;
    private List<AppointmentEntity> appointmentEntityList;

    private List<AppointmentEntity> SearchList;

    public AppointmentPatientRecyclerViewAdapter(Context context, List<AppointmentEntity> appointmentEntityList) {
        this.context = context;
        this.appointmentEntityList = appointmentEntityList;
    }

    @NonNull
    @Override
    public AppointmentPatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.appointment_patient_item, parent, false);
        return new AppointmentPatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentPatientViewHolder holder, int position) {
        final AppointmentEntity poJo = appointmentEntityList.get(position);
        holder.bindPojo(poJo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.Companion.startAppointmentDetailsActivity(context, poJo);
            }
        });

    }

    @Override
    public int getItemCount() {
        return appointmentEntityList != null ? appointmentEntityList.size() : 0;
    }

    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults oReturn = new FilterResults();
                final List<AppointmentEntity> results = new ArrayList<>();
                if (SearchList == null) {
                    SearchList = appointmentEntityList;
                }
                if (charSequence != null) {
//                    String isUrgent = charSequence.toString().split("=>")[0];
//                    String isApproved = charSequence.toString().split("=>")[1];
                    String isHome = charSequence.toString().split("=>")[0];
                    String dt = charSequence.toString().split("=>")[1];
                    if (SearchList != null && SearchList.size() > 0) {
                        for (final AppointmentEntity doctor : SearchList) {
//                            if (doctor.getUrgent_appointment().toLowerCase().toLowerCase().
//                                    replace("no", AppointmentStatus.Urgent.getStatus())
//                                    .replace("yes", AppointmentStatus.NotUrgent.getStatus())
//                                    .contains(isUrgent)) {
//                                String ddt = doctor.getAppointment_Date();
//                                if (doctor.getApproved().contains(isApproved) ||isUrgent.equals(AppointmentStatus.Urgent.getStatus()))
//                                    if (dt.contains(ddt) || ddt.contains(dt))
//                                        results.add(doctor);
//                            }

                            String place = doctor.getHomeAppointment().toLowerCase().
                                    replace("no", AppointmentPlace.Clinic.getPlace()).
                                    replace("yes", AppointmentPlace.Home.getPlace());

                            if (place.equalsIgnoreCase(isHome)) {
                                String ddt = doctor.getAppointment_Date();
//                                if (doctor.getApproved().contains(isApproved) ||isUrgent.equals(AppointmentStatus.Urgent.getStatus()))
                                if (dt.contains(ddt) || ddt.contains(dt))
                                    results.add(doctor);
                            }
                            Log.v("", "");
                        }
                    }
                    //&& doctor.getAppointment_Date().contains(dt)
                    oReturn.values = results;
                    oReturn.count = results.size();
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                try {
                    appointmentEntityList = (ArrayList<AppointmentEntity>) filterResults.values;
                    notifyDataSetChanged();
                } catch (Exception ex) {
                }
            }

        };
    }

}
