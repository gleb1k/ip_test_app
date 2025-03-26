package ru.glebik.ui.kit.theme.values

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class Colors(
    val primary: Color = Color(0xFF2E7D32),
    val secondary: Color = Color(0xFF6A1B9A),
    val surface: Color = Color(0xFFFFFFFF),
    val background: Color = Color(0xFFF5F5F5),

    val error: Color = Color(0xFFDB3223),
)

data class Typography(
    val body: TextStyle,
    val chips: TextStyle,
    val header: TextStyle,
)

data class Padding(
    val p4: Dp,
    val p8: Dp,
    val p12: Dp,
    val p16: Dp,
    val p24: Dp,
    val p32: Dp,
)

data class CornerShape(
    val rounded: CornerBasedShape,
)