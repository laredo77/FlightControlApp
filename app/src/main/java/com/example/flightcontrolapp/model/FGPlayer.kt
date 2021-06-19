package com.example.flightcontrolapp.model

import java.io.PrintWriter
import java.net.Socket
import java.util.concurrent.BlockingDeque
import java.util.concurrent.LinkedBlockingDeque
import kotlin.properties.Delegates

class FGPlayer(ip: String?, port: Int) {
    private var rudder = 0.0
    private var throttle = 0.0
    private var aileron = 0.0
    private var elevator = 0.0
    private var shouldStop by Delegates.notNull<Boolean>()
    var dispatchQueue: BlockingDeque<Runnable> = LinkedBlockingDeque()
    lateinit var socket: Socket
    lateinit var output: PrintWriter

    init {
        shouldStop = false
        Thread {
            socket = Socket(ip, port)
            output = PrintWriter(socket.getOutputStream(), true)
            while (!shouldStop) {
                try {
                    dispatchQueue.take().run()
                } catch (e: InterruptedException) {
                }
            }
        }.start()
    }

    @Throws(InterruptedException::class)
    fun set_rudder(value: Int) {
        rudder = value * 0.01
        dispatchQueue.put(Runnable {
            output.print("set /controls/flight/rudder $rudder\r\n")
            output.flush()
        })
    }

    @Throws(InterruptedException::class)
    fun set_throttle(value: Int) {
        throttle = value * 0.01
        dispatchQueue.put(Runnable {
            output.print("set /controls/engines/current-engine/throttle $throttle\r\n")
            output.flush()
        })
    }

    @Throws(InterruptedException::class)
    fun setAileron(value: Float) {
        aileron = value * 0.01
        dispatchQueue.put(Runnable {
            output.print("set /controls/flight/aileron $aileron\r\n")
            output.flush()
        })
    }

    @Throws(InterruptedException::class)
    fun setElevator(value: Float) {
        elevator = value * 0.01
        dispatchQueue.put(Runnable {
            output.print("set /controls/flight/elevator $elevator\r\n")
            output.flush()
        })
    }

    @Throws(InterruptedException::class)
    fun close() {
        dispatchQueue.put(Runnable {
            shouldStop = true
        })
    }
}