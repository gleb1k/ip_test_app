package ru.glebik.feature.home.impl.domain

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import ru.glebik.core.arch.util.ResultWrapper
import ru.glebik.feature.home.api.data.HomeRepository
import ru.glebik.feature.home.api.domain.GetProductsUseCase
import ru.glebik.feature.home.api.model.ProductModel


class GetProductsUseCaseImpl @Inject constructor(
    private val repository: HomeRepository,
) : GetProductsUseCase {
    override fun search(query: String): Flow<ResultWrapper<List<ProductModel>>> =
        repository.getProducts(query)
}