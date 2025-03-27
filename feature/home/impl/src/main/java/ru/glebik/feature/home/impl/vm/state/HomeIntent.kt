package ru.glebik.feature.home.impl.vm.state

import ru.glebik.core.arch.MviIntent

sealed interface HomeIntent : MviIntent {
    data object Load : HomeIntent
    data class OnSearchQueryChange(val query: String) : HomeIntent
    data object OnSearchClick : HomeIntent
    data class OnRemoveProductClick(val productId: Int) : HomeIntent
    data class OnEditProductClick(val productId: Int) : HomeIntent

    data object OnHideAmountDialog : HomeIntent
    data object OnConfirmDialogClick : HomeIntent
    data object OnIncreaseDialogClick : HomeIntent
    data object OnDecreaseDialogClick : HomeIntent
}