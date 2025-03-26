package ru.glebik.ui.kit.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import ru.glebik.ui.kit.theme.values.Colors
import ru.glebik.ui.kit.theme.values.CornerShape
import ru.glebik.ui.kit.theme.values.Padding
import ru.glebik.ui.kit.theme.values.Typography

object AppTheme {

    val colors: Colors
        @Composable
        get() = LocalCustomColors.current

    val visifyTypography: Typography
        @Composable
        get() = LocalCustomTypography.current

    val padding: Padding
        @Composable
        get() = LocalCustomPadding.current

    val cornerShape: CornerShape
        @Composable
        get() = LocalCustomCornerShape.current
}

val LocalCustomColors = staticCompositionLocalOf<Colors> {
    error("No colors provided")
}

val LocalCustomTypography =
    staticCompositionLocalOf<Typography> {
        error("No typography provided")
    }

val LocalCustomPadding = staticCompositionLocalOf<Padding> {
    error("No padding provided")
}

val LocalCustomCornerShape = staticCompositionLocalOf<CornerShape> {
    error("No corner shape provided")
}
