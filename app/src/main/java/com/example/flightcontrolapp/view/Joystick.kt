package com.example.flightcontrolapp.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import com.jackandphantom.joystickview.JoyStickView
import kotlin.math.cos
import kotlin.math.sin

class Joystick(joyStickView: JoyStickView) : AppCompatActivity() {

    private var joystick : JoyStickView = joyStickView
    private var angle : Double = 0.0
    private var strength : Float = 0.0F

    init {
//        joystick.setInnerCircleColor(Color.BLACK)
//        joyStickView.setInnerCircleRadius(0.1f)
//        joyStickView.setOuterCircleBorderColor(Color.RED)
//        joyStickView.setOuterCircleBorderStrokeWidth(15F)
//        joyStickView.setOuterCircleColor(Color.WHITE)
//        joyStickView.setLockCenter(false)
//        joyStickView.setShadowColor(Color.BLACK)
//        joyStickView.setShadowRadius(7f)
//        joyStickView.setShadowDxAndDy(5f, 5f)
        initStyle()
    }

    fun initStyle() {
        joystick.setInnerCircleColor(Color.BLACK)
        joystick.setInnerCircleRadius(0.1f)
        joystick.setOuterCircleBorderColor(Color.RED)
        joystick.setOuterCircleBorderStrokeWidth(15F)
        joystick.setOuterCircleColor(Color.WHITE)
        joystick.setLockCenter(true)
        joystick.setShadowColor(Color.BLACK)
        joystick.setShadowRadius(7f)
        joystick.setShadowDxAndDy(5f, 5f)
    }

    fun onChange(angel: Double, strength: Float) {
        this.angle = angle
        this.strength = strength

        val dx: Double = -strength * cos(Math.toRadians(angle + 90))
        val dy: Double = -strength * sin(Math.toRadians(angle + 90))
    }

}