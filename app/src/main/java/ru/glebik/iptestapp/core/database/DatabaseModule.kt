package ru.glebik.iptestapp.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.glebik.core.database.StringListConverter
import ru.glebik.feature.home.impl.data.database.ProductsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val ASSETS_FILE = "data.db"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        stringListConverter: StringListConverter
    ): AppDataBase = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        AppDataBaseConstants.NAME
    )
        .createFromAsset(ASSETS_FILE)
        //.addCallback(DatabaseReadCallback(context))
        .addTypeConverter(stringListConverter)
        .build()

    @Provides
    @Singleton
    fun provideProductsDao(
        database: AppDataBase,
    ): ProductsDao = database.productsDao()
}