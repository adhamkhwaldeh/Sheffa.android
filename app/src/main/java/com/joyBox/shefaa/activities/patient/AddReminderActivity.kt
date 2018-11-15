package com.joyBox.shefaa.activities.patient

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.di.component.DaggerAddReminderComponent
import com.joyBox.shefaa.di.module.ReminderModule
import com.joyBox.shefaa.di.ui.ReminderContract
import com.joyBox.shefaa.di.ui.ReminderPresenter
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.localJobs.MainJobManager
import com.joyBox.shefaa.viewModels.AddReminderViewHolder
import com.joyBox.shefaa.views.Stateslayoutview
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class AddReminderActivity : BaseActivity(), ReminderContract.View {

    companion object {

        const val AddReminderActivity_Tag = "AddReminderActivity_Tag"

    }

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @Inject
    lateinit var presenter: ReminderPresenter

    lateinit var addReminderViewHolder: AddReminderViewHolder

    private fun initDI() {
        val component = DaggerAddReminderComponent.builder()
                .reminderModule(ReminderModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
    }

    private fun initDatePicker() {
        var calendar = Calendar.getInstance()
        val timePicker = DatePickerDialog(this@AddReminderActivity, R.style.AppTheme_DialogSlideAnimwithback,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    calendar = Calendar.getInstance()
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val date = calendar.time
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    val sdt = sdf.format(date)
                    addReminderViewHolder.dateTextView.text = sdt
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        timePicker.show()
    }

    private fun initTimePicker() {
        var calendar = Calendar.getInstance()
        val timePicker = TimePickerDialog(this@AddReminderActivity, R.style.AppTheme_DialogSlideAnimwithback,
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
                    addReminderViewHolder.timeTextView.text = sdt
                }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true)
        timePicker.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_reminder_layout)
        ButterKnife.bind(this)
        addReminderViewHolder = AddReminderViewHolder(findViewById(android.R.id.content),
                intent.getStringExtra(AddReminderActivity_Tag))
        initDI()

    }

    @OnClick(R.id.dateContainer)
    fun onDateContainerClick(view: View) {
        initDatePicker()
    }

    @OnClick(R.id.timeContainer)
    fun onTimeContainerClick(view: View) {
        initTimePicker()
    }

    @OnClick(R.id.remindBtn)
    fun onRemindButtonClick(view: View) {
        if (addReminderViewHolder.isValid()) {
            presenter.remind(addReminderViewHolder.getUrl())
        }
    }

    /*Add reminder presenter started*/


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

        stringList.forEach {
            val time: Long = it.toLong() - System.currentTimeMillis()
            MainJobManager.addJob2Scheduler(time)
        }
        Toast.makeText(baseContext, baseContext.getString(R.string.ReminderSetCorrectly), Toast.LENGTH_LONG).show()
        Log.v("", "")
    }

    /*Add reminder presenter ended*/

}