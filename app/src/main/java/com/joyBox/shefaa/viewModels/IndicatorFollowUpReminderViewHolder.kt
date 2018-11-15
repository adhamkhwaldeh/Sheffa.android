package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Prescription
import com.joyBox.shefaa.entities.PrescriptionFollowUp
import com.joyBox.shefaa.networking.NetworkingHelper

class IndicatorFollowUpReminderViewHolder constructor(val view: View,
                                                      private val prescriptionFollowUp: PrescriptionFollowUp,
                                                      val prescription: Prescription)
    : RecyclerView.ViewHolder(view) {


    @BindView(R.id.medicineNameEditText)
    lateinit var medicineNameEditText: EditText

    @BindView(R.id.activeIngredientNameEditText)
    lateinit var activeIngredientNameEditText: EditText

    @BindView(R.id.alternativeMedicineEditText)
    lateinit var alternativeMedicineEditText: EditText

    @BindView(R.id.potionEditText)
    lateinit var potionEditText: EditText

    @BindView(R.id.howManyTimesEditText)
    lateinit var howManyTimesEditText: EditText

    @BindView(R.id.perEditText)
    lateinit var perEditText: EditText

    @BindView(R.id.durationEditText)
    lateinit var durationEditText: EditText

    var context: Context

    init {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun isValid(): Boolean {
        return true
    }

    fun getUrl(): String {
//        val client: Client = UserRepository(context).getClient()!!
        return NetworkingHelper.ReminderUrl + "?indi_tid=" + prescriptionFollowUp.indicator_tid +
                "&indi_name=" + prescriptionFollowUp.indicator_name + "&" +
                "pres_id=" + prescription.nid //+ "&first_time=2018-07-25 09:15:00&uid=" + client.user.uid
    }


    fun bind() {
        medicineNameEditText.setText(prescriptionFollowUp.indicator_name)
//            activeIngredientNameEditText.setText(prescriptionFollowUp!!.active_ingredient_name_String)
//            alternativeMedicineEditText.setText(prescriptionFollowUp!!.alternative_medicine_string)
//            potionEditText.setText(prescriptionFollowUp!!.potion)
        howManyTimesEditText.setText(prescriptionFollowUp.how_many_times)
        perEditText.setText(prescriptionFollowUp.per)
        durationEditText.setText(prescriptionFollowUp.for_how_long)

    }


}