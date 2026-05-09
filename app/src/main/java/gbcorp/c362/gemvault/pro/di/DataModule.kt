package gbcorp.c362.gemvault.pro.di

import gbcorp.c362.gemvault.pro.data.repository.GVLTCartRepository
import gbcorp.c362.gemvault.pro.data.repository.GVLTOnboardingRepo
import gbcorp.c362.gemvault.pro.data.repository.GVLTOrderRepository
import gbcorp.c362.gemvault.pro.data.repository.GVLTProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        GVLTOnboardingRepo(
            gvltOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { GVLTProductRepository() }

    single {
        GVLTCartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        GVLTOrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}