package gbcorp.c362.gemvault.pro.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import gbcorp.c362.gemvault.pro.data.dao.CartItemDao
import gbcorp.c362.gemvault.pro.data.dao.OrderDao
import gbcorp.c362.gemvault.pro.data.database.converter.Converters
import gbcorp.c362.gemvault.pro.data.entity.GVLTCartItemEntity
import gbcorp.c362.gemvault.pro.data.entity.GVLTOrderEntity

@Database(
    entities = [GVLTCartItemEntity::class, GVLTOrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class GVLTDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}