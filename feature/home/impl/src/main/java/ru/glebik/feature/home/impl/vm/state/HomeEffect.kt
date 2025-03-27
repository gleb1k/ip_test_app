package ru.glebik.feature.home.impl.vm.state

import ru.glebik.core.arch.MviEffect

sealed interface HomeEffect : MviEffect {
    data class ShowSnackBar(val text: String) : HomeEffect
}