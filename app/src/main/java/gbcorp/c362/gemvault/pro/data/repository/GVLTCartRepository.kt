package gbcorp.c362.gemvault.pro.data.repository

import gbcorp.c362.gemvault.pro.data.dao.CartItemDao
import gbcorp.c362.gemvault.pro.data.entity.GVLTCartItemEntity
import gbcorp.c362.gemvault.pro.data.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GVLTCartRepository(
    private val cartItemDao: CartItemDao,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeAll(): Flow<List<GVLTCartItemEntity>> {
        return cartItemDao.observeAll()
    }

    suspend fun getAll(): List<GVLTCartItemEntity> {
        return cartItemDao.getAll()
    }

    suspend fun deleteById(id: Int) {
        withContext(coroutineDispatcher) {
            cartItemDao.deleteById(id)
        }
    }

    suspend fun deleteAll() {
        withContext(coroutineDispatcher) {
            cartItemDao.deleteAll()
        }
    }

    suspend fun incrementQuantity(productId: Int) {
        withContext(coroutineDispatcher) {
            cartItemDao.incrementQuantity(productId)
        }
    }

    suspend fun incrementProductQuantityOrAdd(product: Product) {
        withContext(coroutineDispatcher) {
            cartItemDao.incrementProductQuantityOrAdd(product)
        }
    }

    suspend fun decrementProductQuantityOrRemove(product: Product) {
        withContext(coroutineDispatcher) {
            cartItemDao.decrementProductQuantityOrRemove(product)
        }
    }
}