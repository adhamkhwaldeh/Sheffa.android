package com.joyBox.shefaa.activities.doctor

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnTouch
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.di.component.DaggerDoctorAddPrescriptionComponent
import com.joyBox.shefaa.di.module.PrescriptionModule
import com.joyBox.shefaa.di.ui.PrescriptionContract
import com.joyBox.shefaa.di.ui.PrescriptionPresenter
import com.joyBox.shefaa.dialogs.AvailableTestDialog
import com.joyBox.shefaa.dialogs.DiagnosiseDialog
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.entities.Prescription
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewModels.AddPrescriptionViewHolder
import javax.inject.Inject

class DoctorAddPrescriptionActivity : BaseActivity(), PrescriptionContract.View {

    companion object {
        const val DoctorAddPrescriptionActivity_Tag = "DoctorAddPrescriptionActivity_Tag"
    }

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var presenter: PrescriptionPresenter

    lateinit var doctorAppointment: DoctorAppointment

    lateinit var addPrescriptionViewHolder: AddPrescriptionViewHolder

    fun initToolBar() {
        toolbar.setTitle(R.string.AddPrescription)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initDI() {
        val component = DaggerDoctorAddPrescriptionComponent.builder()
                .prescriptionModule(PrescriptionModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_add_prescription_layout)
        ButterKnife.bind(this)
        addPrescriptionViewHolder = AddPrescriptionViewHolder(findViewById(android.R.id.content))

        val jSon = intent.getStringExtra(DoctorAddPrescriptionActivity_Tag)
        doctorAppointment = Gson().fromJson(jSon, DoctorAppointment::class.java)
        addPrescriptionViewHolder.bindDoctorAppointment(doctorAppointment)

        initToolBar()
        initDI()

        RxBus.listen(MessageEvent::class.java).subscribe {
            when(it.action){
                EventActions.MedicineAutoCompleteActivity_Tag->{

                }
            }
        }

    }

    @OnTouch(R.id.diagnosisName)
    fun onDiagnosisNameTouch(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action != KeyEvent.ACTION_DOWN)
            return false

        val dialog = DiagnosiseDialog.newInstance()
        dialog.show(supportFragmentManager, DiagnosiseDialog.DiagnosiseDialog_Tag)
        return false
    }

    @OnTouch(R.id.requiredTestEditText)
    fun onRequiredTestEditTextTouch(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action != KeyEvent.ACTION_DOWN)
            return false

        val dialog = AvailableTestDialog.newInstance()
        dialog.show(supportFragmentManager, AvailableTestDialog.AvailableTestDialog_Tag)
        return false
    }

    @OnTouch(R.id.medicineName)
    fun onMedicineNameTouch(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action != KeyEvent.ACTION_DOWN)
            return false
        IntentHelper.startMedicineAutoCompleteActivity(this)
        return false
    }

    @OnTouch(R.id.activeMaterialName)
    fun onActiveMaterialNameTouch(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action != KeyEvent.ACTION_DOWN)
            return false
        IntentHelper.startActiveMaterialAutoCompleteActivity(this)
        return false
    }

    @OnTouch(R.id.alterMedicineName)
    fun onAlterMedicineNameTouch(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action != KeyEvent.ACTION_DOWN)
            return false
        IntentHelper.startMedicineAutoCompleteActivity(this)
        return false
    }


    /*Prescription presenter started*/
    override fun showProgress(show: Boolean) {

    }

    override fun showEmptyView(visible: Boolean) {
        super.showEmptyView(visible)
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        super.showLoadErrorMessage(visible)
    }

    override fun onPrescriptionAddedSuccessfully() {
        super.onPrescriptionAddedSuccessfully()
    }

    override fun onAddPrescriptionFailed() {
        super.onAddPrescriptionFailed()
    }
    /*Prescription presenter ended*/

}