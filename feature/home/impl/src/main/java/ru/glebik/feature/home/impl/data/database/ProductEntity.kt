package ru.glebik.feature.home.impl.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity(tableName = "item")
data class ProductEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "time") val time : LocalDateTime,
    @ColumnInfo(name = "tags") val tags : List<String>,
    @ColumnInfo(name = "amount") val amount : Int,
)