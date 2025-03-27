package ru.glebik.feature.home.impl.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.glebik.feature.home.api.data.HomeRepository
import ru.glebik.feature.home.impl.data.repository.HomeRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class HomeRepositoryModule {

    @Binds
    abstract fun bindHomeRepository(
        impl: HomeRepositoryImpl,
    ): HomeRepository

}