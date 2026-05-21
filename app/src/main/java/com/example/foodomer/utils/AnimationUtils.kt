package com.example.foodomer.utils

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp

fun Modifier.rotateWithAnimation(degTo: Float, duration: Int = 1000, easing: Easing = EaseInOutCubic): Modifier = composed {
    val rotateState by animateFloatAsState(
        targetValue = degTo,
        animationSpec = tween(
            durationMillis = duration,
            easing = easing
        ),
        label = "rotate"
    )

    this.rotate(rotateState)
}

fun Modifier.flipInfinite(duration: Int = 500, delay: Int = 200): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "flip")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = duration,
                delayMillis = delay,
                easing = EaseInOutCubic,
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "flipRotation"
    )

    this.graphicsLayer {
        rotationY = rotation
    }
}

fun Modifier.blink(duration: Int = 500, delay: Int = 0): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "blink")
    val opacity by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = duration,
                delayMillis = delay,
                easing = EaseInOutSine,
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "blinkOpacity"
    )

    this.graphicsLayer {
        alpha = opacity
    }
}

fun Modifier.slideByOffsetX(offsetXTo: Dp, duration: Int = 500, easing: Easing = EaseInOutCubic) = composed {
    val offsetX by animateDpAsState(
        targetValue = offsetXTo,
        animationSpec = tween(
            durationMillis = duration,
            easing = easing
        ),
        label = "slideX"
    )
    this.offset(x = offsetX)
}

fun Modifier.bobbing(
    amplitude: Float = 12f,
    duration: Int = 2000,
    delay: Int = 0
) = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "bobbing")
    val offsetY by infiniteTransition.animateFloat(
        initialValue = -amplitude,
        targetValue = amplitude,
        animationSpec = infiniteRepeatable(
            animation = tween(duration, delayMillis = delay, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bobbingOffsetY"
    )
    
    this.graphicsLayer {
        translationY = offsetY
    }
}

fun Modifier.wobbleAndPulse(enabled: Boolean): Modifier = composed {
    if (!enabled) return@composed this

    val infiniteTransition = rememberInfiniteTransition(label = "wobble")
    
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.96f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(300, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "wobbleScale"
    )
    
    val rotation by infiniteTransition.animateFloat(
        initialValue = -3f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            animation = tween(200, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "wobbleRotation"
    )

    this.graphicsLayer {
        scaleX = scale
        scaleY = scale
        rotationZ = rotation
    }
}

fun Modifier.spin(enabled: Boolean): Modifier = composed {
    if (!enabled) return@composed this

    val infiniteTransition = rememberInfiniteTransition(label = "spin")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "spinRotation"
    )

    this.rotate(rotation)
}

fun Modifier.scaleInOnShow(
    delay: Int = 0,
    duration: Int = 600,
    easing: Easing = EaseOutBack
) = composed {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        isVisible = true
    }
    
    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(
            durationMillis = duration,
            delayMillis = delay,
            easing = easing
        ),
        label = "scaleInOnShow"
    )
    
    this.graphicsLayer {
        scaleX = scale
        scaleY = scale
    }
}