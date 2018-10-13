package com.joyBox.shefaa.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.entities.MedicinePotionEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Adhamkh on 2018-08-21.
 */

public class MedicineAndPotionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.idText)
    public TextView idText;

    @BindView(R.id.toggleBtn)
    public ToggleButton toggleBtn;

    public MedicineAndPotionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(MedicinePotionEntity entity) {
        idText.setText(entity.getId());
    }


}
