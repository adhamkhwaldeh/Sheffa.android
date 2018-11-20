package com.joyBox.shefaa.fragments.doctorPatientsFragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.DoctorTestsResultRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorPatientsTestResultComponent
import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.ui.DoctorContract
import com.joyBox.shefaa.di.ui.DoctorPresenter
import com.joyBox.shefaa.entities.TestResultEntity
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class DoctorPatientsTestResultFragment : BaseDoctorPatientsFragment(), DoctorContract.View {
    companion object {
        fun getNewInstance(): BaseDoctorPatientsFragment {
            val f = DoctorPatientsTestResultFragment()
            f.titleRes = R.string.MyPatientsTestResult
            return f
        }
    }

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @Inject
    lateinit var presenter: DoctorPresenter

    fun initRecyclerView() {
        recyclerView.addItemDecoration(GridDividerDecoration(context))
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun initDI() {
        val component = DaggerDoctorPatientsTestResultComponent.builder()
                .doctorModule(DoctorModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        requestData()
    }

    fun requestData() {
        val userId = UserRepository(context!!).getClient()!!.user.uid!!
        presenter.loadDoctorTestResult(userId)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.doctor_patients_test_results_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initDI()
        requestData()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                requestData()
            }

            override fun onRequestPermission() {

            }
        })

    }


    /*Doctor presenter started*/
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

    override fun onTestsResultsLoadedSuccessfully(testResultEntityList: MutableList<TestResultEntity>) {
        recyclerView.adapter = DoctorTestsResultRecyclerViewAdapter(context!!, testResultEntityList)
    }
    /*Doctor presenter ended*/

}