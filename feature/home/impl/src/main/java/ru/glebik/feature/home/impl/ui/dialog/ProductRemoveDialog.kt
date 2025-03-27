package ru.glebik.feature.home.impl.ui.dialog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.glebik.feature.home.impl.R
import ru.glebik.feature.home.impl.model.dialog.ProductRemoveState
import ru.glebik.ui.kit.theme.AppTheme
import ru.glebik.ui.kit.theme.values.bodyBold
import ru.glebik.ui.kit.theme.values.headerBold
import ru.glebik.ui.kit.util.clickableNoInteraction

@Composable
fun ProductRemoveDialog(
    state: ProductRemoveState,
    onDismissRequest: () -> Unit,
    onConfirmClick: (productId: Int) -> Unit,
) {
    if (state.isShow) {
        AlertDialog(
            icon = {
                Icon(Icons.Default.Warning, contentDescription = "Warning")
            },
            title = {
                Text(
                    text = stringResource(R.string.home_dialog_delete_product_title),
                    style = AppTheme.typography.headerBold,
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.home_dialog_delete_product_desc),
                    style = AppTheme.typography.body,
                )
            },
            onDismissRequest = {},
            confirmButton = {
                Text(
                    text = stringResource(R.string.home_yes),
                    style = AppTheme.typography.bodyBold,
                    modifier = Modifier.clickableNoInteraction {
                        onConfirmClick(state.productId)
                    })
            },
            dismissButton = {
                Text(
                    text = stringResource(R.string.home_no),
                    style = AppTheme.typography.bodyBold,
                    modifier = Modifier.clickableNoInteraction {
                        onDismissRequest()
                    })
            }
        )
    }
}