package gbcorp.c362.gemvault.pro.data.repository

import gbcorp.c362.gemvault.pro.data.datastore.GMVTOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GMVTOnboardingRepo(
    private val gmvtOnboardingStoreManager: GMVTOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return gmvtOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            gmvtOnboardingStoreManager.setOnboardedState(state)
        }
    }
}