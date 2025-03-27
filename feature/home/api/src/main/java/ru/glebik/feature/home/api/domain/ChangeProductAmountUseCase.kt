package ru.glebik.feature.home.api.domain

interface ChangeProductAmountUseCase {
    suspend fun change(productId: Int, amount: Int)
}
