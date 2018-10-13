package com.joyBox.shefaa.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.JoyBox.Shefaa.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrescriptionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.PrescriptionName)
    public TextView PrescriptionName;

    @BindView(R.id.PrescriptionDoctor)
    public TextView PrescriptionDoctor;

    @BindView(R.id.PrescriptionDate)
    public TextView PrescriptionDate;

    public PrescriptionViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

}