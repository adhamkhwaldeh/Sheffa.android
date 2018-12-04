package com.joyBox.shefaa.filtrations

import com.joyBox.shefaa.entities.SpecialistAutoComplete

class DoctorFilter : BaseFilter {

    var specialistAutoComplete: SpecialistAutoComplete? = null
    var cost: String? = null

    constructor() : super()

    constructor(query: String, specialistAutoComplete: SpecialistAutoComplete?, cost: String?, city: String?) : super() {
        this.query = query
        this.specialistAutoComplete = specialistAutoComplete
        this.cost = cost
        this.city = city
    }


}