package ru.glebik.ui.kit.theme.values

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


internal val baseTypography = Typography(
    body = TextStyle(
        fontSize = 15.sp,
    ),
    header = TextStyle(
        fontSize = 20.sp,
    ),
)

val Typography.bodyBold: TextStyle
    @Composable
    get() = body.copy(fontWeight = FontWeight.Bold)

val Typography.headerBold: TextStyle
    @Composable
    get() = header.copy(fontWeight = FontWeight.Bold)