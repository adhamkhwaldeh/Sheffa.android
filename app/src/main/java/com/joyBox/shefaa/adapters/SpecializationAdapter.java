package com.joyBox.shefaa.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.JoyBox.Shefaa.R;

import java.util.List;

/**
 * Created by Adhamkh on 2017-07-27.
 */

public class SpecializationAdapter extends RecyclerView.Adapter<SpecializationAdapter.ViewHolder> {

    public Context context;
    public List<String> Specializationlist;

    public SpecializationAdapter(Context context, List<String> Specializationlist) {
        this.context = context;
        this.Specializationlist = Specializationlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.specialization_recycler_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String Specializationname = Specializationlist.get(position);
        holder.Specializationname.setText(Specializationname);
    }

    @Override
    public int getItemCount() {
        return Specializationlist == null ? 0 : Specializationlist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Specializationname;

        public ViewHolder(View view) {
            super(view);
            Specializationname = (TextView) view.findViewById(R.id.Specializationname);
        }

    }

}
