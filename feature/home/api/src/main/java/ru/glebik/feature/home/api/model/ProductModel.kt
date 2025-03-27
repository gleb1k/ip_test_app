package ru.glebik.feature.home.api.model

import java.time.LocalDateTime

data class ProductModel(
    val id: Int,
    val name: String,
    val time: LocalDateTime,
    val tags: List<TagModel>,
    val amount: Int
)