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
import com.joyBox.shefaa.adapters.CityRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerCityComponent
import com.joyBox.shefaa.di.module.CityModule
import com.joyBox.shefaa.di.ui.CityContract
import com.joyBox.shefaa.di.ui.CityPresenter
import com.joyBox.shefaa.entities.CityEntity
import com.joyBox.shefaa.enums.CityEnum
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject


class CitiesDialog : DialogFragment(), CityContract.View {

    companion object {
        const val CitiesDialog_Tag = "CitiesDialog_Tag"

        fun newInstance(cityEnum: CityEnum): CitiesDialog {
            val citiesDialog = CitiesDialog()
            citiesDialog.cityEnum = cityEnum
            return citiesDialog
        }

    }

    lateinit var cityEnum: CityEnum


    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var presenter: CityPresenter

    fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(GridDividerDecoration(context))
    }

    fun initDI() {
        val component = DaggerCityComponent.builder()
                .cityModule(CityModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadCityList(cityEnum)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.cities_dialog_layout, container, false)
        ButterKnife.bind(this, mView)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initDI()
        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadCityList(cityEnum)
            }

            override fun onRequestPermission() {

            }
        })

        RxBus.listen(MessageEvent::class.java).subscribe {

            when (it.action) {
                EventActions.CityPharmacy_Tag, EventActions.CityDoctor_Tag, EventActions.CityLab_Tag -> {
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

    /*City presenter started*/

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

    override fun onCityListLoaded(cityList: MutableList<CityEntity>) {
        recyclerView.adapter = CityRecyclerViewAdapter(context!!, cityEnum, cityList)
    }
    /*City presenter ended*/

}