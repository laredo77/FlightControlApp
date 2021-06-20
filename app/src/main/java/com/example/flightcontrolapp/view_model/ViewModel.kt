package com.example.flightcontrolapp.view_model

import com.example.flightcontrolapp.model.FGPlayer

/*
 this class connect the view's classes to the model calculating and biding data class.
 in this class appear few functions and called by the view and send their arguments
 to the model, in purpose to transfer the data to the server.
 the idea of this class is to separate view and model classes.
 */
class ViewModel(ip: String?, port: Int) {

    var model : FGPlayer

    init {
        model = FGPlayer(ip, port)
    }

    fun onChange_throttle(value: Int) {
        model.set_throttle(value)
    }

    fun onChange_rudder(value: Int) {
        model.set_rudder(value)
    }

    fun setAileron(value : Float) {
        model.setAileron(value)
    }

    fun setElevator(value : Float) {
        model.setElevator(value)
    }

    fun disconnect() {
        model.close()
    }
}