package com.joyBox.shefaa.activities.doctor

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.di.component.DaggerDoctorAppointmentDetailsComponent
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.ui.AppointmentContract
import com.joyBox.shefaa.di.ui.AppointmentPresenter
import com.joyBox.shefaa.dialogs.ProgressDialog
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.enums.AppointmentFlagName
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.viewHolders.DoctorAppointmentDetailsViewHolder
import javax.inject.Inject


class DoctorAppointmentDetailsActivity : BaseActivity(), AppointmentContract.View {

    companion object {
        const val DoctorAppointmentDetailsActivity_Tag = "DoctorAppointmentDetailsActivity_Tag"
    }

    @Inject
    lateinit var presenter: AppointmentPresenter

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    fun initToolBar() {
        toolbar.setTitle(R.string.AppointmentDetail)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initDI() {
        val component = DaggerDoctorAppointmentDetailsComponent.builder()
                .appointmentModule(AppointmentModule(this))
                .build()
        component.inject(this)

        presenter.attachView(this)
        presenter.subscribe()
    }

    var progressDialog: ProgressDialog = ProgressDialog.newInstance()

    lateinit var doctorAppointment: DoctorAppointment
    lateinit var doctorAppointmentDetailsViewHolder: DoctorAppointmentDetailsViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_appointment_details_layout)
        ButterKnife.bind(this)
        initToolBar()
        initDI()
        val jSon = intent.getStringExtra(DoctorAppointmentDetailsActivity_Tag)
        doctorAppointment = Gson().fromJson(jSon, DoctorAppointment::class.java)
        doctorAppointmentDetailsViewHolder = DoctorAppointmentDetailsViewHolder(findViewById(android.R.id.content))
        doctorAppointmentDetailsViewHolder.bind(doctorAppointment)

    }

    @OnClick(R.id.startAppointmentBtn)
    fun onStartAppointmentButtonClick(view: View) {
        val userId = UserRepository(this).getClient()!!.user.uid
        presenter.switchAppointmentState(NetworkingHelper.Doctor_Appointment_Url, AppointmentFlagName.START,
                doctorAppointmentDetailsViewHolder.appointmentId.text.toString(),
                userId /*doctorAppointmentDetailsViewHolder.patientId.text.toString()*/, "flag")
    }

    @OnClick(R.id.endAppointmentBtn)
    fun onEndAppointmentButtonClick(view: View) {
        val userId = UserRepository(this).getClient()!!.user.uid
        presenter.switchAppointmentState(NetworkingHelper.Doctor_Appointment_Url, AppointmentFlagName.END,
                doctorAppointmentDetailsViewHolder.appointmentId.text.toString(),
                userId /*doctorAppointmentDetailsViewHolder.patientId.text.toString()*/, "flag")
    }

    @OnClick(R.id.acceptAppointmentBtn)
    fun onAcceptAppointmentButtonClick(view: View) {
        val userId = UserRepository(this).getClient()!!.user.uid
        presenter.switchAppointmentState(NetworkingHelper.Doctor_Appointment_Url, AppointmentFlagName.Accept,
                doctorAppointmentDetailsViewHolder.appointmentId.text.toString(),
                userId /*doctorAppointmentDetailsViewHolder.patientId.text.toString()*/, "flag")
    }

    @OnClick(R.id.refuseAppointmentBtn)
    fun onRefuseAppointmentButtonClick(view: View) {
        val userId = UserRepository(this).getClient()!!.user.uid
        presenter.switchAppointmentState(NetworkingHelper.Doctor_Appointment_Url, AppointmentFlagName.Refuse,
                doctorAppointmentDetailsViewHolder.appointmentId.text.toString(),
                userId /*doctorAppointmentDetailsViewHolder.patientId.text.toString()*/, "flag")
    }

    @OnClick(R.id.deleteAppointmentBtn)
    fun onDeleteAppointmentButtonClick(view: View) {
        presenter.deleteAppointment(appointmentId = doctorAppointment.nid)
    }

    @OnClick(R.id.addPrescriptionBtn)
    fun onAddPrescriptionButtonClick(view: View) {
        IntentHelper.startDoctorAddPrescriptionActivity(this@DoctorAppointmentDetailsActivity, doctorAppointment)
    }

    @OnClick(R.id.addInvoiceBtn)
    fun onAddInvoiceButtonClick(view: View) {
        IntentHelper.startDoctorAppointmentInvoiceActivity(this@DoctorAppointmentDetailsActivity, doctorAppointment)
    }

    override fun onAppointmentSwitchedSuccessfully() {
        Toast.makeText(this@DoctorAppointmentDetailsActivity, R.string.AppointmentStateSwitchedSuccessfully, Toast.LENGTH_LONG).show()
    }

    override fun onAppointmentSwitchedFailed() {
        Toast.makeText(this@DoctorAppointmentDetailsActivity, R.string.UnexpectedError, Toast.LENGTH_LONG).show()
    }

    override fun onAppointmentDeletedSuccessfully() {
        Toast.makeText(this@DoctorAppointmentDetailsActivity, R.string.AppointmentDeletedSuccessfully, Toast.LENGTH_LONG).show()
    }

    override fun onAppointmentDeletedFailed() {
        Toast.makeText(this@DoctorAppointmentDetailsActivity, R.string.UnexpectedError, Toast.LENGTH_LONG).show()
    }

    /*Appointment presenter started*/
    override fun showProgress(show: Boolean) {
        if (show)
            progressDialog.show(supportFragmentManager, ProgressDialog.ProgressDialog_Tag)
        else
            progressDialog.dismiss()
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        super.showLoadErrorMessage(visible)
        Toast.makeText(baseContext, resources.getString(R.string.ErrorConnection), Toast.LENGTH_LONG).show();
    }
    /*Appointment presenter ended*/

}