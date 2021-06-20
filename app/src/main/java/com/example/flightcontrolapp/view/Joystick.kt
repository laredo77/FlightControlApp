package com.example.flightcontrolapp.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import com.example.flightcontrolapp.R
import com.jackandphantom.joystickview.JoyStickView

class Joystick(joyStickView: JoyStickView) : AppCompatActivity() {

    private var joystick : JoyStickView = joyStickView
    private var angle : Double = 0.0
    private var strength : Float = 0.0F

    init {
        initStyle()
    }

    /*
     joystick view initializations. defining how the joystick looks,
     size and colors.
     */
    fun initStyle() {
        joystick.setInnerCircleColor(Color.BLACK)
        joystick.setInnerCircleImageResId(R.mipmap.ic_joystic_round)
        joystick.setInnerCircleRadius(0.20f)
        joystick.setOuterCircleBorderColor(Color.RED)
        joystick.setOuterCircleBorderStrokeWidth(5F)
        joystick.setOuterCircleColor(Color.TRANSPARENT)
        joystick.setLockCenter(true)
        joystick.setShadowColor(Color.BLACK)
        joystick.setShadowRadius(7f)
        joystick.setShadowDxAndDy(5f, 5f)
    }

    /*
     when the joystick had change in its position, save the new position.
     */
    fun onChange(angel: Double, strength: Float) {
        this.angle = angle
        this.strength = strength
    }
}