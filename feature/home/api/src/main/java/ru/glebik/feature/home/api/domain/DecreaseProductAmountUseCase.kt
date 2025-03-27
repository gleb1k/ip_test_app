package ru.glebik.feature.home.api.domain

interface DecreaseProductAmountUseCase {
    suspend fun decrease(productId: Int)
}