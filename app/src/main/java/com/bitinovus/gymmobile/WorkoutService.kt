package com.bitinovus.gymmobile

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WorkoutService : Service() {

    companion object {
        const val CHANNEL_ID = "WorkoutTimerChannel"
        const val NOTIFICATION_ID = 1
        const val EXTRA_DURATION = "EXTRA_DURATION"
        const val ACTION_UPDATE_TIME = "com.bitinovus.gymmobile.UPDATE_TIME"
        const val EXTRA_TIME_LEFT = "EXTRA_TIME_LEFT"
        const val TIME_STOP = "TIME_STOP"
    }


    private var job: Job? = null
    private var notificationManager: NotificationManager? = null
    private var remainingTime = 0

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(NotificationManager::class.java)
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {
            Actions.START.toString() -> {
                val duration = intent.getIntExtra(EXTRA_DURATION, 10)
                startTimer(duration)
            }

            Actions.STOP.toString() -> {
                stopSelf()
                sendTimeStop(Actions.STOP.toString())
            }
        }

        return START_STICKY

    }

    private fun sendTimeUpdate(timeLeft: Int) {

        val intent = Intent(ACTION_UPDATE_TIME).apply {
            putExtra(EXTRA_TIME_LEFT, timeLeft)
        }

        sendBroadcast(intent)
    }

    private fun sendTimeStop(timeActionStop: String) {

        val intent = Intent(ACTION_UPDATE_TIME).apply {
            putExtra(TIME_STOP, timeActionStop)
        }

        sendBroadcast(intent)

    }

    private fun startTimer(duration: Int) {
        remainingTime = duration * 60

        startForeground(NOTIFICATION_ID, createNotification(remainingTime))

        job = CoroutineScope(Dispatchers.Main).launch {
            while (remainingTime > 0) {
                delay(1000) // Wait for 1 second
                remainingTime--
                sendTimeUpdate(remainingTime)
                updateNotification(remainingTime)
            }
            stopSelf() // Stop service when timer ends
        }
    }

    private fun createNotification(remainingTime: Int): Notification {
        val stopIntent = Intent(this, WorkoutService::class.java).apply {
            action = Actions.STOP.toString()
        }
        val stopPendingIntent = PendingIntent.getService(
            this, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.outline_exercise)
            .setContentTitle("Workout Timer")
            .setContentText("Time left: ${formatTime(remainingTime)}")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .addAction(R.drawable.filled_cancel, "Stop", stopPendingIntent) // Stop button
            .build()
    }


    private fun updateNotification(remainingTime: Int) {
        notificationManager?.notify(NOTIFICATION_ID, createNotification(remainingTime))
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val sec = seconds % 60
        return String.format("%02d:%02d", minutes, sec)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Workout Timer",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Shows remaining workout time"
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    enum class Actions {
        START,
        STOP
    }

}