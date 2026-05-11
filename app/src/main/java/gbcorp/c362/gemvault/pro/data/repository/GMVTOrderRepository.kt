package gbcorp.c362.gemvault.pro.data.repository

import gbcorp.c362.gemvault.pro.data.dao.OrderDao
import gbcorp.c362.gemvault.pro.data.entity.GMVTOrderEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GMVTOrderRepository(
    private val orderDao: OrderDao,
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend fun save(orderEntity: GMVTOrderEntity): Long {
        return withContext(coroutineDispatcher) {
            orderDao.save(orderEntity)
        }
    }

    fun observeAll(): Flow<List<GMVTOrderEntity>> {
        return orderDao.observeAll()
    }


    suspend fun deleteByNumber(orderNumber: String) {
        withContext(coroutineDispatcher) {
            orderDao.deleteByNumber(orderNumber)
        }
    }
}