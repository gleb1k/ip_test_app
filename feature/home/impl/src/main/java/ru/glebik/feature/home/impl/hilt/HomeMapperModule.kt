package ru.glebik.feature.home.impl.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.glebik.feature.home.impl.mapper.domain.ProductEntityMapper
import ru.glebik.feature.home.impl.mapper.domain.ProductEntityMapperImpl
import ru.glebik.feature.home.impl.mapper.ui.ProductUiMapper
import ru.glebik.feature.home.impl.mapper.ui.ProductUiMapperImpl
import ru.glebik.feature.home.impl.mapper.ui.TagUiMapper
import ru.glebik.feature.home.impl.mapper.ui.TagUiMapperImpl


@Module
@InstallIn(ViewModelComponent::class)
internal abstract class HomeMapperModule {

    @Binds
    abstract fun bindTagUiMapper(
        impl: TagUiMapperImpl,
    ): TagUiMapper

    @Binds
    abstract fun bindProductUiMapper(
        impl: ProductUiMapperImpl,
    ): ProductUiMapper

    @Binds
    abstract fun bindProductEntityMapper(
        impl: ProductEntityMapperImpl,
    ): ProductEntityMapper

}