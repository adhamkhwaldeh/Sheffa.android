package com.joyBox.shefaa.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.entities.Client;
import com.joyBox.shefaa.entities.MessageEntity;
import com.joyBox.shefaa.helpers.IntentHelper;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.repositories.UserRepository;
import com.joyBox.shefaa.viewHolders.MessageViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Adhamkh on 2017-08-10.
 */
public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<MessageViewHolder> {
    public Context context;
    public List<MessageEntity> messageList;

    public MessageRecyclerViewAdapter(Context context, List<MessageEntity> messageList) {
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

                Client client = new UserRepository(context).getClient();
                new setMessageReaded().execute(holder.getAdapterPosition(), NetworkingHelper.MakeReadedURL +
                        "?message_id=" + message.getMid() +
                        "&sess_id=" + client.getSessid() + "&rec=" + client.getUser().getUid() +
                        "&sess_name=" + client.getSessionName() + "&token=" + client.getToken());
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageList == null ? 0 : messageList.size();
    }


    public class setMessageReaded extends AsyncTask<Object, Void, String> {
        int position = 0;

        @Override
        protected String doInBackground(Object... params) {
            position = (int) params[0];
            String url = (String) params[1];
            return GeneralConnections.getJson(url, NetworkingHelper.RequestTimeout);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                    Toast.makeText(context, R.string.ErrorConnection, Toast.LENGTH_LONG).show();
                } else {
                    if (s.contains("0")) {
                        messageList.get(position).set_new("0");
                        notifyItemChanged(position);
                    }
                }
            } catch (Exception ex) {
            }
        }
    }


}
