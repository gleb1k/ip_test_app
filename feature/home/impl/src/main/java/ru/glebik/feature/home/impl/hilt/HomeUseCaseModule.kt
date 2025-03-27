package ru.glebik.feature.home.impl.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.glebik.feature.home.api.domain.ChangeProductAmountUseCase
import ru.glebik.feature.home.api.domain.DeleteProductUseCase
import ru.glebik.feature.home.api.domain.GetProductsUseCase
import ru.glebik.feature.home.impl.domain.ChangeProductAmountUseCaseImpl
import ru.glebik.feature.home.impl.domain.DeleteProductUseCaseImpl
import ru.glebik.feature.home.impl.domain.GetProductsUseCaseImpl


@Module
@InstallIn(ViewModelComponent::class)
internal abstract class HomeUseCaseModule {

    @Binds
    abstract fun bindSearchProductsUseCase(
        impl: GetProductsUseCaseImpl,
    ): GetProductsUseCase

    @Binds
    abstract fun bindDeleteProductUseCase(
        impl: DeleteProductUseCaseImpl,
    ): DeleteProductUseCase

    @Binds
    abstract fun bindIncreaseProductAmountUseCase(
        impl: ChangeProductAmountUseCaseImpl,
    ): ChangeProductAmountUseCase

}