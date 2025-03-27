package ru.glebik.feature.home.impl.data.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Query("SELECT * FROM item WHERE name LIKE '%' || :searchQuery || '%'")
    fun getProducts(searchQuery: String): Flow<List<ProductEntity>>

    @Query("DELETE FROM item WHERE id= :productId")
    fun deleteProductById(productId : Int)

    @Query("UPDATE item SET amount = amount + 1 WHERE id = :productId")
    fun increaseAmount(productId: Int)

    // Уменьшение счетчика (но не меньше 0)
    @Query("UPDATE item SET amount = CASE WHEN amount > 0 THEN amount - 1 ELSE 0 END WHERE id = :productId")
    fun decreaseAmount(productId: Int)
}