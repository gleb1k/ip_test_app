package ru.glebik.ui.kit.theme.values

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class Colors(
    val primary: Color = Color(0xFFD1DCFB),
    val secondary: Color = Color(0xFF6503e8),
    val surface: Color = Color(0xFFFFFFFF),
    val background: Color = Color(0xFFF5F5F5),

    val error: Color = Color(0xFFd03e02),
    val black: Color = Color(0xFF000000),
)

data class Typography(
    val body: TextStyle,
    val header: TextStyle,
)

data class Padding(
    val p4dp: Dp,
    val p8dp: Dp,
    val p12dp: Dp,
    val p16dp: Dp,
    val p24dp: Dp,
    val p32dp: Dp,
)

data class CornerShape(
    val rounded: CornerBasedShape,
    val roundedBig: CornerBasedShape,
)