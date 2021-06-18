package com.example.flightcontrolapp.view_model

import android.text.Editable
import com.example.flightcontrolapp.model.FGPlayer

class ViewModel {

    var model = FGPlayer()

    fun ViewModel() {
        // constructor
    }

    fun onChange_throttle(value: Int) {
        model.set_throttle(value)
    }

    fun onChange_rudder(value: Int) {
        model.set_rudder(value)
    }

    fun Connect(ip: Editable, port: String) {
        model.connect(ip, port)
    }
}