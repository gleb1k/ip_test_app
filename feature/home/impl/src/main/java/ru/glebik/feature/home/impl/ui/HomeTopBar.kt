package ru.glebik.feature.home.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.glebik.feature.home.impl.R
import ru.glebik.ui.kit.theme.AppTheme

@Composable
internal fun HomeTopBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .statusBarsPadding()
            .height(56.dp)
            .fillMaxWidth()
            .background(AppTheme.colors.primary)
    ) {
        Text(text = stringResource(R.string.home_header), style = AppTheme.visifyTypography.header)
    }
}