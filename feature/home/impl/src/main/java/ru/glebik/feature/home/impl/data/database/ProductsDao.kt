package ru.glebik.feature.home.impl.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Query("SELECT * FROM item WHERE name LIKE '%' || :searchQuery || '%'")
    fun getProducts(searchQuery: String): Flow<List<ProductEntity>>

    @Query("DELETE FROM item WHERE id= :productId")
    fun deleteProductById(productId: Int)

    @Query("UPDATE item SET amount = :amount WHERE id = :productId")
    fun updateAmount(productId: Int, amount: Int)
}