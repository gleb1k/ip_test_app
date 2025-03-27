package ru.glebik.feature.home.impl.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.glebik.feature.home.api.domain.DecreaseProductAmountUseCase
import ru.glebik.feature.home.api.domain.DeleteProductUseCase
import ru.glebik.feature.home.api.domain.GetProductsUseCase
import ru.glebik.feature.home.api.domain.IncreaseProductAmountUseCase
import ru.glebik.feature.home.impl.domain.DecreaseProductAmountUseCaseImpl
import ru.glebik.feature.home.impl.domain.DeleteProductUseCaseImpl
import ru.glebik.feature.home.impl.domain.GetProductsUseCaseImpl
import ru.glebik.feature.home.impl.domain.IncreaseProductAmountUseCaseImpl


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
        impl: IncreaseProductAmountUseCaseImpl,
    ): IncreaseProductAmountUseCase

    @Binds
    abstract fun bindDecreaseProductAmountUseCase(
        impl: DecreaseProductAmountUseCaseImpl,
    ): DecreaseProductAmountUseCase
}