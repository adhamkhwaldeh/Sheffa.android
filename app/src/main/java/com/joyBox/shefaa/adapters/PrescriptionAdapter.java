package com.joyBox.shefaa.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.entities.Prescription;
import com.joyBox.shefaa.helpers.IntentHelper;
import com.joyBox.shefaa.viewHolders.PrescriptionViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Adhamkh on 2017-07-30.
 */

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionViewHolder> {

    public Context context;
    public List<Prescription> prescriptionList;

    public PrescriptionAdapter(Context context, List<Prescription> prescriptionList) {
        this.context = context;
        this.prescriptionList = prescriptionList;
    }

    @NotNull
    @Override
    public PrescriptionViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new PrescriptionViewHolder(inflater.inflate(R.layout.prescription_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PrescriptionViewHolder holder, int position) {
        final Prescription prescription = prescriptionList.get(position);

        holder.PrescriptionName.setText(prescription.getTitle());
        holder.PrescriptionDate.setText(prescription.getPrec_date());
        holder.PrescriptionDoctor.setText(prescription.getAuthor_Name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.Companion.startPrescriptionDetailsActivity(context,prescription);
            }
        });

    }

    @Override
    public int getItemCount() {
        return prescriptionList == null ? 0 : prescriptionList.size();
    }

}
