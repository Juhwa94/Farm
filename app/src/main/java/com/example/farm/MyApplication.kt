package com.example.farm

import android.app.Application
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.util.*


class MyApplication: Application() {
    interface SocketListener {
        fun onReceiveData()
        fun onSocketError()
        fun onConnect()
    }
    companion object{
        lateinit var socket: Socket

        val writeQueue: Queue<String> = LinkedList()
        val readQueue: Queue<String> = LinkedList()
        val data = hashMapOf<String, String>()
        val historyData = arrayListOf<String>()
        var socketListener : SocketListener? = null
        private var outStream: OutputStream? = null
        val socketThread = Thread {
            try {
                socket = Socket()
                socket.connect(InetSocketAddress("192.168.4.1", 12345))
            } catch (e: Exception) {
                e.printStackTrace()
                socketListener?.onSocketError()
                return@Thread
            }

            outStream = socket.getOutputStream()
            val inputStream = Scanner(socket.getInputStream())
            socketListener?.onConnect()

            while (inputStream.hasNextLine()) {
                readQueue.add(inputStream.nextLine())
                socketListener?.onReceiveData()
            }
        }

        val writeThread = Thread {
            do {
                var data: String? = writeQueue.peek()
                data?.let { s ->
                    val byteArray = "${s}\r\n".toByteArray();
                    outStream?.let {
                        try {
                            it.write(byteArray, 0, byteArray.size)
                        } catch (e: java.lang.Exception) {
                            socketListener?.onSocketError()
                            data = "exit"
                        }
                        writeQueue.poll()
                    }
                }
            } while (data != "exit")
        }
    }
}