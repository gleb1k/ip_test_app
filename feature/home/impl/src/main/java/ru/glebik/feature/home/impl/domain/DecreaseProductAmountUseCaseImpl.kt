package ru.glebik.feature.home.impl.domain

import jakarta.inject.Inject
import ru.glebik.feature.home.api.data.HomeRepository
import ru.glebik.feature.home.api.domain.DecreaseProductAmountUseCase

class DecreaseProductAmountUseCaseImpl @Inject constructor(
    private val repository: HomeRepository,
) : DecreaseProductAmountUseCase {
    override suspend fun decrease(productId: Int) = repository.decreaseProductAmount(productId)
}