package com.joyBox.shefaa.dialogs;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.JoyBox.Shefaa.R;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.repositories.UserRepository;

public class RatingDialog extends DialogFragment {


    public String TargetId = "-1";
    public RatingBar ratingBar;
    public ProgressDialog progressDialog;

    public static String RatingDialog_Target_Id = "RatingDialog_Target_Id";

    public static String RatingDialog_Tag = "RatingDialog_Tag";

    public static RatingDialog newInstance(String TargetId) {
        RatingDialog f = new RatingDialog(); //new RatingDialog(new ContextThemeWrapper(context, R.style.DialogSlideAnim));
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString(RatingDialog_Target_Id, TargetId);
        f.setArguments(args);
        return f;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.rating_layout, container);
        Button btnOk = (Button) mView.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(DoneAction);

        Button btncancel = (Button) mView.findViewById(R.id.btncancel);
        btncancel.setOnClickListener(DoneAction);
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppTheme_DialogSlideAnim);
        TargetId = getArguments().getString(RatingDialog_Target_Id);
        ratingBar = (RatingBar) view.findViewById(R.id.stars);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        // stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(getResources().getColor(R.color.scratch_end_gradient), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.golden_stars), PorterDuff.Mode.SRC_ATOP);

    }

    View.OnClickListener DoneAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.btnOk:
                    String userId = new UserRepository(getActivity()).getClient().getUser().getUid();
                    Float aFloat = ratingBar.getRating() * 20.00f;
                    new AddRating().execute(NetworkingHelper.RatingURL + "?target=" + TargetId + "&uid=" + userId
                            + "&rate=" + aFloat.toString());
                    break;
                case R.id.btncancel:
                    RatingDialog.this.dismiss();
                    break;
            }
        }
    };

    public class AddRating extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return GeneralConnections.getJson(params[0], NetworkingHelper.RequestTimeout);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(RatingDialog.this.getContext());
            progressDialog.setMessage(getResources().getString(R.string.pleasewait));
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s.equals("Error Connection")) {
                Toast.makeText(getContext(), getResources().getString(R.string.noconnection), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), getResources().getString(R.string.Thankyouforrating), Toast.LENGTH_LONG).show();
                RatingDialog.this.dismiss();
            }

        }
    }

}