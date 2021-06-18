package com.example.flightcontrolapp.model

import android.text.Editable
import com.example.flightcontrolapp.view.Joystick
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.PrintWriter
import java.net.Socket
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread
import kotlin.properties.Delegates

class FGPlayer {

    private var rudder = 0.0 // maybe in constructor
    fun FGPlayer() {
        // constructor
    }

    fun set_throttle(value: Int) {
        //rudder = value * 0.01
        var d_value = value * 0.01
        // send d_value to server /!\
        // send data to server
        // set/controls/flight/aileron(value)
        // set/controls/flight/elevator(value)
        // set/controls/flight/rudder(value)
        // set/controls/engines/current-engine/throttle(value)
    }

    fun set_rudder(value: Int) {
        rudder = value * 0.01
        //var d_value = value * 0.01
    }

    fun get_rudder() : String {
        var str = "set /controls/flight/rudder "
        str += this.rudder
        str += "\r\n"
        return str
    }

    fun connect(ip: Editable, port: String) {
        if(ip.isEmpty() or port.isEmpty()) {
            // should check if its legal address and digits and so on..
            println("error message") // pop up some error message
        } else {
            thread {
                val client = Socket(ip.toString(), port.toInt())
                val output = PrintWriter(client.getOutputStream(), true)
                var connected = true
                while (connected) {
                    output.println(get_rudder())
                    output.flush()
                }
                //output.println("set /controls/flight/rudder 1\r\n")
                //output.flush()
                //Thread.sleep(3000)
                //output.println("set /controls/flight/rudder -1\r\n")
                //output.flush()
//                client.outputStream.write("Hello from the client!".toByteArray())
//                client.close()
            }
        }
    }

    fun client(address: Editable, port: Int) {
        println("6")
        //val client = Socket(address, port)
//        val output = PrintWriter(client.getOutputStream(), true)
//        val input = BufferedReader(InputStreamReader(client.inputStream))
//        var connected: Boolean = true
            println("IM IN CLIENT FUNC")
            thread {
                println("thread")
                Thread.sleep(5000)
                println("end of thread")
            }
            //val client = Socket(address, port)
//                    val output = PrintWriter(client.getOutputStream(), true)
//                    val input = BufferedReader(InputStreamReader(client.inputStream))
//                    var connected: Boolean = true
//                    println("Connected!") }
//            while (connected) {
//                val input = readLine() ?: ""
//                if ("exit" in input) {
//                    connected = false
//                    reader.close()
//                    connection.close()
//                } else {
//                    write(input)
//                }
//            }

//        thread { while(connected) {
//
//        }
//        }

                //println("Connected!")
                //output.println("Hello")
                //println("Client receiving [${input.readLine()}]")
                //client.close()
          //  }
    }

//    class Client(address: String, port: Int) {
//        private val connection: Socket = Socket(address, port)
//        private var connected: Boolean = true
//
//        init {
//            println("Connected to server at $address on port $port")
//        }
//
//        private val reader: Scanner = Scanner(connection.getInputStream())
//        private val writer: OutputStream = connection.getOutputStream()
//
//        fun run() {
//            thread { read() }
//            while (connected) {
//                val input = readLine() ?: ""
//                if ("exit" in input) {
//                    connected = false
//                    reader.close()
//                    connection.close()
//                } else {
//                    write(input)
//                }
//            }
//        }
//
//        private fun write(message: String) {
//            writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
//        }
//
//        private fun read() {
//            while (connected)
//                println(reader.nextLine())
//        }
//    }
}

