package gbcorp.c362.gemvault.pro.data.repository

import gbcorp.c362.gemvault.pro.data.datastore.GVLTOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GVLTOnboardingRepo(
    private val gvltOnboardingStoreManager: GVLTOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return gvltOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            gvltOnboardingStoreManager.setOnboardedState(state)
        }
    }
}