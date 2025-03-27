package ru.glebik.feature.home.api.domain

interface DeleteProductUseCase {
    suspend fun delete(productId: Int)
}