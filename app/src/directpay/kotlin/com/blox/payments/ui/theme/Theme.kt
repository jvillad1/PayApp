package com.blox.payments.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = BlueLight40,
    onPrimary = Color.White,
    onPrimaryContainer = BlueLight40,
    secondary = Color.DarkGray,
    onSecondary = Color.White,
    tertiary = Color.Gray,
    background = Blue40,
    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color.White,
    outline = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = BlueLight40,
    onPrimary = Color.White,
    onPrimaryContainer = BlueLight40,
    secondary = Color.White,
    onSecondary = Color.Black,
    tertiary = Color.Gray,
    background = Blue40,
    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color.White,
    outline = Color.White

    /* Other default colors to override
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun PayApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    /* Disable dynamicColor
    dynamicColor: Boolean = true,
    */
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        /* Disable dynamicColor
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        */

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
