package com.example.flightcontrolapp.view_model

import android.text.Editable
import com.example.flightcontrolapp.model.FGPlayer

class ViewModel(ip: String?, port: Int) {

    var model : FGPlayer

    init {
        model = FGPlayer(ip, port)
    }


//    fun onChange_throttle(value: Int) {
//        model.set_throttle(value)
//    }

    fun onChange_rudder(value: Int) {
        model.set_rudder(value)
    }

//    fun Connect(ip: Editable, port: String) {
//        model.connect(ip, port)
//    }
}