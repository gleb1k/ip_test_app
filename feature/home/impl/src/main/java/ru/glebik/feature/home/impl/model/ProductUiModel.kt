package ru.glebik.feature.home.impl.model

import kotlinx.collections.immutable.PersistentList

internal data class ProductUiModel(
    val id: Int,
    val name: String,
    val time: String,
    val tags: PersistentList<TagUiModel>,
    val amount: Int
)