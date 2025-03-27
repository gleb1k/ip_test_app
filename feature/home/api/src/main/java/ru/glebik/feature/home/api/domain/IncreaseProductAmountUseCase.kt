package ru.glebik.feature.home.api.domain

interface IncreaseProductAmountUseCase {
    suspend fun increase(productId: Int)
}
