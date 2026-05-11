package gbcorp.c362.gemvault.pro.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import gbcorp.c362.gemvault.pro.data.dao.CartItemDao
import gbcorp.c362.gemvault.pro.data.dao.OrderDao
import gbcorp.c362.gemvault.pro.data.database.converter.Converters
import gbcorp.c362.gemvault.pro.data.entity.GMVTCartItemEntity
import gbcorp.c362.gemvault.pro.data.entity.GMVTOrderEntity

@Database(
    entities = [GMVTCartItemEntity::class, GMVTOrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class GMVTDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}