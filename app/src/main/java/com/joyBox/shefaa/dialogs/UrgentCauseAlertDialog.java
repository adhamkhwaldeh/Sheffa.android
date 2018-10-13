package com.joyBox.shefaa.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.listeners.OnUrgentCauseListener;

/**
 * Created by Adhamkh on 2017-09-23.
 */

public class UrgentCauseAlertDialog extends DialogFragment {

    public static UrgentCauseAlertDialog newInstance() {
        UrgentCauseAlertDialog f = new UrgentCauseAlertDialog();
        return f;
    }

    public Button btnOk;
    public OnUrgentCauseListener onUrgentCauseListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.urgent_cause_alert_dialog_layout, container);
        btnOk = (Button) mView.findViewById(R.id.btnOk);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onUrgentCauseListener != null)
                    onUrgentCauseListener.onUrgentCauseSelected("");
                UrgentCauseAlertDialog.this.dismiss();
            }
        });
    }

    public void setOnUrgentCauseListener(OnUrgentCauseListener onUrgentCauseListener) {
        this.onUrgentCauseListener = onUrgentCauseListener;
    }

}
