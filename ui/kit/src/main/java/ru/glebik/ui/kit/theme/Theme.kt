package ru.glebik.ui.kit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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

    CompositionLocalProvider(
        LocalCustomColors provides colors,
        LocalCustomTypography provides typography,
        LocalCustomPadding provides padding,
        LocalCustomCornerShape provides cornerShape,
        content = content
    )
}