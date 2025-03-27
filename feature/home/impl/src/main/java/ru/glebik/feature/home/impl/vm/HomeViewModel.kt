package ru.glebik.feature.home.impl.vm

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.update
import ru.glebik.core.arch.MviViewModel
import ru.glebik.core.arch.util.content
import ru.glebik.core.arch.util.failure
import ru.glebik.core.arch.util.loading
import ru.glebik.feature.home.api.model.ProductModel
import ru.glebik.feature.home.impl.mapper.ui.ProductUiMapper
import ru.glebik.feature.home.impl.vm.state.HomeEffect
import ru.glebik.feature.home.impl.vm.state.HomeIntent
import ru.glebik.feature.home.impl.vm.state.HomeState
import java.time.LocalDateTime

//internal не навесить на весь модуль, тк HomeScreen() вызывается напрямую из MainActivity (нужна прослойка навигации)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productUiMapper: ProductUiMapper,
) : MviViewModel<HomeState, HomeEffect, HomeIntent>() {

    init {
        handleIntent(HomeIntent.Load)
    }

    override fun initialState(): HomeState = HomeState(
        searchQuery = INITIAL_SEARCH_QUERY_VALUE,
        products = loading()
    )

    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.Load -> load()
            is HomeIntent.OnEditProductClick -> onEditProductClick(intent.productId)
            is HomeIntent.OnRemoveProductClick -> onRemoveProductClick(intent.productId)
            is HomeIntent.OnSearchQueryChange -> onSearchQueryChange(intent.query)
            HomeIntent.OnSearchClick -> onSearchClick()
        }
    }

    private fun load() {
        val products = listOf(
            ProductModel(
                id = 3806,
                name = "Tania Parsons",
                time = LocalDateTime.now(),
                tags = listOf(),
                amount = 7867
            ),
            ProductModel(
                id = 386,
                name = "Tania Parsons",
                time = LocalDateTime.now(),
                tags = listOf(),
                amount = 7867
            )
        )

        val uiProducts = products.map { productUiMapper.transform(it) }.toPersistentList()

        mutableState.update {
            it.copy(
                products = content(uiProducts)
            )
        }
    }

    private fun onEditProductClick(productId: Int) {

    }

    private fun onRemoveProductClick(productId: Int) {

    }

    private fun onSearchQueryChange(query: String) {

    }

    private fun onSearchClick() {

    }

    override fun onException(error: Throwable) {
        super.onException(error)
        mutableState.update {
            it.copy(products = failure(errorMessage = error.localizedMessage.orEmpty(), error))
        }
    }

    companion object {
        private const val INITIAL_SEARCH_QUERY_VALUE = ""
    }
}