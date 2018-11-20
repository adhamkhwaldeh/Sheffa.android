package com.joyBox.shefaa.fragments.medicalTestFragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.PrescriptionAdapter
import com.joyBox.shefaa.di.component.DaggerPrescriptionListComponent
import com.joyBox.shefaa.di.module.PresciptionListModule
import com.joyBox.shefaa.di.ui.PrescriptionListContract
import com.joyBox.shefaa.di.ui.PrescriptionListPresenter
import com.joyBox.shefaa.entities.Prescription
import com.joyBox.shefaa.entities.User
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class PrescriptionsFragment : BaseMedicalTestFragment(), PrescriptionListContract.View {

    companion object {
        fun getNewInstance(user: User): PrescriptionsFragment {
            val f = PrescriptionsFragment()
            f.titleRes = R.string.Prescriptions
            f.user = user
            return f
        }
    }

    @Inject
    lateinit var presenter: PrescriptionListPresenter

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    private fun initDI() {
        val component = DaggerPrescriptionListComponent.builder()
                .presciptionListModule(PresciptionListModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadPrescriptions(generateRequestUrl())
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(GridDividerDecoration(context))
    }

    private fun generateRequestUrl(): String {
        val client = UserRepository(context!!).getClient()!!
        return NetworkingHelper.PrescriptionUrl + "?patient_id=" + /*client.*/user.uid +
                "&sess_name=" + client.sessionName + "&sess_id=" + client.sessid + "&token=" + client.token
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.prescription_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initDI()


        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadPrescriptions(generateRequestUrl())
            }

            override fun onRequestPermission() {

            }
        })
    }

    /*Presenter started*/
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

    override fun onPrescriptionsLoadedSuccessfully(prescriptionList: List<Prescription>) {
        recyclerView.adapter = PrescriptionAdapter(context, prescriptionList)
        Log.v("", "")
    }

    /*Presenter ended*/

}