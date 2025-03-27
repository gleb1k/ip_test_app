package ru.glebik.feature.home.impl.vm.state

import kotlinx.collections.immutable.PersistentList
import ru.glebik.core.arch.MviState
import ru.glebik.core.arch.util.ViewProperty
import ru.glebik.feature.home.impl.model.ProductUiModel

data class HomeState(
    val searchQuery: String,
    val products: ViewProperty<PersistentList<ProductUiModel>>
) : MviState