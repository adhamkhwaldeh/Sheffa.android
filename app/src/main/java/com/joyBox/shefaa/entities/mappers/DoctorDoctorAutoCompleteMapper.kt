package com.joyBox.shefaa.entities.mappers

import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.entities.DoctorAutoComplete

/**
 * Created by Adhamkh on 2018-11-03.
 */
class DoctorDoctorAutoCompleteMapper {

    companion object {

        fun doctorDoctorAutoCompleteTransform(doctorList: MutableList<Doctor>): MutableList<DoctorAutoComplete> {
            val doctorAutoCompleteList: MutableList<DoctorAutoComplete> = mutableListOf()
            doctorList.forEach {
                doctorAutoCompleteList.add(doctorDoctorAutoCompleteTransform(it))
            }
            return doctorAutoCompleteList
        }

        fun doctorDoctorAutoCompleteTransform(doctor: Doctor): DoctorAutoComplete {
            val doctorAutoComplete: DoctorAutoComplete = DoctorAutoComplete()

            doctorAutoComplete.doctor_id = doctor.doctor_id
            doctorAutoComplete.nothing = doctor.name
            doctorAutoComplete.name = doctor.name
            return doctorAutoComplete
        }
    }

}
