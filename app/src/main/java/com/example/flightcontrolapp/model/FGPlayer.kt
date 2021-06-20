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

    /*
     model constructor. when called, according the active object design pattern,
     model will initialize queue of tasks. the constructor create new thread that connect
     to the flightgear server with the ip&port given from user.
     the tasks send to the server on by one in a look until the user will press
     the disconnect button.
     */
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

    /*
     when the rudder seekbar value change, this function will add new
     task to the queue. the task function is to send the new value wrap by
     specific string to the server.
     */
    @Throws(InterruptedException::class)
    fun set_rudder(value: Int) {
        rudder = value * 0.01
        dispatchQueue.put(Runnable {
            output.print("set /controls/flight/rudder $rudder\r\n")
            output.flush()
        })
    }

    /*
     when the throttle seekbar value change, this function will add new
     task to the queue. the task function is to send the new value wrap by
     specific string to the server.
     */
    @Throws(InterruptedException::class)
    fun set_throttle(value: Int) {
        throttle = value * 0.01
        dispatchQueue.put(Runnable {
            output.print("set /controls/engines/current-engine/throttle $throttle\r\n")
            output.flush()
        })
    }

    /*
     when the aileron (x axle in the joystick) value change,
     this function will add new task to the queue.
     the task function is to send the new value wrap by specific string to the server.
     */
    @Throws(InterruptedException::class)
    fun setAileron(value: Float) {
        aileron = value * 0.01
        dispatchQueue.put(Runnable {
            output.print("set /controls/flight/aileron $aileron\r\n")
            output.flush()
        })
    }

    /*
     when the elevator (y axle in the joystick) value change,
     this function will add new task to the queue.
     the task function is to send the new value wrap by specific string to the server.
    */
    @Throws(InterruptedException::class)
    fun setElevator(value: Float) {
        elevator = value * 0.01
        dispatchQueue.put(Runnable {
            output.print("set /controls/flight/elevator $elevator\r\n")
            output.flush()
        })
    }

    /*
     when the user press the disconnect button, this function will add
     new task to the queue that change the value of the boolean var shouldStop
     to true. the idea is to let the other tasks in the line to finish and then to close
     the connection with the server.
     */
    @Throws(InterruptedException::class)
    fun close() {
        dispatchQueue.put(Runnable {
            shouldStop = true
        })
    }
}