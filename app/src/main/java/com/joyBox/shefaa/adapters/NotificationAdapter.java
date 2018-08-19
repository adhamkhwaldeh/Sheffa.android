package com.joyBox.shefaa.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.entities.NotificationEntity;
import com.joyBox.shefaa.viewHolders.NotificationViewHolder;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Adhamkh on 2018-08-19.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder> {
    Context context;
    List<NotificationEntity> notificationEntities;

    public NotificationAdapter(Context context, List<NotificationEntity> notificationEntities) {
        this.context = context;
        this.notificationEntities = notificationEntities;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_item, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationEntity pojo = notificationEntities.get(position);
        holder.bind(pojo);
    }

    @Override
    public int getItemCount() {
        return notificationEntities != null ? notificationEntities.size() : 0;
    }
}
