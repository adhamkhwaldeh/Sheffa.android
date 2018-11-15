package com.joyBox.shefaa.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.DoctorSpecializationRecyclerViewAdapter
import com.joyBox.shefaa.entities.SpecialistAutoComplete
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.views.GridDividerDecoration

class DoctorSpecializationDialog : DialogFragment() {

    lateinit var doctorSpecializationList: MutableList<String>
    lateinit var specializationList: MutableList<SpecialistAutoComplete>

    companion object {

        const val DoctorSpecializationDialog_Tag = "DoctorSpecializationDialog_Tag"

        fun newInstance(doctorSpecializationList: MutableList<String>, specializationList: MutableList<SpecialistAutoComplete>)
                : DoctorSpecializationDialog {
            val doctorSpecializationDialog = DoctorSpecializationDialog()
            doctorSpecializationDialog.doctorSpecializationList = doctorSpecializationList
            doctorSpecializationDialog.specializationList = specializationList
            return doctorSpecializationDialog
        }
    }

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    fun initRecyclerView() {
        recyclerView.addItemDecoration(GridDividerDecoration(context))
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = DoctorSpecializationRecyclerViewAdapter(context!!, specializationList = specializationList)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.doctor_specialization_dialog_layout, container)
        ButterKnife.bind(this, mView)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

    }

    @OnClick(R.id.submitBtn)
    fun onSubmitButtonClick(view: View) {
        val list = (recyclerView.adapter as DoctorSpecializationRecyclerViewAdapter).getDoctorSpecializationList()
        RxBus.publish(MessageEvent(EventActions.DoctorSpecializationDialog_Tag, list))
        dismiss()
    }

}