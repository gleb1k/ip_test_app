package ru.glebik.feature.home.impl.domain

import jakarta.inject.Inject
import ru.glebik.feature.home.api.data.HomeRepository
import ru.glebik.feature.home.api.domain.DeleteProductUseCase

class DeleteProductUseCaseImpl @Inject constructor(
    private val repository: HomeRepository,
) : DeleteProductUseCase {
    override suspend fun delete(productId: Int) = repository.deleteProduct(productId)
}
