package ru.glebik.feature.home.impl.model

import kotlinx.collections.immutable.PersistentList
import ru.glebik.core.arch.util.ViewProperty

internal data class HomeUiModel(
    val searchQuery: String,
    val products: ViewProperty<PersistentList<ProductUiModel>>
)