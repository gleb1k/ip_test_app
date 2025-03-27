package ru.glebik.feature.home.impl.mapper.domain

import jakarta.inject.Inject
import ru.glebik.feature.home.api.model.ProductModel
import ru.glebik.feature.home.api.model.TagModel
import ru.glebik.feature.home.impl.data.database.ProductEntity

interface ProductEntityMapper {
    fun transform(entity: ProductEntity): ProductModel
}

class ProductEntityMapperImpl @Inject constructor() : ProductEntityMapper {
    override fun transform(entity: ProductEntity): ProductModel {
        with(entity) {
            return ProductModel(
                id = id,
                name = name,
                time = time,
                tags = tags.map { TagModel(it) },
                amount = amount
            )
        }
    }
}