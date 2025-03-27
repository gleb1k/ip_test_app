package ru.glebik.feature.home.impl.domain

import jakarta.inject.Inject
import ru.glebik.feature.home.api.data.HomeRepository
import ru.glebik.feature.home.api.domain.IncreaseProductAmountUseCase

class IncreaseProductAmountUseCaseImpl @Inject constructor(
    private val repository: HomeRepository,
) : IncreaseProductAmountUseCase {
    override suspend fun increase(productId: Int) = repository.increaseProductAmount(productId)
}