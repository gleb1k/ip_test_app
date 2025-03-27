package ru.glebik.feature.home.impl.mapper.ui

import jakarta.inject.Inject
import kotlinx.collections.immutable.toPersistentList
import ru.glebik.feature.home.api.model.ProductModel
import ru.glebik.feature.home.impl.model.ProductUiModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface ProductUiMapper {
    fun transform(domain: ProductModel): ProductUiModel
}

class ProductUiMapperImpl @Inject constructor(
    private val tagUiMapper: TagUiMapper,
) : ProductUiMapper {
    override fun transform(domain: ProductModel): ProductUiModel {
        with(domain) {
            return ProductUiModel(
                id = id,
                name = name,
                time = transformDateTime(time),
                tags = tags.map { tagUiMapper.transform(it) }.toPersistentList(),
                amount = amount
            )
        }
    }

    private fun transformDateTime(dateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val formattedDate = dateTime.format(formatter)
        return formattedDate
    }
}