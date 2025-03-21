package com.bitinovus.gymmobile.presentation.components.timerdialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bitinovus.gymmobile.presentation.ui.theme.PrimaryBlack25
import com.bitinovus.gymmobile.presentation.ui.theme.PrimaryBlack95

@Composable
fun TimerDialog(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier
            .background(color = PrimaryBlack95)
            .fillMaxSize()
    ) {
        Card(
            modifier = modifier
                .padding(15.dp)
                .fillMaxSize(),
            colors = CardDefaults.cardColors(
                containerColor = PrimaryBlack25
            )
        ) {
            content()
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun TimerDialogPreview() {
    TimerDialog()
}