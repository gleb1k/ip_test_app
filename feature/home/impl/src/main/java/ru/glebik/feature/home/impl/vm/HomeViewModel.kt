package ru.glebik.feature.home.impl.vm

import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.update
import ru.glebik.core.arch.MviViewModel
import ru.glebik.core.arch.util.content
import ru.glebik.core.arch.util.failure
import ru.glebik.core.arch.util.loading
import ru.glebik.feature.home.impl.model.ProductUiModel
import ru.glebik.feature.home.impl.model.TagUiModel
import ru.glebik.feature.home.impl.vm.state.HomeEffect
import ru.glebik.feature.home.impl.vm.state.HomeIntent
import ru.glebik.feature.home.impl.vm.state.HomeState

internal class HomeViewModel(

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
            is HomeIntent.OnEditProductClick -> TODO()
            is HomeIntent.OnRemoveProductClick -> TODO()
            is HomeIntent.OnSearchQueryChange -> TODO()
            HomeIntent.OnSearchClick -> TODO()
        }
    }


    private fun load() {
        val stub = persistentListOf(
            ProductUiModel(
                id = 1739,
                name = "Iphone 13",
                time = "01.10.2021",
                tags = persistentListOf(
                    TagUiModel("что-то"),
                    TagUiModel("что-то2"),
                    TagUiModel("что-то3")
                ),
                amount = 15
            ),
            ProductUiModel(
                id = 173,
                name = "Iphone 13",
                time = "01.10.2021",
                tags = persistentListOf(
                    TagUiModel("что-то"),
                    TagUiModel("что-то2"),
                    TagUiModel("большой тэг")
                ),
                amount = 15
            ),
            ProductUiModel(
                id = 1,
                name = "Iphone 13",
                time = "01.10.2021",
                tags = persistentListOf(
                    TagUiModel("что-то"),
                    TagUiModel("что-то2"),
                ),
                amount = 15
            ),
        )

        mutableState.update {
            it.copy(
                products = content(stub)
            )
        }
    }

    private fun onEditProductClick() {

    }

    private fun onRemoveProductClick() {

    }

    private fun onSearchQueryChange() {

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