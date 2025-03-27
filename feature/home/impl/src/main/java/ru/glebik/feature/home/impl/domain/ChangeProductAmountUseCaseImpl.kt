package ru.glebik.feature.home.impl.domain

import jakarta.inject.Inject
import ru.glebik.feature.home.api.data.HomeRepository
import ru.glebik.feature.home.api.domain.ChangeProductAmountUseCase

class ChangeProductAmountUseCaseImpl @Inject constructor(
    private val repository: HomeRepository,
) : ChangeProductAmountUseCase {
    override suspend fun change(productId: Int, amount: Int) =
        repository.changeProductAmount(productId, amount)
}