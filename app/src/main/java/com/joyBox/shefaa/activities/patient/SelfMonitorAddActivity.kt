package com.joyBox.shefaa.activities.patient

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import butterknife.*
import butterknife.Optional
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.MeasurementTypeSpinnerAdapter
import com.joyBox.shefaa.di.component.DaggerSelfMonitorAddComponent
import com.joyBox.shefaa.di.module.MeasurementTypeModule
import com.joyBox.shefaa.di.module.SelfMonitorModule
import com.joyBox.shefaa.di.ui.MeasurementTypeContract
import com.joyBox.shefaa.di.ui.MeasurementTypePresenter
import com.joyBox.shefaa.di.ui.SelfMonitorContact
import com.joyBox.shefaa.di.ui.SelfMonitorPresenter
import com.joyBox.shefaa.entities.MeasurementType
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.helpers.mainHelper
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.viewModels.SelfMonitorAddViewHolder
import com.joyBox.shefaa.views.Stateslayoutview
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SelfMonitorAddActivity : BaseActivity(), SelfMonitorContact.View, MeasurementTypeContract.View {


    @Inject
    lateinit var presenter: SelfMonitorPresenter

    @Inject
    lateinit var measurementTypePresenter: MeasurementTypePresenter

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    var isAddRequest: Boolean = false


    fun initToolBar() {
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.addSelfMonitor)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initDI() {
        val component = DaggerSelfMonitorAddComponent.builder()
                .selfMonitorModule(SelfMonitorModule(this))
                .measurementTypeModule(MeasurementTypeModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()

        measurementTypePresenter.attachView(this)
        measurementTypePresenter.loadMeasurementTypeList()
    }

    private fun initDatePicker() {
        var calendar = Calendar.getInstance()
        val timePicker = DatePickerDialog(this@SelfMonitorAddActivity, R.style.AppTheme_DialogSlideAnimwithback,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    calendar = Calendar.getInstance()
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val date = calendar.time
//                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val sdt = sdf.format(date)
                    selfMonitorAddViewHolder.measureDate.setText(sdt)
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        timePicker.show()
    }

    private fun initTimePicker() {
        var calendar = Calendar.getInstance()
        val timePicker = TimePickerDialog(this@SelfMonitorAddActivity, R.style.AppTheme_DialogSlideAnimwithback,
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    calendar = Calendar.getInstance()
                    calendar.set(Calendar.HOUR, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)
                    calendar.set(Calendar.AM_PM, Calendar.AM)
                    val date = calendar.time
                    //SimpleDateFormat sdf12 = new SimpleDateFormat("HH:mm a", Locale.ENGLISH);
                    val sdf = SimpleDateFormat("HH:mm", Locale.ENGLISH)
                    val sdt = sdf.format(date)
//                    Log.v("hour", S(hourOfDay))
//                    Log.v("minute", String.valueOf(minute))
                    selfMonitorAddViewHolder.measureTime.setText(sdt)
                }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true)
        timePicker.show()
    }

    private lateinit var selfMonitorAddViewHolder: SelfMonitorAddViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.self_monitor_add_layout)
        ButterKnife.bind(this)
        selfMonitorAddViewHolder = SelfMonitorAddViewHolder(findViewById(android.R.id.content))
        initToolBar()
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                if (isAddRequest) {
                    onSaveButtonClick(stateLayout)
                } else {
                    measurementTypePresenter.loadMeasurementTypeList()
                }
            }

            override fun onRequestPermission() {

            }
        })

    }

    @OnTouch(R.id.measureDate)
    fun onMeasureDateTouch(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action != KeyEvent.ACTION_DOWN)
            return false
        mainHelper.hideKeyboard(view)
        initDatePicker()
        return false
    }

    @OnTouch(R.id.measureTime)
    fun onMeasureTimeTouch(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action != KeyEvent.ACTION_DOWN)
            return false
        mainHelper.hideKeyboard(view)
        initTimePicker()
        return false
    }

    @OnClick(R.id.saveBtn)
    fun onSaveButtonClick(view: View) {
        if (selfMonitorAddViewHolder.isValid()) {
            presenter.addSelfMonitor(selfMonitorAddModel = selfMonitorAddViewHolder.getSelfMonitorAddModel())
        }
    }


    @Optional
    @OnFocusChange(R.id.measureDate, R.id.measureTime)
    fun onFocusChanged(view: View, isFocused: Boolean) {
        if (isFocused)
            mainHelper.hideKeyboard(view)
    }

    /*Self monitor presenter started*/
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

    override fun onSelfMonitorAddedSuccessfully() {
        Toast.makeText(baseContext, R.string.SelfMonitorAddedSuccessfully, Toast.LENGTH_LONG).show()
        RxBus.publish(MessageEvent(EventActions.SelfMonitorAddActivity_Tag, 1))
        finish()
        Log.v("", "")
    }

    override fun onSelfMonitorAddedFailed() {
        Toast.makeText(baseContext, R.string.AddSelfMonitorFailed, Toast.LENGTH_LONG).show()
        Log.v("", "")
    }
    /*Self monitor presenter ended*/


    /*Measurement Type presenter started*/
    override fun showMeasurementTypeProgress(show: Boolean) {
        if (show) {
            stateLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showMeasurementTypeEmptyView(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Nodatalayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showMeasurementTypeLoadErrorMessage(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Noconnectionlayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun onMeasurementTypeListLoaded(measurementTypeList: MutableList<MeasurementType>) {
        selfMonitorAddViewHolder.measureTypeSpinner.adapter =
                MeasurementTypeSpinnerAdapter(context = baseContext, measurementTypeList = measurementTypeList)
        isAddRequest = true
        Log.v("", "")
    }
    /*Measurement Type presenter ended*/

}