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

class WorkoutViewmodel(private val context: Context) : ViewModel() {

    private val _timeLeft = MutableStateFlow(0)
    val timeLeft: StateFlow<Int> = _timeLeft.asStateFlow()

    private val timeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val time = intent?.getIntExtra(WorkoutService.EXTRA_TIME_LEFT, 0)
            if (time != null) {
                _timeLeft.value = time
            }
        }

    }

    init {
        val intentFilter = IntentFilter(WorkoutService.ACTION_UPDATE_TIME)
        context.registerReceiver(timeReceiver, intentFilter, Context.RECEIVER_EXPORTED)
    }

    override fun onCleared() {
        super.onCleared()
        context.unregisterReceiver(timeReceiver)
    }

}