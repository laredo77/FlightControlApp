package com.example.flightcontrolapp

import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flightcontrolapp.model.*
import com.example.flightcontrolapp.view.Joystick
import android.widget.SeekBar
import android.widget.Toast
import com.example.flightcontrolapp.view_model.ViewModel

class MainActivity : AppCompatActivity() {

    //private lateinit var fgPlayer: FGPlayer
    //private lateinit var client: Client
    private lateinit var joystick: Joystick
    private lateinit var VM: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fgPlayer = FGPlayer()
        //VM = ViewModel()

        btnConnect.setOnClickListener {
            val ip = ipTextBox.text
            val port = portTextBox.text.toString()
            VM = ViewModel(ip.toString(), port.toInt())
        }

//        val throttle = findViewById<SeekBar>(R.id.throttleBar)
//        throttle?.setOnSeekBarChangeListener(object :
//            SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(throttle: SeekBar,
//                                           progress: Int, fromUser: Boolean) {
//                // write custom code for progress is changed
//                VM.onChange_throttle(throttle.progress)
//            }
//
//            override fun onStartTrackingTouch(throttle: SeekBar) {
//                // write custom code for progress is started
//            }
//
//            override fun onStopTrackingTouch(throttle: SeekBar) {
//                //println(throttle.progress)
//                // write custom code for progress is stopped
////                    Toast.makeText(this@MainActivity,
////                        "Progress is: " + seek.progress + "%",
////                        Toast.LENGTH_SHORT).show()
//            }
//        })

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
                //println(rudder.progress)
                // write custom code for progress is stopped
//                    Toast.makeText(this@MainActivity,
//                        "Progress is: " + seek.progress + "%",
//                        Toast.LENGTH_SHORT).show()
            }
        })

//        joystick = Joystick()
//        joystick.onChange = (a, e)->{
//        }



    }
}