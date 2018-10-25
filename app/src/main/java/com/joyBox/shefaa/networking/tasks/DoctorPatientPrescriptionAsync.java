package com.joyBox.shefaa.networking.tasks;

import android.os.AsyncTask;

import com.joyBox.shefaa.entities.DoctorPatientPrescription;
import com.joyBox.shefaa.networking.JsonParser;
import com.joyBox.shefaa.networking.NetworkingHelper;
import com.joyBox.shefaa.networking.connections.GeneralConnections;
import com.joyBox.shefaa.networking.listeners.OnDoctorPatientPrescriptionListener;

import java.util.List;

/**
 * Created by Adhamkh on 2018-10-20.
 */

public class DoctorPatientPrescriptionAsync extends AsyncTask<Void, Void, String> {

    private String doctorName;
    private String patientId;

    private OnDoctorPatientPrescriptionListener onDoctorPatientPrescriptionListener;

    public DoctorPatientPrescriptionAsync(String doctorName, String patientId,
                                          OnDoctorPatientPrescriptionListener onDoctorPatientPrescriptionListener) {
        this.doctorName = doctorName;
        this.patientId = patientId;
        this.onDoctorPatientPrescriptionListener = onDoctorPatientPrescriptionListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onDoctorPatientPrescriptionListener.onDoctorPatientPrescriptionLoading();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return GeneralConnections.getJson(NetworkingHelper.Doctor_Patient_Prescription_Url
                + "?patient_id=" + patientId + "&doctor_name=" + doctorName, NetworkingHelper.RequestTimeout);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            if (s.equalsIgnoreCase(NetworkingHelper.ErrorConnectionResponse)) {
                onDoctorPatientPrescriptionListener.onDoctorPatientPrescriptionInternetConnection();
            } else {
                List<DoctorPatientPrescription> doctorPatientPrescriptionList = JsonParser.getDoctorPatientPrescription(s);
                if (doctorPatientPrescriptionList.size() > 0) {
                    onDoctorPatientPrescriptionListener.onDoctorPatientPrescriptionSuccessFully(doctorPatientPrescriptionList);
                } else {
                    onDoctorPatientPrescriptionListener.onDoctorPatientPrescriptionNoData();
                }
            }
            return;
        } catch (Exception ex) {

        }
        onDoctorPatientPrescriptionListener.onDoctorPatientPrescriptionInternetConnection();
    }
}
