package com.joyBox.shefaa.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.di.component.DaggerMedicineAndPotionComponent
import com.joyBox.shefaa.di.component.DaggerMedicineAndPotionDialogComponent
import com.joyBox.shefaa.di.module.ReminderModule
import com.joyBox.shefaa.di.ui.ReminderContract
import com.joyBox.shefaa.di.ui.ReminderPresenter
import com.joyBox.shefaa.entities.MedicinePotionEntity
import com.joyBox.shefaa.entities.Prescription
import com.joyBox.shefaa.entities.PrescriptionFollowUp
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.enums.ReminderType
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.localJobs.MainJobManager
import com.joyBox.shefaa.viewModels.MedicineAndPotionReminderViewHolder
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject


class MedicineAndPotionDialog : DialogFragment(), ReminderContract.View {

    companion object {
        const val MedicineAndPotionDialog_Tag = "MedicineAndPotionDialog_Tag"

        fun newInstance(medicinePotionEntity: MedicinePotionEntity, prescription: Prescription): MedicineAndPotionDialog {
            val f = MedicineAndPotionDialog()
            f.isCancelable = true
            f.medicinePotionEntity = medicinePotionEntity
            f.prescription = prescription
            f.reminderType = ReminderType.MEDICINE_AND_POTION
            val args = Bundle()
            f.arguments = args
            return f
        }

    }

    lateinit var medicinePotionEntity: MedicinePotionEntity

    lateinit var prescription: Prescription

    lateinit var reminderType: ReminderType

    lateinit var medicineAndPotionReminderViewHolder: MedicineAndPotionReminderViewHolder

    @Inject
    lateinit var presenter: ReminderPresenter

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    private fun initDI() {
        val component = DaggerMedicineAndPotionDialogComponent.builder()
                .reminderModule(ReminderModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
//        presenter.loadAvailableTime(doctorId = doctor.doctor_id, date = dateText.text.toString())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView: View = inflater.inflate(R.layout.medicine_and_potion_dialog_layout, container)
        ButterKnife.bind(this, mView)
        medicineAndPotionReminderViewHolder = MedicineAndPotionReminderViewHolder(mView, medicinePotionEntity, prescription)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDI()
        medicineAndPotionReminderViewHolder.bind()
    }

    @OnClick(R.id.remindBtn)
    fun onReminderBtnClick(view: View) {
        IntentHelper.startAddReminderActivity(activity!!, medicineAndPotionReminderViewHolder.getUrl())
//        if (medicineAndPotionReminderViewHolder.isValid()) {
//            presenter.remind(medicineAndPotionReminderViewHolder.getUrl())
//        }
    }


    /*Reminder presenter started*/

    override fun showProgress(show: Boolean) {
        if (show) {
            stateLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showEmptyView(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Nodatalayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Noconnectionlayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun onRemindSuccessfully(stringList: MutableList<String>) {
        for (s in stringList) {
            try {
                val time = s.toLong() - System.currentTimeMillis()
                MainJobManager.addJob2Scheduler(time)
            } catch (ex: Exception) {
            }
        }
        dismiss()
        Log.v("", "")
//        presenter.remind()
//        IntentHelper.startMedicineAndPotionReminderActivity(context!!, reminderType = reminderType)
    }
    /*Reminder presenter ended*/
}