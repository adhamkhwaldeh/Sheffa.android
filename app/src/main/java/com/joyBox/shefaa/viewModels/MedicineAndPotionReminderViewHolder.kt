package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.MedicinePotionEntity
import com.joyBox.shefaa.entities.Prescription
import com.joyBox.shefaa.networking.NetworkingHelper


class MedicineAndPotionReminderViewHolder constructor(val view: View,
                                                      private val medicinePotionEntity: MedicinePotionEntity,
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
        this.context = view.context
    }


    fun isValid(): Boolean {
        return true
    }

    fun getUrl(): String {
        return NetworkingHelper.ReminderUrl + "?med_id=" + medicinePotionEntity.medicine_id +
                "&pres_id=" + prescription.nid //+ "&first_time=2018-07-25 09:15:00&uid="+user.user.uid
    }

    fun bind() {
        medicineNameEditText.setText(medicinePotionEntity.medicine_name)
        activeIngredientNameEditText.setText(medicinePotionEntity.active_ingredient_name_String)
        alternativeMedicineEditText.setText(medicinePotionEntity.alternative_medicine_string)
        potionEditText.setText(medicinePotionEntity.potion)
        howManyTimesEditText.setText(medicinePotionEntity.how_many_times)
        perEditText.setText(medicinePotionEntity.per)
        durationEditText.setText(medicinePotionEntity.for_how_long)
    }

}