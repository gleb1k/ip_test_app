package ru.glebik.feature.home.impl.data.repository

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import ru.glebik.core.arch.util.ResultWrapper
import ru.glebik.feature.home.api.data.HomeRepository
import ru.glebik.feature.home.api.model.ProductModel
import ru.glebik.feature.home.impl.data.database.ProductsDao
import ru.glebik.feature.home.impl.mapper.domain.ProductEntityMapper

class HomeRepositoryImpl @Inject constructor(
    private val dao: ProductsDao,
    private val productEntityMapper: ProductEntityMapper,
) : HomeRepository {

    override fun getProducts(query: String): Flow<ResultWrapper<List<ProductModel>>> = flow {
        emit(ResultWrapper.Loading)
        dao.getProducts(query).collect { products ->
            val mapped = products.map { entity ->
                productEntityMapper.transform(entity)
            }
            emit(ResultWrapper.Success(mapped))
        }
    }.catch {
        emit(ResultWrapper.Failure(it))
    }

    override suspend fun deleteProduct(productId: Int) {
        dao.deleteProductById(productId)
    }

    override suspend fun increaseProductAmount(productId: Int) {
        dao.increaseAmount(productId)
    }

    override suspend fun decreaseProductAmount(productId: Int) {
        dao.decreaseAmount(productId)
    }
}
