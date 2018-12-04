package com.joyBox.shefaa.viewHolders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.entities.AppointmentEntity;
import com.joyBox.shefaa.enums.AppointmentStatus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentPatientViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.title)
    public TextView title;

    @BindView(R.id.datetext)
    public TextView datetext;

    @BindView(R.id.patient)
    public TextView patient;

    @BindView(R.id.time)
    public TextView time;

    @BindView((R.id.cardView))
    public CardView cardView;

    @BindView(R.id.isUrgent)
    public View isUrgentView;

    public Context context;

    public AppointmentPatientViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        context = view.getContext();
    }

    public void bindPoJo(AppointmentEntity appointment) {
        title.setText(appointment.getTitle());
        datetext.setText(appointment.getAppointment_Date());
        patient.setText(appointment.getPatient_Name());
        time.setText(appointment.getAppointment_start_time());
        String isUrgent = appointment.getUrgent_appointment();
        if (isUrgent.equalsIgnoreCase(AppointmentStatus.Urgent.getStatus()) ||
                isUrgent.equalsIgnoreCase(AppointmentStatus.UrgentYes.getStatus())) {
//            cardView.setBackgroundColor(context.getResources().getColor(R.color.redcolor));
            isUrgentView.setVisibility(View.VISIBLE);
        }
    }

}
