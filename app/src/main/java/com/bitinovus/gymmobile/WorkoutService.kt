package com.bitinovus.gymmobile

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

class WorkoutService : Service() {

    companion object {
        const val CHANNEL_ID = "WorkoutTimerChannel"
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when(intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start(){

        val notification = NotificationCompat
            .Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.outline_exercise)
            .setContentTitle("Start Training")
            .setContentText("Timer")
            .build()

        startForeground(1, notification)
    }

    enum class Actions {
        START,
        STOP
    }

}