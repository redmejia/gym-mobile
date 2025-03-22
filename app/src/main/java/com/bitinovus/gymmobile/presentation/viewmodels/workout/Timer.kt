package com.bitinovus.gymmobile.presentation.viewmodels.workout

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.bitinovus.gymmobile.presentation.components.timerdialog.TimerDialog
import androidx.compose.runtime.getValue


@Composable
fun Timer(
    workoutViewmodel: WorkoutViewmodel,
) {
    val workoutState by workoutViewmodel.workoutState.collectAsState()

    if (workoutState.timeLeft != 0) {

        TimerDialog {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Time Left : ${workoutViewmodel.formatTime(workoutState.timeLeft)}",
                    color = Color.White
                )
            }
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun TimerPreview() {
//    Timer()
}