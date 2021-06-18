package com.example.flightcontrolapp.model

import java.io.PrintWriter
import java.net.Socket
import java.util.concurrent.BlockingDeque
import java.util.concurrent.LinkedBlockingDeque

class FGPlayer(ip: String?, port: Int) {
    var rudder = 0.0
    var dispatchQueue: BlockingDeque<Runnable> = LinkedBlockingDeque()
    lateinit var socket: Socket
    lateinit var output: PrintWriter
    @Throws(InterruptedException::class)
    fun set_rudder(value: Int) {
        rudder = value * 0.01
        dispatchQueue.put(Runnable {
            output.print("set /controls/flight/rudder $rudder\r\n")
            output.flush()
        })
    }

    init {
        Thread {
            socket = Socket(ip, port)
            output = PrintWriter(socket.getOutputStream(), true)
            while (true) {
                try {
                    dispatchQueue.take().run()
                } catch (e: InterruptedException) {
                }
            }
        }.start()
    }
}