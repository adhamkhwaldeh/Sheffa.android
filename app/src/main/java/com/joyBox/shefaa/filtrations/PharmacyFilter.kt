package com.joyBox.shefaa.filtrations


class PharmacyFilter : BaseFilter {

    var medicineName: String? = null

    constructor() : super()
    constructor(query: String?, medicineName: String?) : super() {
        this.query = query
        this.medicineName = medicineName
    }


}