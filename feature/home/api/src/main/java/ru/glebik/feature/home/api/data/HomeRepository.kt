package ru.glebik.feature.home.api.data

import kotlinx.coroutines.flow.Flow
import ru.glebik.core.arch.util.ResultWrapper
import ru.glebik.feature.home.api.model.ProductModel

interface HomeRepository {
    fun getProducts(query: String): Flow<ResultWrapper<List<ProductModel>>>
    suspend fun deleteProduct(productId: Int)
    suspend fun increaseProductAmount (productId: Int)
    suspend fun decreaseProductAmount (productId: Int)
}