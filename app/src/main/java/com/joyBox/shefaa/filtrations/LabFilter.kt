package com.joyBox.shefaa.filtrations

/**
 * Created by Adhamkh on 2018-11-10.
 */
class LabFilter : BaseFilter {
    var address: String? = null

    constructor() : super()

    constructor(query: String, address: String) {
        this.query = query
        this.address = address
    }

}