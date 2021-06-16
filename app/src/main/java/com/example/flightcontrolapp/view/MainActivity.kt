package com.example.flightcontrolapp

import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flightcontrolapp.model.*
import com.example.flightcontrolapp.view.Joystick
import android.widget.SeekBar
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var fgPlayer: FGPlayer
    private lateinit var client: Client
    //private lateinit var joystick: Joystick
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fgPlayer = FGPlayer()

        btnConnect.setOnClickListener {
            val ip = ipTextBox.text.toString()
            val port = ipTextBox.text.toString()
            if(ip.isEmpty() or port.isEmpty()) {
                // should check if its legal address and digits and so on..
                println("error message") // pop up some error message
            } else {
                client = Client(ip, port.toInt())
            }
            //println("Hello World!")

            val seek = findViewById<SeekBar>(R.id.seekBar)
            seek?.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seek: SeekBar,
                                               progress: Int, fromUser: Boolean) {
                    // write custom code for progress is changed
                }

                override fun onStartTrackingTouch(seek: SeekBar) {
                    // write custom code for progress is started
                }

                override fun onStopTrackingTouch(seek: SeekBar) {
                    println(seek.progress)
                    // write custom code for progress is stopped
//                    Toast.makeText(this@MainActivity,
//                        "Progress is: " + seek.progress + "%",
//                        Toast.LENGTH_SHORT).show()
                }
            })
        }



    }
}