package ru.glebik.feature.home.impl.vm.state

import ru.glebik.core.arch.MviIntent

sealed interface HomeIntent : MviIntent {
    data object Load : HomeIntent
    data class OnSearchQueryChange(val query: String) : HomeIntent
    data object OnSearchClick : HomeIntent
    data class OnRemoveProductClick(val productId: Int) : HomeIntent
    data class OnEditProductClick(val productId: Int) : HomeIntent

    data object HideRemoveProductDialog : HomeIntent
    data object OnConfirmRemoveProductDialogClick : HomeIntent

    data object HideChangeAmountDialog : HomeIntent
    data object OnConfirmChangeAmountDialogClick : HomeIntent
    data object OnIncreaseDialogClick : HomeIntent
    data object OnDecreaseDialogClick : HomeIntent
}