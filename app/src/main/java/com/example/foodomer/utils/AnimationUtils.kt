package com.example.foodomer.utils

import androidx.compose.animation.core.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.rotateWithAnimation(degTo: Float, duration: Int = 1000, easing: Easing = FastOutLinearInEasing): Modifier = composed {
    val rotateState by animateFloatAsState(
        targetValue = degTo,
        animationSpec = tween(
            durationMillis = duration,
            easing = easing
        )
    )

    this.rotate(rotateState)
}

fun Modifier.flipInfinite(duration: Int = 500, delay: Int = 200): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = duration,
                delayMillis = delay,
                easing = FastOutSlowInEasing,
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    this.graphicsLayer {
        rotationY = rotation
    }
}