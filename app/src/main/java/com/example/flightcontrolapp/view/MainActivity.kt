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

        /*
         button click listener.
         on click, will try to connect to the server.
         if IP/Port invalid, pop up message will show.
         otherwise, initialize vm with the ip&port data.
        */
        btnConnect.setOnClickListener {
            val ip = ipTextBox.text
            val port = portTextBox.text.toString()
            try {
                VM = ViewModel(ip.toString(), port.toInt())
                enable = true
            } catch (e : Exception) {
                val ad = AlertDialog.Builder(this)
                ad.setTitle("Error")
                ad.setMessage("Couldn't connect to the server." +
                        "\nPlease submit valid IP and Port before pressing the connect button.")

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
        }

        /*
         throttle listener. when the user will change the value
         of the throttle seekbar, the listener will know it and call
         vm onchange function.
         */
        val throttle = findViewById<SeekBar>(R.id.throttleBar)
        throttle?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(throttle: SeekBar,
                                           progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(throttle: SeekBar) {}

            override fun onStopTrackingTouch(throttle: SeekBar) {
                if (enable)
                    VM.onChange_throttle(throttle.progress)
            }
        })

        /*
         rudder listener. when the user will change the value
         of the rudder seekbar, the listener will know it and call
         vm onchange function.
         */
        val rudder = findViewById<SeekBar>(R.id.rudderBar)
        rudder?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(rudder: SeekBar,
                                           progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(rudder: SeekBar) {}

            override fun onStopTrackingTouch(rudder: SeekBar) {
                if (enable)
                    VM.onChange_rudder(rudder.progress)
            }
        })

        /*
         initialize joystick with joystickview interface.
         */
        val joyStickView = findViewById<JoyStickView>(R.id.joy)
        val joystick = Joystick(joyStickView)

        /*
         joystick listener. when user move the joystick, the in the lambada expression
         calculated the x and y axes values according the movement.
         then, send the data to vm in order to send the data to the server.
         */
        joyStickView.setOnMoveListener { angle, strength ->
            joystick.onChange(angle, strength)
            if (enable) {
                val dx: Double = strength * cos(Math.toRadians(angle))
                val dy: Double = strength * sin(Math.toRadians(angle))
                VM.setAileron(dx.toFloat())
                VM.setElevator(dy.toFloat())
            }
        }

        /*
         disconnect listener. when user press the disconnect button
         this function will send alert to vm disconnect function.
         */
        btnDisconnect.setOnClickListener {
            if (enable)
                VM.disconnect()
        }
    }
}