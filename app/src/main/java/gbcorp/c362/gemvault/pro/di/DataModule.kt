package gbcorp.c362.gemvault.pro.di

import gbcorp.c362.gemvault.pro.data.repository.GMVTCartRepository
import gbcorp.c362.gemvault.pro.data.repository.GMVTOnboardingRepo
import gbcorp.c362.gemvault.pro.data.repository.GMVTOrderRepository
import gbcorp.c362.gemvault.pro.data.repository.GMVTProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        GMVTOnboardingRepo(
            gmvtOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { GMVTProductRepository() }

    single {
        GMVTCartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        GMVTOrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}