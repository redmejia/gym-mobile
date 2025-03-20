package com.bitinovus.gymmobile.presentation.screens.workout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bitinovus.gymmobile.R
import com.bitinovus.gymmobile.presentation.components.actionbuttons.category.Category
import com.bitinovus.gymmobile.presentation.components.actioncard.ActionCard
import com.bitinovus.gymmobile.presentation.components.fadingedge.Fading
import com.bitinovus.gymmobile.presentation.ui.theme.PrimaryBlack25
import com.bitinovus.gymmobile.presentation.ui.theme.PrimaryBlack80

@Composable
fun Workout() {
    Box(
        modifier = Modifier
            .fillMaxHeight(0.35f),
        contentAlignment = Alignment.TopCenter
    ) {
        Fading(
            modifier = Modifier
                .fillMaxWidth(),
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent, // Top fade
                    Color.Transparent,
                    PrimaryBlack80.copy(alpha = 0.45f),
                    PrimaryBlack25.copy(alpha = 1f)  // Bottom fade
                ),
                startY = 0f,
                endY = LocalConfiguration.current.screenHeightDp.toFloat()
            )
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.mygym),
                contentScale = ContentScale.Crop,
                contentDescription = "header_image"
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PrimaryBlack25)
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Column {
                Text(
                    text = "Exercise Routine",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {
                    Category(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = PrimaryBlack80,
                                shape = RoundedCornerShape(size = 10.dp)
                            ),
                        titleStyle = TextStyle(color = Color.White),
                        textFooter = "5 min",
                        textFooterStyle = TextStyle(color = Color.White),
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.outline_heart),
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    )
                    Category(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = PrimaryBlack80,
                                shape = RoundedCornerShape(size = 10.dp)
                            ),
                        title = "Exercise",
                        titleStyle = TextStyle(color = Color.White),
                        textFooter = "3",
                        textFooterStyle = TextStyle(color = Color.White),
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.outline_exercise),
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    )
                    Category(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                color = PrimaryBlack80,
                                shape = RoundedCornerShape(size = 10.dp)
                            ),
                        title = "Rating",
                        titleStyle = TextStyle(color = Color.White),
                        textFooter = "0.0",
                        textFooterStyle = TextStyle(color = Color.White),
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.outline_star),
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
                val exerciseList = listOf(
                    Actions(
                        image = R.drawable.squats,
                        title = "Squats",
                        routineSet = "3x10",
                        duration = "12min"
                    ),
                    Actions(
                        image = R.drawable.push_ups,
                        title = "Push ups",
                        routineSet = "3x12",
                        duration = "15min"
                    ),
                    Actions(
                        image = R.drawable.lunges,
                        title = "Lunges",
                        routineSet = "3x13",
                        duration = "16min"
                    )
                )
                exerciseList.forEach { action ->
                    ActionCard(
                        modifier = Modifier.padding(top = 4.dp, bottom = 20.dp),
                        image = action.image,
                        contentBody = {
                            Column {
                                Text(
                                    action.title,
                                    fontSize = 20.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium
                                )
                                Spacer(Modifier.height(15.dp))
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    RoutineDescription(
                                        painterIcon = R.drawable.outline_clock_analog,
                                        description = action.duration,
                                        textStyle = TextStyle(
                                            color = Color.White,
                                        )
                                    )
                                    RoutineDescription(
                                        description = action.routineSet,
                                        textStyle = TextStyle(
                                            color = Color.White,
                                        )
                                    )
                                }

                            }
                        }
                    ) {
                        Button(
                            modifier = Modifier
                                .padding(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryBlack25
                            ),
                            onClick = {}) { Text("Start") }
                    }
                }
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {  }) {
                    Text("Routine Completed", fontSize = 17.sp)
                }
            }
        }
    }
}

data class Actions(
    val image: Int,
    val title: String,
    val routineSet: String,
    val duration: String,
)