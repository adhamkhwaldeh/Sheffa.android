package com.joyBox.shefaa.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.MedicinePotionEntity
import com.joyBox.shefaa.entities.PrescriptionFollowUp
import com.joyBox.shefaa.enums.ReminderType
import com.joyBox.shefaa.helpers.IntentHelper


class MedicineAndPotionDialog : DialogFragment() {

    lateinit var medicinePotionEntity: MedicinePotionEntity


    lateinit var reminderType: ReminderType

    companion object {
        const val MedicineAndPotionDialog_Tag = "MedicineAndPotionDialog_Tag"

        fun newInstance(medicinePotionEntity: MedicinePotionEntity): MedicineAndPotionDialog {
            val f = MedicineAndPotionDialog()
            f.isCancelable = true
            f.medicinePotionEntity = medicinePotionEntity
            f.reminderType = ReminderType.MEDICINE_AND_POTION
            // Supply num input as an argument.
            val args = Bundle()
            f.arguments = args
            return f
        }


    }


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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.medicine_and_potion_dialog_layout, container)
        ButterKnife.bind(this, mView)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind()

    }

    @OnClick(R.id.remindBtn)
    fun onReminderBtnClick(view: View) {
        IntentHelper.startMedicineAndPotionReminderActivity(context!!, reminderType = reminderType)
    }

    fun bind() {
        if (medicinePotionEntity != null) {
            medicineNameEditText.setText(medicinePotionEntity!!.medicine_name)
            activeIngredientNameEditText.setText(medicinePotionEntity!!.active_ingredient_name_String)
            alternativeMedicineEditText.setText(medicinePotionEntity!!.alternative_medicine_string)
            potionEditText.setText(medicinePotionEntity!!.potion)
            howManyTimesEditText.setText(medicinePotionEntity!!.how_many_times)
            perEditText.setText(medicinePotionEntity!!.per)
            durationEditText.setText(medicinePotionEntity!!.for_how_long)
        }
    }

}