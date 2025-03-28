package ru.glebik.feature.home.impl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.PersistentList
import ru.glebik.core.arch.util.ViewProperty
import ru.glebik.feature.home.impl.model.ProductUiModel
import ru.glebik.feature.home.impl.ui.HomeSearchField
import ru.glebik.feature.home.impl.ui.HomeTopBar
import ru.glebik.feature.home.impl.ui.ProductItem
import ru.glebik.feature.home.impl.ui.dialog.ProductAmountDialog
import ru.glebik.feature.home.impl.ui.dialog.ProductRemoveDialog
import ru.glebik.feature.home.impl.vm.HomeViewModel
import ru.glebik.feature.home.impl.vm.state.HomeEffect
import ru.glebik.feature.home.impl.vm.state.HomeIntent
import ru.glebik.feature.home.impl.vm.state.HomeState
import ru.glebik.ui.kit.theme.AppTheme
import ru.glebik.ui.kit.util.CollectEffect

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    CollectEffect(viewModel.effect) {
        when (it) {
            is HomeEffect.ShowSnackBar -> {

            }
        }
    }

    HomeView(state, viewModel::handleIntent)

    ProductAmountDialog(
        onDismissRequest = { viewModel.handleIntent(HomeIntent.HideChangeAmountDialog) },
        onConfirmClick = { viewModel.handleIntent(HomeIntent.OnConfirmChangeAmountDialogClick) },
        onIncreaseClick = { viewModel.handleIntent(HomeIntent.OnIncreaseDialogClick) },
        onDecreaseClick = { viewModel.handleIntent(HomeIntent.OnDecreaseDialogClick) },
        state = state.productAmountState
    )

    ProductRemoveDialog(
        state = state.productRemoveState,
        onDismissRequest = { viewModel.handleIntent(HomeIntent.HideRemoveProductDialog) },
        onConfirmClick = { viewModel.handleIntent(HomeIntent.OnConfirmRemoveProductDialogClick) }
    )
}

@Composable
private fun HomeView(
    state: HomeState,
    handleIntent: (HomeIntent) -> Unit,
) {

    Scaffold(
        containerColor = AppTheme.colors.background,
        topBar = { HomeTopBar() },
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(
                top = AppTheme.padding.p16dp,
                start = AppTheme.padding.p12dp,
                end = AppTheme.padding.p12dp,
                bottom = AppTheme.padding.p16dp,
            ),
            verticalArrangement = Arrangement.spacedBy(AppTheme.padding.p16dp)
        ) {

            item {
                HomeSearchField(
                    query = state.searchQuery,
                    onQueryChange = {
                        handleIntent(HomeIntent.OnSearchQueryChange(it))
                    },
                    onSearchClick = {
                        handleIntent(HomeIntent.OnSearchClick)
                    }
                )
            }

            when (val property = state.products) {
                is ViewProperty.Content -> homeProductsItems(property.content, handleIntent)
                is ViewProperty.Error -> {
                    item {
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) { Text(stringResource(R.string.home_error)) }
                    }
                }

                ViewProperty.Loading -> {
                    item {
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}


private fun LazyListScope.homeProductsItems(
    products: PersistentList<ProductUiModel>,
    handleIntent: (HomeIntent) -> Unit,
) {
    items(products, key = { it.id }) { product ->
        ProductItem(
            product = product,
            onRemoveClick = { handleIntent(HomeIntent.OnRemoveProductClick(it)) },
            onEditClick = { handleIntent(HomeIntent.OnEditProductClick(it)) },
        )
    }
}
