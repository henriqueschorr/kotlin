package com.example.kotlintraining

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class NoteGetTogetherHelper(val context: Context, val lifecycle: Lifecycle) : LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    val tag = this::class.simpleName
    var currentLat = 0.0
    var currentLon = 0.0

    val locationManager = PseudoLocationManager(context) { lat, lon ->
        currentLat = lat
        currentLon = lon
        Log.d("Location", "Location Callback Lat:$lat Lon:$lon")
    }

    val messagingManager = PseudoMessagingManager(context)
    var messagingConnection: PseudoMessagingConnection? = null

    fun sendMessage(note: NoteInfo) {
        val getTogetherMessage = "$currentLat|$currentLon|${note.title}|${note.course?.title}"
        messagingConnection?.send(getTogetherMessage)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startHandler() {
        locationManager.start()
        messagingManager.connect { connection ->
            Log.d(tag, "Connection Callback - LifeCycle State:${lifecycle.currentState}")
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                Log.d(tag, "Connected")
                messagingConnection = connection
            } else {
                Log.d(tag, "Disconnected")
                messagingConnection?.disconnect()
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopHandler() {
        locationManager.stop()
        Log.d(tag, "Disconnected")
        messagingConnection?.disconnect()
    }

}