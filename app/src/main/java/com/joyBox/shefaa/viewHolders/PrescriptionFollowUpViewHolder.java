package com.joyBox.shefaa.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.entities.PrescriptionFollowUp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrescriptionFollowUpViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.idText)
    public TextView idText;

    @BindView(R.id.medicineName)
    public TextView medicineName;

    @BindView(R.id.toggleBtn)
    public ToggleButton toggleBtn;

    public PrescriptionFollowUpViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(PrescriptionFollowUp followUp) {
        idText.setText(followUp.getId());
        medicineName.setText(followUp.getIndicator_name());
    }

}

