package ru.glebik.feature.home.impl.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.glebik.feature.home.impl.R
import ru.glebik.feature.home.impl.model.ProductUiModel
import ru.glebik.ui.kit.theme.AppTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun ProductItem(
    modifier: Modifier = Modifier,
    product: ProductUiModel,
    onRemoveClick: (productId: Int) -> Unit,
    onEditClick: (productId: Int) -> Unit,
) {

    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(
            containerColor = AppTheme.colors.surface
        ),
        shape = AppTheme.cornerShape.rounded,
        elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.padding.p4dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = AppTheme.padding.p12dp,
                    start = AppTheme.padding.p12dp,
                    end = AppTheme.padding.p12dp,
                    bottom = AppTheme.padding.p12dp,
                )
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = product.name, style = AppTheme.visifyTypography.header)

                Row {
                    IconButton(
                        onClick = { onEditClick(product.id) },
                        content = {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "Edit",
                                tint = AppTheme.colors.secondary,
                            )
                        }
                    )

                    IconButton(
                        onClick = { onRemoveClick(product.id) },
                        content = {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Remove",
                                tint = AppTheme.colors.error
                            )
                        }
                    )
                }
            }

            Spacer(Modifier.width(AppTheme.padding.p8dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.padding.p8dp),
                verticalArrangement = Arrangement.spacedBy(AppTheme.padding.p8dp),
            ) {
                product.tags.forEach { chip ->
                    AssistChip(
                        onClick = { },
                        label = { Text(text = chip.name) },
                    )
                }
            }

            Spacer(Modifier.width(AppTheme.padding.p8dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                ) {
                DescriptionItem(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.home_product_item_in_stock),
                    content = product.amount.toString(),
                )

                DescriptionItem(
                    modifier = Modifier.weight(1f),
                    title = stringResource(R.string.home_product_item_date_added),
                    content = product.time,
                )
            }
        }
    }
}

@Composable
private fun DescriptionItem(
    modifier: Modifier = Modifier,
    title: String,
    content: String
) {
    Column(
        modifier
    ) {
        Text(text = title)
        Text(text = content)
    }
}
