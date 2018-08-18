package com.joyBox.shefaa.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.entities.MessageEntity;
import com.joyBox.shefaa.helpers.IntentHelper;
import com.joyBox.shefaa.viewHolders.MessageViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Adhamkh on 2017-08-10.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    public Context context;
    public List<MessageEntity> messageList;

    public MessageAdapter(Context context, List<MessageEntity> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @NotNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MessageViewHolder(inflater.inflate(R.layout.message_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder holder, final int position) {
        final MessageEntity message = messageList.get(position);
        holder.subject.setText(message.getSubject());
        holder.body.setText(message.getBody());
        holder.messagedate.setText(message.getDate());
        if (message.is_new().equals("0"))
            holder.alarm.setVisibility(View.GONE);
        else
            holder.alarm.setVisibility(View.VISIBLE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.Companion.startMessageDetailsActivity(context, message);
            }
        });

        holder.alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new setMessageReaded().execute(holder.getAdapterPosition(), Utl.MakeReadedURL + "?message_id=" + message.getMid() +
//                        "&sess_id=" + Utl.client.getSessid() + "&rec=" + Utl.client.getUser().getUid() +
//                        "&sess_name=" + Utl.client.getSessionName() + "&token=" + Utl.client.getToken());
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageList == null ? 0 : messageList.size();
    }


//    public class setMessageReaded extends AsyncTask<Object, Void, String> {
//        int position = 0;
//
//        @Override
//        protected String doInBackground(Object... params) {
//            position = (int) params[0];
//            String url = (String) params[1];
//            return JsonParser.getJson(url, Utl.RequestTimeout);
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            if (s.equals("Error Connection")) {
//                Toast.makeText(context, R.string.ErrorConnection, Toast.LENGTH_LONG).show();
//            } else {
//                if (s.contains("0")) {
//                    messageList.get(position).setIs_new("0");
//                    notifyItemChanged(position);
//                }
//            }
//        }
//    }


}
