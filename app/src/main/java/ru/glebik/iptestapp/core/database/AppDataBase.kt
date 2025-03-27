package ru.glebik.iptestapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.glebik.core.database.LocalDateConverter
import ru.glebik.core.database.StringListConverter
import ru.glebik.feature.home.impl.data.database.ProductEntity
import ru.glebik.feature.home.impl.data.database.ProductsDao


@Database(
    entities = [
        ProductEntity::class,
    ],
    version = AppDataBaseConstants.VERSION,
    autoMigrations = []
)
@TypeConverters(
    LocalDateConverter::class,
    StringListConverter::class
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
}