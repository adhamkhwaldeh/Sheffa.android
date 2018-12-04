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
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.DiagonsisRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerDiagnosiseComponent
import com.joyBox.shefaa.di.module.DiagnosisModule
import com.joyBox.shefaa.di.ui.DiagnosiseContract
import com.joyBox.shefaa.di.ui.DiagnosisePresenter
import com.joyBox.shefaa.entities.DiagnosiseAutoComplete
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class DiagnosiseDialog : DialogFragment(), DiagnosiseContract.View {

    companion object {
        const val DiagnosiseDialog_Tag = "DiagnosiseDialog"
        fun newInstance(): DiagnosiseDialog {
            val diagnosiseDialog = DiagnosiseDialog()

            return diagnosiseDialog
        }
    }

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView


    @Inject
    lateinit var presenter: DiagnosisePresenter

    fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(GridDividerDecoration(activity))
    }

    fun initDI() {
        val component = DaggerDiagnosiseComponent.builder()
                .diagnosisModule(DiagnosisModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadDiagnosiseAutoComplete()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.diagnosis_dialog_layout, container, false)
        ButterKnife.bind(this, mView)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadDiagnosiseAutoComplete()
            }

            override fun onRequestPermission() {

            }
        })

        RxBus.listen(MessageEvent::class.java).subscribe {

            when (it.action) {
                EventActions.Diagnosis_Tag -> {
                    try {
                        dismiss()
                    } catch (ex: Exception) {
                    }
                }
            }

        }
    }

    override fun onStart() {
        super.onStart()
//        val width = resources.getDimensionPixelSize(R.dimen.dialogHeight)
//        val height = resources.getDimensionPixelSize(R.dimen.dialogHeight)
        val width = dialog.window.windowManager.defaultDisplay.width
        val height = dialog.window.windowManager.defaultDisplay.height
        dialog.window!!.setLayout(3 * width / 4, 2 * height / 3)

    }

    /*Diagnosis presenter started*/
    override fun showProgress(show: Boolean) {
    }

    override fun showDiagnosiseAutoCompleteProgress(show: Boolean) {
        if (show) {
            stateLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showDiagnosiseAutoCompleteEmptyView(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Nodatalayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showDiagnosiseAutoCompleteLoadErrorMessage(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Noconnectionlayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun onDiagnosiseAutoCompleteSuccessfully(diagnosiseAutoCompleteList: MutableList<DiagnosiseAutoComplete>) {
        recyclerView.adapter = DiagonsisRecyclerViewAdapter(context!!, diagnosiseAutoCompleteList)
    }
    /*Diagnosis presenter ended*/
}