package com.bitinovus.gymmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.bitinovus.gymmobile.presentation.screens.workout.Workout
import com.bitinovus.gymmobile.presentation.ui.theme.GymMobileTheme
import com.bitinovus.gymmobile.presentation.viewmodels.GymMobileViewModelFactory
import com.bitinovus.gymmobile.presentation.viewmodels.workout.WorkoutViewmodel

class MainActivity : ComponentActivity() {
    private lateinit var workoutViewmodel: WorkoutViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymMobileTheme {

                workoutViewmodel = ViewModelProvider(
                    this,
                    GymMobileViewModelFactory(applicationContext)
                )[WorkoutViewmodel::class.java]

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        Workout(workoutViewmodel = workoutViewmodel)
                    }
                }
            }
        }
    }
}
