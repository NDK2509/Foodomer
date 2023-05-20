package com.example.foodomer.utils

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp

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

fun Modifier.blink(duration: Int = 500, delay: Int = 0): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition()
    val opacity by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = duration,
                delayMillis = delay,
                easing = FastOutSlowInEasing,
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    this.graphicsLayer {
        alpha = opacity
    }
}

fun Modifier.slideByOffsetX(offsetXTo: Dp, duration: Int = 500, easing: Easing = FastOutLinearInEasing) = composed{
    val offsetX by animateDpAsState(
        targetValue = offsetXTo,
        animationSpec = tween(
            durationMillis = duration,
            easing = easing
        )
    )
    this.offset(x = offsetX)
}