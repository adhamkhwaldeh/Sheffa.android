package com.joyBox.shefaa.entities

/**
 * Created by Adhamkh on 2018-11-19.
 */
class GeneralReportItem {

    var name: String = ""
    var incoming: String = ""
    var spending: String = ""
    var net: String = ""

    constructor(name: String, incoming: String, spending: String, net: String) {
        this.name = name
        this.incoming = incoming
        this.spending = spending
        this.net = net
    }
}