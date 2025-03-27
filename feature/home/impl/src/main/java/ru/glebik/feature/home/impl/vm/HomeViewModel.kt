package ru.glebik.feature.home.impl.vm

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import ru.glebik.core.arch.MviViewModel
import ru.glebik.core.arch.util.ResultWrapper
import ru.glebik.core.arch.util.content
import ru.glebik.core.arch.util.failure
import ru.glebik.core.arch.util.loading
import ru.glebik.core.ktx.CoroutineDispatchers
import ru.glebik.feature.home.api.domain.DecreaseProductAmountUseCase
import ru.glebik.feature.home.api.domain.DeleteProductUseCase
import ru.glebik.feature.home.api.domain.GetProductsUseCase
import ru.glebik.feature.home.api.domain.IncreaseProductAmountUseCase
import ru.glebik.feature.home.impl.mapper.ui.ProductUiMapper
import ru.glebik.feature.home.impl.vm.state.HomeEffect
import ru.glebik.feature.home.impl.vm.state.HomeIntent
import ru.glebik.feature.home.impl.vm.state.HomeState

//internal не навесить на весь модуль, тк HomeScreen() вызывается напрямую из MainActivity (нужна прослойка навигации)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productUiMapper: ProductUiMapper,
    private val dispatchers: CoroutineDispatchers,
    private val getProductsUseCase: GetProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val increaseProductAmountUseCase: IncreaseProductAmountUseCase,
    private val decreaseProductAmountUseCase: DecreaseProductAmountUseCase,
) : MviViewModel<HomeState, HomeEffect, HomeIntent>() {

    private val searchQueryPublisher = MutableSharedFlow<String>(extraBufferCapacity = 1)

    init {
        handleIntent(HomeIntent.Load)
        listenToSearchQuery()
    }

    private fun listenToSearchQuery() {
        searchQueryPublisher
            .map { it.trim() }
            .distinctUntilChanged()
            .debounce(SEARCH_DEBOUNCE)
            .flowOn(dispatchers.default)
            .mapLatest(::loadProductsInternal)
            .launchIn(viewModelScope)
    }

    override fun initialState(): HomeState = HomeState(
        searchQuery = INITIAL_SEARCH_QUERY_VALUE,
        products = loading(),
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
        loadProductsInternal(INITIAL_SEARCH_QUERY_VALUE)
    }

    private fun loadProductsInternal(query: String) {
        viewModelScope.launchSafe(dispatchers.io) {
            getProductsUseCase.search(query).collect { productsWrapper ->
                when (val wrapper = productsWrapper) {
                    is ResultWrapper.Failure -> {

                    }

                    ResultWrapper.Loading -> mutableState.update {
                        it.copy(products = loading())
                    }

                    is ResultWrapper.Success -> {
                        val mapped = wrapper.value.map {
                            productUiMapper.transform(it)
                        }.toPersistentList()

                        mutableState.update { it.copy(products = content(mapped)) }
                    }
                }
            }
        }
    }

    private fun onEditProductClick(productId: Int) {

    }

    private fun onRemoveProductClick(productId: Int) {
        viewModelScope.launchSafe(dispatchers.io) {
            deleteProductUseCase.delete(productId)
        }
    }

    private fun onSearchQueryChange(query: String) {
        viewModelScope.launchSafe(dispatchers.main.immediate) {
            mutableState.update { it.copy(searchQuery = query) }
            searchQueryPublisher.emit(query)
        }
    }

    private fun onSearchClick() {
        loadProductsInternal(mviState.searchQuery)
    }

    override fun onException(error: Throwable) {
        super.onException(error)
        mutableState.update {
            it.copy(products = failure(errorMessage = error.localizedMessage.orEmpty(), error))
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE = 200L
        private const val INITIAL_SEARCH_QUERY_VALUE = ""
    }
}