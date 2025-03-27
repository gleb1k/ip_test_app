package ru.glebik.feature.home.impl.mapper.ui

import jakarta.inject.Inject
import ru.glebik.feature.home.api.model.TagModel
import ru.glebik.feature.home.impl.model.TagUiModel


interface TagUiMapper {
    fun transform(domain: TagModel): TagUiModel
}

internal class TagUiMapperImpl @Inject constructor() : TagUiMapper {

    override fun transform(domain: TagModel): TagUiModel = TagUiModel(domain.name)

}