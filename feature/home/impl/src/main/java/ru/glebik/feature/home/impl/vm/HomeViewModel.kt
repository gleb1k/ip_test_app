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
import ru.glebik.core.arch.util.safeContent
import ru.glebik.core.ktx.CoroutineDispatchers
import ru.glebik.feature.home.api.domain.ChangeProductAmountUseCase
import ru.glebik.feature.home.api.domain.DeleteProductUseCase
import ru.glebik.feature.home.api.domain.GetProductsUseCase
import ru.glebik.feature.home.impl.mapper.ui.ProductUiMapper
import ru.glebik.feature.home.impl.model.dialog.ProductAmountState
import ru.glebik.feature.home.impl.model.dialog.ProductRemoveState
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
    private val changeProductAmountUseCase: ChangeProductAmountUseCase,
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
        productAmountState = ProductAmountState(
            isShow = false,
            productId = Int.MIN_VALUE,
            amount = Int.MIN_VALUE,
        ),
        productRemoveState = ProductRemoveState(
            isShow = false,
            productId = Int.MIN_VALUE
        )
    )

    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            HomeIntent.Load -> load()
            is HomeIntent.OnEditProductClick -> onEditProductClick(intent.productId)
            is HomeIntent.OnRemoveProductClick -> onRemoveProductClick(intent.productId)
            is HomeIntent.OnSearchQueryChange -> onSearchQueryChange(intent.query)
            HomeIntent.OnSearchClick -> onSearchClick()
            HomeIntent.OnConfirmChangeAmountDialogClick -> onConfirmChangeAmountDialogClick()
            HomeIntent.OnDecreaseDialogClick -> onDecreaseDialogClick()
            HomeIntent.HideChangeAmountDialog -> onHideAmountDialog()
            HomeIntent.OnIncreaseDialogClick -> onIncreaseDialogClick()
            HomeIntent.HideRemoveProductDialog -> hideRemoveProductDialog()
            HomeIntent.OnConfirmRemoveProductDialogClick -> onConfirmRemoveProductDialogClick()
        }
    }

    private fun load() {
        loadProductsInternal(INITIAL_SEARCH_QUERY_VALUE)
    }

    private fun loadProductsInternal(query: String) {
        viewModelScope.launchSafe(dispatchers.io) {
            getProductsUseCase.search(query).collect { productsWrapper ->
                when (productsWrapper) {
                    is ResultWrapper.Failure -> mutableState.update {
                        it.copy(
                            products = failure(
                                productsWrapper.exception?.localizedMessage.orEmpty(),
                                productsWrapper.exception
                            )
                        )
                    }

                    ResultWrapper.Loading -> mutableState.update {
                        it.copy(products = loading())
                    }

                    is ResultWrapper.Success -> {
                        val mapped = productsWrapper.value.map {
                            productUiMapper.transform(it)
                        }.toPersistentList()

                        mutableState.update { it.copy(products = content(mapped)) }
                    }
                }
            }
        }
    }

    private fun hideRemoveProductDialog() {
        mutableState.update {
            it.copy(productRemoveState = it.productRemoveState.copy(isShow = false))
        }
    }

    private fun onConfirmRemoveProductDialogClick() {
        viewModelScope.launchSafe(dispatchers.io) {
            deleteProductUseCase.delete(mviState.productRemoveState.productId)
            handleIntent(HomeIntent.HideRemoveProductDialog)
        }
    }

    private fun onEditProductClick(productId: Int) {
        val products = mviState.products.safeContent ?: return
        val productToShow = products.find { it.id == productId } ?: return

        mutableState.update {
            it.copy(
                productAmountState = ProductAmountState(
                    isShow = true, productId = productId, amount = productToShow.amount
                )
            )
        }
    }

    private fun onConfirmChangeAmountDialogClick() {
        viewModelScope.launchSafe(dispatchers.io) {
            val dialogState = mviState.productAmountState
            changeProductAmountUseCase.change(dialogState.productId, dialogState.amount)
            handleIntent(HomeIntent.HideChangeAmountDialog)
        }
    }

    private fun onDecreaseDialogClick() {
        mutableState.update {
            it.copy(productAmountState = it.productAmountState.copy(amount = it.productAmountState.amount.dec()))
        }
    }

    private fun onHideAmountDialog() {
        mutableState.update {
            it.copy(productAmountState = it.productAmountState.copy(isShow = false))
        }
    }

    private fun onIncreaseDialogClick() {
        mutableState.update {
            it.copy(productAmountState = it.productAmountState.copy(amount = it.productAmountState.amount.inc()))
        }
    }

    private fun onRemoveProductClick(productId: Int) {
        mutableState.update {
            it.copy(
                productRemoveState = ProductRemoveState(isShow = true, productId = productId)
            )
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