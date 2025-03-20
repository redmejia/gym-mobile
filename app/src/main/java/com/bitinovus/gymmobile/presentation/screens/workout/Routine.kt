package com.bitinovus.gymmobile.presentation.screens.workout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.bitinovus.gymmobile.R

@Composable
fun RoutineDescription(
    painterIcon : Int =  R.drawable.outline_exercise,
    description: String =  "description",
    textStyle: TextStyle = LocalTextStyle.current
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            painter = painterResource(id = painterIcon),
            contentDescription = "",
            tint = Color.White
        )
        Text(text = description, style = textStyle)
    }
}