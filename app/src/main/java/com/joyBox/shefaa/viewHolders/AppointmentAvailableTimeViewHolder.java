package com.joyBox.shefaa.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.JoyBox.Shefaa.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentAvailableTimeViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.availabletimetext)
    public TextView availabletimetext;

    @BindView(R.id.selecttimecheckbox)
    public CheckBox selecttimecheckbox;

    public AppointmentAvailableTimeViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

}