package ru.glebik.feature.home.impl.ui.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.glebik.feature.home.impl.R
import ru.glebik.feature.home.impl.model.dialog.ProductAmountState
import ru.glebik.ui.kit.theme.AppTheme
import ru.glebik.ui.kit.theme.values.bodyBold
import ru.glebik.ui.kit.util.clickableNoInteraction

@Composable
fun ProductAmountDialog(
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit,
    onIncreaseClick: () -> Unit,
    onDecreaseClick: () -> Unit,
    state: ProductAmountState,
) {
    if (state.isShow) {
        Dialog(onDismissRequest = onDismissRequest) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = AppTheme.cornerShape.roundedBig,
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(AppTheme.padding.p24dp)
                ) {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = stringResource(id = R.string.home_dialog_amount_count),
                        style = AppTheme.typography.header,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp)
                    )

                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onDecreaseClick,
                            content = {
                                Icon(
                                    painterResource(R.drawable.ic_remove_circle_24),
                                    contentDescription = "Minus",
                                    tint = AppTheme.colors.secondary,
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                        )

                        Text(
                            text = state.amount.toString(),
                            style = AppTheme.typography.header,
                            modifier = Modifier.padding(horizontal = AppTheme.padding.p12dp)
                        )

                        IconButton(
                            onClick = onIncreaseClick,
                            content = {
                                Icon(
                                    painterResource(R.drawable.ic_add_circle_24),
                                    contentDescription = "Plus",
                                    tint = AppTheme.colors.secondary,
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(AppTheme.padding.p16dp))

                    Row(
                        modifier = Modifier
                            .align(Alignment.End)
                    ) {

                        Text(
                            text = stringResource(R.string.home_dialog_cancel),
                            style = AppTheme.typography.bodyBold,
                            modifier = Modifier.clickableNoInteraction {
                                onDismissRequest()
                            }
                        )

                        Spacer(modifier = Modifier.width(AppTheme.padding.p12dp))

                        Text(
                            text = stringResource(R.string.home_dialog_confirm),
                            style = AppTheme.typography.bodyBold,
                            modifier = Modifier.clickableNoInteraction {
                                onConfirmClick()
                            }
                        )
                    }
                }
            }
        }
    }
}