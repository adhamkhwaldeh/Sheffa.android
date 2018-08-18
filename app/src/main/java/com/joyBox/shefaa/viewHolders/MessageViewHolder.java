package com.joyBox.shefaa.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.JoyBox.Shefaa.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.subject)
    public TextView subject;

    @BindView(R.id.body)
    public TextView body;

    @BindView(R.id.messagedate)
    public TextView messagedate;

    @BindView(R.id.alarm)
    public ImageView alarm;

    public MessageViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

}
