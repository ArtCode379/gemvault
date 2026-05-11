package gbcorp.c362.gemvault.pro.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import gbcorp.c362.gemvault.pro.data.entity.GMVTOrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Query("SELECT * FROM orders")
    fun observeAll(): Flow<List<GMVTOrderEntity>>

    @Query("SELECT * FROM orders")
    suspend fun getAll(): List<GMVTOrderEntity>

    @Query("SELECT * FROM orders WHERE order_number = :orderNumber")
    fun observeByNumber(orderNumber: String): Flow<GMVTOrderEntity?>

    @Query("SELECT * FROM orders WHERE order_number = :orderNumber")
    suspend fun getByNumber(orderNumber: String): GMVTOrderEntity?

    @Insert
    suspend fun save(orderEntity: GMVTOrderEntity): Long

    @Query("DELETE FROM orders WHERE order_number = :orderNumber")
    suspend fun deleteByNumber(orderNumber: String)
}