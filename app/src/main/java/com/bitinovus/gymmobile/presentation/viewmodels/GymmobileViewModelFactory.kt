package com.bitinovus.gymmobile.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitinovus.gymmobile.presentation.viewmodels.workout.WorkoutViewmodel

class GymMobileViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutViewmodel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return WorkoutViewmodel(context) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }

}