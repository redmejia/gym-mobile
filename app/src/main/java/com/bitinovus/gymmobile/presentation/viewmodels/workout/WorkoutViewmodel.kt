package com.bitinovus.gymmobile.presentation.viewmodels.workout

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.ViewModel
import com.bitinovus.gymmobile.WorkoutService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class WorkoutViewmodel(private val context: Context) : ViewModel() {

    private val _workoutState = MutableStateFlow(WorkoutState())
    val workoutState: StateFlow<WorkoutState> = _workoutState.asStateFlow()

    private val timeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            val time = intent?.getIntExtra(WorkoutService.EXTRA_TIME_LEFT, 0)

            if (time != null) {
                _workoutState.update { currentState ->
                    currentState.copy(timeLeft = time)
                }
            }
        }
    }

    init {
        val intentFilter = IntentFilter(WorkoutService.ACTION_UPDATE_TIME)
        context.registerReceiver(timeReceiver, intentFilter, Context.RECEIVER_EXPORTED)
    }

    fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val sec = seconds % 60
        return String.format("%02d:%02d", minutes, sec)
    }

    override fun onCleared() {
        super.onCleared()
        context.unregisterReceiver(timeReceiver)
    }

}