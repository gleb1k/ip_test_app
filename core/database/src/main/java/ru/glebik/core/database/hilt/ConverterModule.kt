package ru.glebik.core.database.hilt

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.glebik.core.database.StringListConverter


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideStringListConverter(
        gson: Gson
    ): StringListConverter = StringListConverter(gson)
}