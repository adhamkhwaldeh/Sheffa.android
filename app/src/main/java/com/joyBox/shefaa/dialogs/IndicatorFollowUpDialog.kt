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
import com.joyBox.shefaa.di.component.DaggerIndicatorFollowUpDialogComponent
import com.joyBox.shefaa.di.module.ReminderModule
import com.joyBox.shefaa.di.ui.ReminderContract
import com.joyBox.shefaa.di.ui.ReminderPresenter
import com.joyBox.shefaa.entities.Prescription
import com.joyBox.shefaa.entities.PrescriptionFollowUp
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.enums.ReminderType
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewModels.IndicatorFollowUpReminderViewHolder
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class IndicatorFollowUpDialog : DialogFragment(), ReminderContract.View {

    companion object {
        const val FollowUpDialog_Tag = "FollowUpDialog_Tag"


        fun newInstance(prescriptionFollowUp: PrescriptionFollowUp, prescription: Prescription): IndicatorFollowUpDialog {
            val f = IndicatorFollowUpDialog()
            f.isCancelable = true
            f.prescriptionFollowUp = prescriptionFollowUp
            f.prescription = prescription
            f.reminderType = ReminderType.MEDICINE_AND_POTION
            // Supply num input as an argument.
            val args = Bundle()
            f.arguments = args
            return f
        }
    }

    lateinit var prescriptionFollowUp: PrescriptionFollowUp

    lateinit var prescription: Prescription

    lateinit var reminderType: ReminderType


    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @Inject
    lateinit var presenter: ReminderPresenter

    lateinit var indicatorFollowUpReminderViewHolder: IndicatorFollowUpReminderViewHolder

    private fun initDI() {
        val component = DaggerIndicatorFollowUpDialogComponent.builder()
                .reminderModule(ReminderModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.follow_up_dialog_layout, container)
        ButterKnife.bind(this, mView)
        indicatorFollowUpReminderViewHolder = IndicatorFollowUpReminderViewHolder(mView, prescriptionFollowUp, prescription)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDI()
        indicatorFollowUpReminderViewHolder.bind()
    }

    @OnClick(R.id.remindBtn)
    fun onReminderBtnClick(view: View) {
        IntentHelper.startAddReminderActivity(activity!!, indicatorFollowUpReminderViewHolder.getUrl())
//        if (indicatorFollowUpReminderViewHolder.isValid())
//            presenter.remind(indicatorFollowUpReminderViewHolder.getUrl())
    }


    /*Follow up presenter started*/

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
//        IntentHelper.startMedicineAndPotionReminderActivity(context!!, reminderType = reminderType)
        Log.v("", "")
    }

    /*Follow up presenter ended*/


}