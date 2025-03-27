package ru.glebik.feature.home.api.domain

import kotlinx.coroutines.flow.Flow
import ru.glebik.core.arch.util.ResultWrapper
import ru.glebik.feature.home.api.model.ProductModel


interface GetProductsUseCase {
    fun search(query: String): Flow<ResultWrapper<List<ProductModel>>>
}