package com.bitinovus.gymmobile.presentation.screens.workout

import android.content.Intent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.bitinovus.gymmobile.presentation.components.timerdialog.TimerDialog
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.bitinovus.gymmobile.WorkoutService
import com.bitinovus.gymmobile.presentation.ui.theme.PrimaryBlack25
import com.bitinovus.gymmobile.presentation.ui.theme.PrimaryBlack80
import com.bitinovus.gymmobile.presentation.viewmodels.workout.WorkoutViewmodel


@Composable
fun Timer(
    workoutViewmodel: WorkoutViewmodel,
) {
    val workoutState by workoutViewmodel.workoutState.collectAsState()

    val infiniteTransition = rememberInfiniteTransition()

    val rotation by infiniteTransition.animateFloat(
        initialValue = 15f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2500,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )


    TimerDialog {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val brush = Brush.sweepGradient(
                colors = listOf(
                    PrimaryBlack25,
                    PrimaryBlack80,
                    Color.Blue
                )
            )
            Box(
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .rotate(rotation) // Rotate only the border
                        .border(width = 4.dp, brush = brush, shape = CircleShape)
                        .size(200.dp)
                )
                Box(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = workoutViewmodel.formatTime(workoutState.timeLeft),
                        color = Color.White,
                        fontSize = 35.sp
                    )
                }
            }
            val context = LocalContext.current
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val workoutService = Intent(context, WorkoutService::class.java)
                        .also {
                            it.action = WorkoutService.Actions.STOP.toString()
                        }
                    ContextCompat.startForegroundService(context, workoutService)
                }) {
                Text("Stop", fontSize = 17.sp)
            }
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun TimerPreview() {
//    Timer()
}