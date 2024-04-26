package com.example.weatherapp.common.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = DarkGrey40,
    onPrimary = white,
    primaryContainer = DarkGrey70,
    onPrimaryContainer = DarkGrey10,
    inversePrimary = DarkGrey70,
    secondary = DarkGrey60,
    onSecondary = white,
    secondaryContainer = Grey90,
    onSecondaryContainer = Grey10,
    error = Red40,
    onError = white,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = DarkGrey30,
    onBackground = Grey80,
    surface = DarkGrey70,
    onSurface = DarkGrey30,
    inverseSurface = Grey20,
    inverseOnSurface = Grey90,
    surfaceVariant = DarkGrey70,
    onSurfaceVariant = DarkGrey30,
    outline = DarkGrey40
)

private val LightColorScheme = lightColorScheme(
    primary = yellow40,
    onPrimary = white,
    primaryContainer = yellow90,
    onPrimaryContainer = yellow80,
    inversePrimary = yellow80,
    secondary = DarkYellow40,
    onSecondary = white,
    secondaryContainer = DarkYellow90,
    onSecondaryContainer = DarkYellow10,
    error = Red40,
    onError = white,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = YellowGrey80,
    onBackground = yellow20,
    surface = YellowGrey90,
    onSurface = YellowGrey30,
    inverseSurface = Grey20,
    inverseOnSurface = Grey90,
    surfaceVariant = YellowGrey90,
    onSurfaceVariant = YellowGrey30,
    outline = YellowGrey50
)
@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}