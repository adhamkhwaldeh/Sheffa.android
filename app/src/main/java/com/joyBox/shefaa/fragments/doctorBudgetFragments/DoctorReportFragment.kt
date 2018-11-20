package com.joyBox.shefaa.fragments.doctorBudgetFragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.DoctorGeneralReportItemRecyclerViewAdapter
import com.joyBox.shefaa.adapters.DoctorReportExpenseRecyclerViewAdapter
import com.joyBox.shefaa.adapters.DoctorReportReceiptRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorReportComponent
import com.joyBox.shefaa.di.module.ReportModule
import com.joyBox.shefaa.di.ui.ReportContract
import com.joyBox.shefaa.di.ui.ReportPresenter
import com.joyBox.shefaa.dialogs.ProgressDialog
import com.joyBox.shefaa.entities.GeneralReport
import com.joyBox.shefaa.entities.GeneralReportItem
import com.joyBox.shefaa.entities.ReportExpense
import com.joyBox.shefaa.entities.ReportReceipt
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.helpers.ReportHelper
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import kotlinx.android.synthetic.main.magazine_post_comment_item.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class DoctorReportFragment : BaseDoctorBudgetFragment(), ReportContract.View {

    companion object {
        fun getNewInstance(): BaseDoctorBudgetFragment {
            val f = DoctorReportFragment()
            f.titleRes = R.string.Reports
            return f
        }
    }

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.generalReportCalenderDate)
    lateinit var generalReportCalenderDate: TextView

    @BindView(R.id.generalReportRecyclerView)
    lateinit var generalReportRecyclerView: RecyclerView

    @BindView(R.id.incomingReportRecyclerView)
    lateinit var incomingReportRecyclerView: RecyclerView

    @BindView(R.id.spendingReportRecyclerView)
    lateinit var spendingReportRecyclerView: RecyclerView

    @BindView(R.id.incomingReportToCalenderTextView)
    lateinit var incomingReportToCalenderTextView: TextView

    @BindView(R.id.incomingReportFromCalenderTextView)
    lateinit var incomingReportFromCalenderTextView: TextView

    @BindView(R.id.incomingReportFooterTextView)
    lateinit var incomingReportFooterTextView: TextView

    @BindView(R.id.spendingReportFromCalenderTextView)
    lateinit var spendingReportFromCalenderTextView: TextView

    @BindView(R.id.spendingReportToCalenderTextView)
    lateinit var spendingReportToCalenderTextView: TextView

    @BindView(R.id.spendingReportFooterTextView)
    lateinit var spendingReportFooterTextView: TextView

    @Inject
    lateinit var presenter: ReportPresenter

    var progressDialog: ProgressDialog = ProgressDialog.newInstance()

    fun initDI() {
        val component = DaggerDoctorReportComponent.builder()
                .reportModule(ReportModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
//        presenter.load(generateRequestUrl())
    }

    private fun initRecyclerViews() {
        generalReportRecyclerView.layoutManager = LinearLayoutManager(context)
        generalReportRecyclerView.addItemDecoration(GridDividerDecoration(context))

        incomingReportRecyclerView.layoutManager = LinearLayoutManager(context)
        incomingReportRecyclerView.addItemDecoration(GridDividerDecoration(context))

        spendingReportRecyclerView.layoutManager = LinearLayoutManager(context)
        spendingReportRecyclerView.addItemDecoration(GridDividerDecoration(context))
    }

    private fun initDatePicker(viewClicked: View) {
        var calendar = Calendar.getInstance()
        val timePicker = DatePickerDialog(context, R.style.AppTheme_DialogSlideAnimwithback,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    calendar = Calendar.getInstance()
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val date = calendar.time
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    val sdt = sdf.format(date)
                    when (viewClicked.id) {
                        R.id.generalReportCalenderContainer -> {
                            generalReportCalenderDate.text = sdt
                            presenter.loadGeneralReport(calendar.get(Calendar.YEAR).toString(),
                                    calendar.get(Calendar.MONTH).toString(), calendar.get(Calendar.DAY_OF_MONTH).toString())
                        }
                        R.id.incomingReportCalenderFromContainer -> {
                            incomingReportFromCalenderTextView.text = sdt
                            if (!incomingReportToCalenderTextView.text.isNullOrBlank())
                                presenter.loadIncomingReport(startDate = incomingReportFromCalenderTextView.text.toString(),
                                        endDate = incomingReportToCalenderTextView.text.toString())
                        }
                        R.id.incomingReportCalenderToContainer -> {
                            incomingReportToCalenderTextView.text = sdt
                            if (!incomingReportFromCalenderTextView.text.isNullOrBlank())
                                presenter.loadIncomingReport(startDate = incomingReportFromCalenderTextView.text.toString(),
                                        endDate = incomingReportToCalenderTextView.text.toString())
                        }
                        R.id.spendingReportCalenderToContainer -> {
                            spendingReportToCalenderTextView.text = sdt
                            if (!spendingReportFromCalenderTextView.text.isNullOrBlank())
                                presenter.loadSpendingReport(startDate = spendingReportFromCalenderTextView.text.toString(),
                                        endDate = spendingReportToCalenderTextView.text.toString())
                        }
                        R.id.spendingReportCalenderFromContainer -> {
                            spendingReportFromCalenderTextView.text = sdt
                            if (!spendingReportToCalenderTextView.text.isNullOrBlank())
                                presenter.loadSpendingReport(startDate = spendingReportFromCalenderTextView.text.toString(),
                                        endDate = spendingReportToCalenderTextView.text.toString())
                        }
                    }
//                    addReminderViewHolder.dateTextView.text = sdt
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        timePicker.show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.doctor_reports_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViews()
        initDI()
    }


    @OnClick(R.id.generalReportCalenderContainer)
    fun ongGeneralReportCalenderContainerClick(view: View) {
        initDatePicker(viewClicked = view)
    }

    @OnClick(R.id.incomingReportCalenderToContainer)
    fun onIncomingReportCalenderToContainerClick(view: View) {
        initDatePicker(viewClicked = view)
    }

    @OnClick(R.id.incomingReportCalenderFromContainer)
    fun onIncomingReportCalenderFromContainerClick(view: View) {
        initDatePicker(viewClicked = view)
    }

    @OnClick(R.id.spendingReportCalenderToContainer)
    fun onSpendingReportCalenderToContainerClick(view: View) {
        initDatePicker(viewClicked = view)
    }

    @OnClick(R.id.spendingReportCalenderFromContainer)
    fun onSpendingReportCalenderFromContainerClick(view: View) {
        initDatePicker(viewClicked = view)
    }

    /*Report presenter started*/

    override fun showProgress(show: Boolean) {
        if (show)
            progressDialog.show(childFragmentManager, ProgressDialog.ProgressDialog_Tag)
        else
            progressDialog.dismiss()
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        if (visible)
            Toast.makeText(context, R.string.PleaseCheckInternetConnection, Toast.LENGTH_LONG).show()
//        if (visible) {
//            stateLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
//        } else {
//            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
//        }
    }

    override fun showEmptyView(visible: Boolean) {
        if (visible)
            Toast.makeText(context, R.string.NoDataFound, Toast.LENGTH_LONG).show()
    }

    override fun onGeneralReportLoaded(generalReport: GeneralReport) {
        val generalReportList: MutableList<GeneralReportItem> = mutableListOf()
        generalReportList.add(GeneralReportItem(name = context!!.resources.getString(R.string.Last_Week),
                incoming = generalReport.lastweek_receipts, spending = generalReport.lastweek_expenses,
                net = generalReport.lastweek_net))

        generalReportList.add(GeneralReportItem(name = context!!.resources.getString(R.string.Last_Month),
                incoming = generalReport.lastmonth_receipts, spending = generalReport.lastweek_expenses,
                net = generalReport.lastmonth_net))

        generalReportList.add(GeneralReportItem(name = context!!.resources.getString(R.string.Last_Year),
                incoming = generalReport.lastyear_receipts, spending = generalReport.lastyear_expenses,
                net = generalReport.lastyear_net))

        generalReportList.add(GeneralReportItem(name = context!!.resources.getString(R.string.Total),
                incoming = generalReport.all_receipts, spending = generalReport.all_expenses,
                net = generalReport.all_net))
        generalReportRecyclerView.adapter = DoctorGeneralReportItemRecyclerViewAdapter(context!!, generalReportList)
        Log.v("", "")
    }

    override fun onSpendingReportLoaded(reportExpenseList: MutableList<ReportExpense>) {
        spendingReportRecyclerView.adapter = DoctorReportExpenseRecyclerViewAdapter(context!!, reportExpenseList)
        spendingReportFooterTextView.text = ReportHelper.totalExpenseReport(reportExpenseList).toString()
    }

    override fun onIncomingReportLoaded(reportReceiptList: MutableList<ReportReceipt>) {
        incomingReportRecyclerView.adapter = DoctorReportReceiptRecyclerViewAdapter(context!!, reportReceiptList)
        incomingReportFooterTextView.text = ReportHelper.totalIncomingReport(reportReceiptList).toString()
    }

    /*Report presenter ended*/

}