package gbcorp.c362.gemvault.pro.di

import gbcorp.c362.gemvault.pro.ui.viewmodel.GMVTAppViewModel
import gbcorp.c362.gemvault.pro.ui.viewmodel.GMVTCartViewModel
import gbcorp.c362.gemvault.pro.ui.viewmodel.GMVTCheckoutViewModel
import gbcorp.c362.gemvault.pro.ui.viewmodel.GMVTOnboardingVM
import gbcorp.c362.gemvault.pro.ui.viewmodel.GMVTOrderViewModel
import gbcorp.c362.gemvault.pro.ui.viewmodel.GMVTProductDetailsViewModel
import gbcorp.c362.gemvault.pro.ui.viewmodel.GMVTProductViewModel
import gbcorp.c362.gemvault.pro.ui.viewmodel.GMVTSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        GMVTAppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        GMVTSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        GMVTOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        GMVTProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        GMVTProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        GMVTCheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        GMVTCartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        GMVTOrderViewModel(
            orderRepository = get(),
        )
    }
}