package com.joyBox.shefaa.filtrations

/**
 * Created by Adhamkh on 2018-11-10.
 */
class LabFilter : BaseFilter {
    var address: String? = null

    constructor() : super()

    constructor(query: String, city: String?, address: String) {
        this.query = query
        this.city = city
        this.address = address
    }

}