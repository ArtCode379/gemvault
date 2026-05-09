package gbcorp.c362.gemvault.pro.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import gbcorp.c362.gemvault.pro.data.entity.GVLTOrderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Query("SELECT * FROM orders")
    fun observeAll(): Flow<List<GVLTOrderEntity>>

    @Query("SELECT * FROM orders")
    suspend fun getAll(): List<GVLTOrderEntity>

    @Query("SELECT * FROM orders WHERE order_number = :orderNumber")
    fun observeByNumber(orderNumber: String): Flow<GVLTOrderEntity?>

    @Query("SELECT * FROM orders WHERE order_number = :orderNumber")
    suspend fun getByNumber(orderNumber: String): GVLTOrderEntity?

    @Insert
    suspend fun save(orderEntity: GVLTOrderEntity): Long

    @Query("DELETE FROM orders WHERE order_number = :orderNumber")
    suspend fun deleteByNumber(orderNumber: String)
}