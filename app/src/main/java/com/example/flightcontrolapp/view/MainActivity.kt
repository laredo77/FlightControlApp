package com.example.flightcontrolapp

//import android.R
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.flightcontrolapp.model.*
import com.example.flightcontrolapp.view.Joystick
import com.example.flightcontrolapp.view_model.ViewModel
import com.jackandphantom.joystickview.JoyStickView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //private lateinit var joystick: Joystick
    private lateinit var VM: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnConnect.setOnClickListener {
            val ip = ipTextBox.text
            val port = portTextBox.text.toString()
            VM = ViewModel(ip.toString(), port.toInt())
        }

        val throttle = findViewById<SeekBar>(R.id.throttleBar)
        throttle?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(throttle: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
            }

            override fun onStartTrackingTouch(throttle: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(throttle: SeekBar) {
                VM.onChange_throttle(throttle.progress)
            }
        })

        val rudder = findViewById<SeekBar>(R.id.rudderBar)
        rudder?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(rudder: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
            }

            override fun onStartTrackingTouch(rudder: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(rudder: SeekBar) {
                VM.onChange_rudder(rudder.progress)
            }
        })

        val joyStickView = findViewById<JoyStickView>(R.id.joy)
        val joystick : Joystick = Joystick(joyStickView)

        joyStickView.setOnMoveListener { angle, strength ->
            //joystick.onChange(angle, strength)
            println("angle: " + angle + "strength: " + strength)
        }

        //joystick = Joystick()

//        //val joyStickView = findViewById<JoyStickView>(R.id.joy)
//        joyStickView.setOnMoveListener { angle, strength ->
//            joystick.onChange(angle, strength.toDouble())
//        }


        //joystick.setOnMoveListener(OnMoveListener { angle, strength -> })
//        joystick = Joystick()
//        joystick.onChange = (a, e)->{
//
//        }

//        val joyStickView = findViewById<JoyStickView>(R.id.joy)
//        joyStickView.setOnMoveListener { angle, strength -> }

    }
}