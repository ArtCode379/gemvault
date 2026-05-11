package gbcorp.c362.gemvault.pro.di

import gbcorp.c362.gemvault.pro.data.datastore.GMVTOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { GMVTOnboardingPrefs(androidContext()) }
}