package ru.glebik.feature.home.impl.vm.state

import ru.glebik.core.arch.MviEffect

sealed interface HomeEffect : MviEffect {
    data class ShowProductAmountDialog(val productId: Int) : HomeEffect
    data class HideProductAmountDialog(val productId: Int) : HomeEffect
}