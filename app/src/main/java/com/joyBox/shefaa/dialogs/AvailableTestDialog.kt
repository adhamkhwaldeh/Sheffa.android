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
import com.joyBox.shefaa.adapters.LabTestRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerAvailableTestComponent
import com.joyBox.shefaa.di.component.DaggerDiagnosiseComponent
import com.joyBox.shefaa.di.module.DiagnosisModule
import com.joyBox.shefaa.di.module.TestResultsModule
import com.joyBox.shefaa.di.ui.DiagnosiseContract
import com.joyBox.shefaa.di.ui.DiagnosisePresenter
import com.joyBox.shefaa.di.ui.TestResultsPresenter
import com.joyBox.shefaa.di.ui.TestsResultsContract
import com.joyBox.shefaa.entities.DiagnosiseAutoComplete
import com.joyBox.shefaa.entities.LabTest
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject


class AvailableTestDialog : DialogFragment(), TestsResultsContract.View {

    companion object {
        const val AvailableTestDialog_Tag = "AvailableTestDialog_Tag"
        fun newInstance(): AvailableTestDialog {
            val availableTestDialog = AvailableTestDialog()

            return availableTestDialog
        }
    }

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView


    @Inject
    lateinit var presenter: TestResultsPresenter

    fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(GridDividerDecoration(activity))
    }

    fun initDI() {
        val component = DaggerAvailableTestComponent.builder()
                .testResultsModule(TestResultsModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadAvailableTest(NetworkingHelper.LabTestsUrl)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.available_test_dialog_layout, container, false)
        ButterKnife.bind(this, mView)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadAvailableTest(NetworkingHelper.LabTestsUrl)
            }

            override fun onRequestPermission() {

            }
        })

        RxBus.listen(MessageEvent::class.java).subscribe {

            when (it.action) {
                EventActions.Available_Test_Tag -> {
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

    override fun onAvailableTestsLoadedSuccessfully(labTests: MutableList<LabTest>) {
        recyclerView.adapter = LabTestRecyclerViewAdapter(context!!, labTests)
    }

//    override fun onDiagnosiseAutoCompleteSuccessfully(diagnosiseAutoCompleteList: MutableList<DiagnosiseAutoComplete>) {
//        recyclerView.adapter = DiagonsisRecyclerViewAdapter(context!!, diagnosiseAutoCompleteList)
//    }
    /*Diagnosis presenter ended*/
}