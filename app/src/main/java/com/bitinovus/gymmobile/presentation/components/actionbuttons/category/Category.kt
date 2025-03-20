package com.bitinovus.gymmobile.presentation.components.actionbuttons.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Category(
    modifier: Modifier = Modifier,
    title: String = "Cardio",
    titleStyle: TextStyle = LocalTextStyle.current,
    textFooter: String = "5 min",
    textFooterStyle: TextStyle = LocalTextStyle.current,
    trailingIcon: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier
            .padding(vertical = 10.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = title, style = titleStyle
                )
                trailingIcon()
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                text = textFooter,
                style = textFooterStyle,
                textAlign = TextAlign.End
            )

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategoryPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Category(
            titleStyle = TextStyle(color = Color.White),
            textFooter = "5 min",
            textFooterStyle = TextStyle(color = Color.White),
            trailingIcon = {
                Icon(Icons.Default.CheckCircle, contentDescription = "", tint = Color.White)
            }
        )
    }
}