package gbcorp.c362.gemvault.pro.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class GVLTCartItemEntity(
    @PrimaryKey val id: Int,
    val quantity: Int,
)