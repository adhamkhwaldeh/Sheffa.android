package com.joyBox.shefaa.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.entities.NotificationEntity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Adhamkh on 2018-08-19.
 */

public class NotificationViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.messageText)
    TextView messageText;

    @BindView(R.id.timeStampText)
    TextView timeStampText;

    public NotificationViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(NotificationEntity entity) {
        messageText.setText(Html.fromHtml(entity.getMessages()));
        timeStampText.setText(entity.getTimestamp());
    }
}
