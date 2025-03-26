package ru.glebik.ui.kit.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import ru.glebik.ui.kit.theme.values.Colors
import ru.glebik.ui.kit.theme.values.CornerShape
import ru.glebik.ui.kit.theme.values.Padding
import ru.glebik.ui.kit.theme.values.Typography
import ru.glebik.ui.kit.theme.values.baseCornerShape
import ru.glebik.ui.kit.theme.values.baseDarkPalette
import ru.glebik.ui.kit.theme.values.baseLightPalette
import ru.glebik.ui.kit.theme.values.basePadding
import ru.glebik.ui.kit.theme.values.baseTypography


@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors: Colors = when {
        darkTheme -> baseDarkPalette
        else -> baseLightPalette
    }

    val typography: Typography = baseTypography

    val padding: Padding = basePadding

    val cornerShape: CornerShape = baseCornerShape

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {

            (view.context as? Activity)?.window?.let { window ->
                window.statusBarColor = colors.primary.toArgb()
                window.navigationBarColor = colors.primary.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                    !darkTheme
            }
        }
    }

    CompositionLocalProvider(
        LocalCustomColors provides colors,
        LocalCustomTypography provides typography,
        LocalCustomPadding provides padding,
        LocalCustomCornerShape provides cornerShape,
        content = content
    )
}