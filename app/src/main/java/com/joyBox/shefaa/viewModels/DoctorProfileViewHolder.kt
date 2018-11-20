package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.brainsocket.globalpages.enums.DaysEnum
import com.joyBox.shefaa.adapters.DoctorSpecializationTagRecyclerViewAdapter
import com.joyBox.shefaa.entities.*
import com.joyBox.shefaa.enums.AppointmentPlace
import com.joyBox.shefaa.helpers.ClinicHourHelper
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepository

class DoctorProfileViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

    val context: Context = view.context

    var specialistAutoCompleteList: MutableList<SpecialistAutoComplete>? = null

    @BindView(R.id.city)
    lateinit var city: EditText

    @BindView(R.id.area)
    lateinit var area: EditText

    @BindView(R.id.street)
    lateinit var street: EditText

    @BindView(R.id.specializationRecyclerView)
    lateinit var specializationRecyclerView: RecyclerView

    @BindView(R.id.phone)
    lateinit var phone: EditText

    @BindView(R.id.cost)
    lateinit var cost: EditText

    @BindView(R.id.homeAppointment)
    lateinit var homeAppointment: CheckBox


    @BindView(R.id.saturdayStartDate)
    lateinit var saturdayStartDate: TextView

    @BindView(R.id.saturdayToDate)
    lateinit var saturdayToDate: TextView


    @BindView(R.id.sundayStartDate)
    lateinit var sundayStartDate: TextView

    @BindView(R.id.sundayToDate)
    lateinit var sundayToDate: TextView

    @BindView(R.id.monDayStartDate)
    lateinit var monDayStartDate: TextView

    @BindView(R.id.monDayToDate)
    lateinit var monDayToDate: TextView


    @BindView(R.id.tuesdayStartDate)
    lateinit var tuesdayStartDate: TextView

    @BindView(R.id.tuesdayToDate)
    lateinit var tuesdayToDate: TextView


    @BindView(R.id.wednesdayStartDate)
    lateinit var wednesdayStartDate: TextView

    @BindView(R.id.wednesdayToDate)
    lateinit var wednesdayToDate: TextView


    @BindView(R.id.thursdayStartDate)
    lateinit var thursdayStartDate: TextView

    @BindView(R.id.thursdayToDate)
    lateinit var thursdayToDate: TextView

    @BindView(R.id.fridayStartDate)
    lateinit var fridayStartDate: TextView

    @BindView(R.id.fridayToDate)
    lateinit var fridayToDate: TextView


    init {
        ButterKnife.bind(this, view)
    }

    fun bindSpecialistList(specialistAutoCompleteList: MutableList<SpecialistAutoComplete>) {
        this.specialistAutoCompleteList = specialistAutoCompleteList
    }

    fun bind(doctorProfile: DoctorProfile) {
        if (doctorProfile.field_address_map != null) {
            city.setText(doctorProfile.field_address_map.city)
            street.setText(doctorProfile.field_address_map.street)
        }

        val doctorSpecialistAutoCompleteList: MutableList<SpecialistAutoComplete> = mutableListOf()
        specialistAutoCompleteList?.forEach {
            if (doctorProfile.field_doctor_specialization.contains(it.name)) {
                doctorSpecialistAutoCompleteList.add(it)
            }
        }
        specializationRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        specializationRecyclerView.adapter = DoctorSpecializationTagRecyclerViewAdapter(context, doctorSpecialistAutoCompleteList)


        phone.setText(doctorProfile.field_phone)
        cost.setText(doctorProfile.field_cost)

        homeAppointment.isChecked = (doctorProfile.field_home_doctor == AppointmentPlace.Home.place)

        val sunDay: DoctorOpenHour? = doctorProfile.field_open_hours
                .firstOrNull { it.day == (DaysEnum.SunDay.number.toString()) }
        if (sunDay != null) {
            sundayStartDate.text = ClinicHourHelper.getTime(sunDay.starthours)
            sundayToDate.text = ClinicHourHelper.getTime(sunDay.endhours)
        } else {
            sundayStartDate.text = ""
            sundayToDate.text = ""
        }

        val monDay: DoctorOpenHour? = doctorProfile.field_open_hours
                .firstOrNull { it.day == (DaysEnum.MonDay.number.toString()) }

        if (monDay != null) {
            monDayStartDate.text = ClinicHourHelper.getTime(monDay.starthours)
            monDayToDate.text = ClinicHourHelper.getTime(monDay.endhours)
        } else {
            monDayStartDate.text = ""
            monDayStartDate.text = ""
        }

        val tueDay: DoctorOpenHour? = doctorProfile.field_open_hours
                .firstOrNull { it.day == (DaysEnum.TueDay.number.toString()) }

        if (tueDay != null) {
            tuesdayStartDate.text = ClinicHourHelper.getTime(tueDay.starthours)
            tuesdayToDate.text = ClinicHourHelper.getTime(tueDay.endhours)
        } else {
            tuesdayStartDate.text = ""
            tuesdayToDate.text = ""
        }


        val wenDay: DoctorOpenHour? = doctorProfile.field_open_hours
                .firstOrNull { it.day == (DaysEnum.WenDay.number.toString()) }

        if (wenDay != null) {
            wednesdayStartDate.text = ClinicHourHelper.getTime(wenDay.starthours)
            wednesdayToDate.text = ClinicHourHelper.getTime(wenDay.endhours)
        } else {
            wednesdayStartDate.text = ""
            wednesdayToDate.text = ""
        }

        val thurDay: DoctorOpenHour? = doctorProfile.field_open_hours
                .firstOrNull { it.day == (DaysEnum.ThuDay.number.toString()) }

        if (thurDay != null) {
            thursdayStartDate.text = ClinicHourHelper.getTime(thurDay.starthours)
            thursdayToDate.text = ClinicHourHelper.getTime(thurDay.endhours)
        } else {
            thursdayStartDate.text = ""
            thursdayToDate.text = ""
        }

        val friDay: DoctorOpenHour? = doctorProfile.field_open_hours
                .firstOrNull { it.day == (DaysEnum.FriDay.number.toString()) }
        if (friDay != null) {
            fridayStartDate.text = ClinicHourHelper.getTime(friDay.starthours)
            fridayToDate.text = ClinicHourHelper.getTime(friDay.endhours)
        } else {
            fridayStartDate.text = ""
            fridayToDate.text = ""
        }


        val satDay: DoctorOpenHour? = doctorProfile.field_open_hours
                .firstOrNull { it.day == (DaysEnum.SatDay.number.toString()) }
        if (satDay != null) {
            saturdayStartDate.text = ClinicHourHelper.getTime(satDay.starthours)
            saturdayToDate.text = ClinicHourHelper.getTime(satDay.endhours)
        } else {
            saturdayStartDate.text = ""
            saturdayToDate.text = ""
        }

    }

    fun isValid(): Boolean {
        return true
    }

    fun getModel(): DoctorProfile {
        val doctorProfile = DoctorProfile()

        if (doctorProfile.field_address_map == null) {
            doctorProfile.field_address_map = MapAddress()
        }
        doctorProfile.field_address_map.city = city.text.toString()
        doctorProfile.field_address_map.street = street.text.toString()


//        doctorProfile.field_doctor_specialization = specialization.text.toString()

        doctorProfile.field_phone = phone.text.toString()
        doctorProfile.field_cost = cost.text.toString()

        if (homeAppointment.isChecked) {
            doctorProfile.field_home_doctor = AppointmentPlace.Home.place
        } else {
            doctorProfile.field_home_doctor = AppointmentPlace.Clinic.place
        }

        if (doctorProfile.field_open_hours == null)
            doctorProfile.field_open_hours = mutableListOf()


        if (!(sundayStartDate.text.isNullOrBlank() || sundayToDate.text.isNullOrBlank())) {
            doctorProfile.field_open_hours.add(DoctorOpenHour(DaysEnum.SunDay.number.toString(),
                    ClinicHourHelper.getTimeWithoutFormate(sundayStartDate.text.toString()),
                    ClinicHourHelper.getTimeWithoutFormate(sundayToDate.text.toString())))
        }

        if (!(monDayStartDate.text.isNullOrBlank() || monDayToDate.text.isNullOrBlank())) {
            doctorProfile.field_open_hours.add(DoctorOpenHour(DaysEnum.MonDay.number.toString(),
                    ClinicHourHelper.getTimeWithoutFormate(monDayStartDate.text.toString()),
                    ClinicHourHelper.getTimeWithoutFormate(monDayToDate.text.toString())))
        }

        if (!(tuesdayStartDate.text.isNullOrBlank() || tuesdayToDate.text.isNullOrBlank())) {
            doctorProfile.field_open_hours.add(DoctorOpenHour(DaysEnum.TueDay.number.toString(),
                    ClinicHourHelper.getTimeWithoutFormate(tuesdayStartDate.text.toString()),
                    ClinicHourHelper.getTimeWithoutFormate(tuesdayToDate.text.toString())))
        }

        if (!(wednesdayStartDate.text.isNullOrBlank() || wednesdayToDate.text.isNullOrBlank())) {
            doctorProfile.field_open_hours.add(DoctorOpenHour(DaysEnum.WenDay.number.toString(),
                    ClinicHourHelper.getTimeWithoutFormate(wednesdayStartDate.text.toString()),
                    ClinicHourHelper.getTimeWithoutFormate(wednesdayToDate.text.toString())))
        }

        if (!(thursdayStartDate.text.isNullOrBlank() || thursdayToDate.text.isNullOrBlank())) {
            doctorProfile.field_open_hours.add(DoctorOpenHour(DaysEnum.ThuDay.number.toString(),
                    ClinicHourHelper.getTimeWithoutFormate(thursdayStartDate.text.toString()),
                    ClinicHourHelper.getTimeWithoutFormate(thursdayToDate.text.toString())))
        }

        if (!(fridayStartDate.text.isNullOrBlank() || fridayToDate.text.isNullOrBlank())) {
            doctorProfile.field_open_hours.add(DoctorOpenHour(DaysEnum.FriDay.number.toString(),
                    ClinicHourHelper.getTimeWithoutFormate(fridayStartDate.text.toString()),
                    ClinicHourHelper.getTimeWithoutFormate(fridayToDate.text.toString())))
        }

        if (!(saturdayStartDate.text.isNullOrBlank() || saturdayToDate.text.isNullOrBlank())) {
            doctorProfile.field_open_hours.add(DoctorOpenHour(DaysEnum.SatDay.number.toString(),
                    ClinicHourHelper.getTimeWithoutFormate(saturdayStartDate.text.toString()),
                    ClinicHourHelper.getTimeWithoutFormate(saturdayToDate.text.toString())))
        }

        return doctorProfile
    }

    fun getUpdateUrl(): String {
        val doctorProfile: DoctorProfile = getModel()
        val userId = UserRepository(context).getClient()!!.user.uid
        var url = NetworkingHelper.GeneralProfile_Update_Url + "?uid=" + userId + "&type=doctor&"
        url += "phone=" + doctorProfile.field_phone
        url += "&place=" + doctorProfile.field_address_map.source + "&city=" + doctorProfile.field_address_map.city
        //&lat=33.513807&long=36.276527999&
        url += "&cost=" + cost.text.toString() + "&duration=100" + "&home=" + doctorProfile.field_home_doctor


        val list: MutableList<SpecialistAutoComplete> = (specializationRecyclerView.adapter as DoctorSpecializationTagRecyclerViewAdapter).specializationList
        if (list.size > 0)
            url += "&specialization=" + list.joinToString { it.tid }


        val sunDay: DoctorOpenHour? = doctorProfile.field_open_hours
                .firstOrNull { it.day == (DaysEnum.SunDay.number.toString()) }
        if (sunDay != null) {
            url += "&day0start=" + ClinicHourHelper.getTime(sunDay.starthours) +
                    "&day0end=" + ClinicHourHelper.getTime(sunDay.endhours)
        }

        val monDay: DoctorOpenHour? = doctorProfile.field_open_hours
                .firstOrNull { it.day == (DaysEnum.MonDay.number.toString()) }

        if (monDay != null) {
            url += "&day1start=" + ClinicHourHelper.getTime(monDay.starthours) +
                    "&day1end=" + ClinicHourHelper.getTime(monDay.endhours)
        }

        val tueDay: DoctorOpenHour? = doctorProfile.field_open_hours
                .firstOrNull { it.day == (DaysEnum.TueDay.number.toString()) }

        if (tueDay != null) {
            url += "&day2start=" + ClinicHourHelper.getTime(tueDay.starthours) +
                    "&day2end=" + ClinicHourHelper.getTime(tueDay.endhours)
        }


        val wenDay: DoctorOpenHour? = doctorProfile.field_open_hours
                .firstOrNull { it.day == (DaysEnum.WenDay.number.toString()) }

        if (wenDay != null) {
            url += "&day3start=" + ClinicHourHelper.getTime(wenDay.starthours) +
                    "&day3end=" + ClinicHourHelper.getTime(wenDay.endhours)
        }

        val thurDay: DoctorOpenHour? = doctorProfile.field_open_hours
                .firstOrNull { it.day == (DaysEnum.ThuDay.number.toString()) }

        if (thurDay != null) {
            url += "&day4start=" + ClinicHourHelper.getTime(thurDay.starthours) +
                    "&day4end=" + ClinicHourHelper.getTime(thurDay.endhours)
        }

        val friDay: DoctorOpenHour? = doctorProfile.field_open_hours
                .firstOrNull { it.day == (DaysEnum.FriDay.number.toString()) }
        if (friDay != null) {
            url += "&day5start=" + ClinicHourHelper.getTime(friDay.starthours) +
                    "&day5end=" + ClinicHourHelper.getTime(friDay.endhours)
        }

        val satDay: DoctorOpenHour? = doctorProfile.field_open_hours
                .firstOrNull { it.day == (DaysEnum.SatDay.number.toString()) }
        if (satDay != null) {
            url += "&day6start=" + ClinicHourHelper.getTime(satDay.starthours) +
                    "&day6end=" + ClinicHourHelper.getTime(satDay.endhours)
        }

        return url
    }

}