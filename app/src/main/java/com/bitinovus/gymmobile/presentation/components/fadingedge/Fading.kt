package com.bitinovus.gymmobile.presentation.components.fadingedge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

@Composable
fun Fading(
    modifier: Modifier = Modifier,
    brush: Brush,
    content: @Composable () -> Unit = {},
) {
    Box(modifier = modifier) {
        content()
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = brush,
                )
        )
    }
}

