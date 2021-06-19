package com.example.flightcontrolapp

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.flightcontrolapp.view.Joystick
import com.example.flightcontrolapp.view_model.ViewModel
import com.jackandphantom.joystickview.JoyStickView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.math.cos
import kotlin.math.sin
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var VM: ViewModel
    private var enable : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnConnect.setOnClickListener {
            val ip = ipTextBox.text
            val port = portTextBox.text.toString()
            try {
                VM = ViewModel(ip.toString(), port.toInt())
            } catch (e : Exception) {
                val ad = AlertDialog.Builder(this)
                ad.setTitle("Error")
                ad.setMessage("Couldn't connect to the server.\nPlease submit your IP and Port before pressing connect button")

                ad.setPositiveButton(android.R.string.yes) { dialog, which ->
                    Toast.makeText(applicationContext,
                        android.R.string.yes, Toast.LENGTH_SHORT).show()
                }

                ad.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(applicationContext,
                        android.R.string.no, Toast.LENGTH_SHORT).show()
                }
                ad.show()
            }
            enable = true
        }

        val throttle = findViewById<SeekBar>(R.id.throttleBar)
        throttle?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(throttle: SeekBar,
                                           progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(throttle: SeekBar) {
            }

            override fun onStopTrackingTouch(throttle: SeekBar) {
                if (enable)
                    VM.onChange_throttle(throttle.progress)
            }
        })

        val rudder = findViewById<SeekBar>(R.id.rudderBar)
        rudder?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(rudder: SeekBar,
                                           progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(rudder: SeekBar) {
            }

            override fun onStopTrackingTouch(rudder: SeekBar) {
                if (enable)
                    VM.onChange_rudder(rudder.progress)
            }
        })

        val joyStickView = findViewById<JoyStickView>(R.id.joy)
        val joystick = Joystick(joyStickView)

        joyStickView.setOnMoveListener { angle, strength ->
            joystick.onChange(angle, strength)
            if (enable) {
                val dx: Double = strength * cos(Math.toRadians(angle))
                val dy: Double = strength * sin(Math.toRadians(angle))
                VM.setAileron(dx.toFloat())
                VM.setElevator(dy.toFloat())
            }
        }

        btnDisconnect.setOnClickListener {
            if (enable)
                VM.disconnect()
        }
    }
}