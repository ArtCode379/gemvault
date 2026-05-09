package gbcorp.c362.gemvault.pro.di

import gbcorp.c362.gemvault.pro.ui.viewmodel.GVLTAppViewModel
import gbcorp.c362.gemvault.pro.ui.viewmodel.GVLTCartViewModel
import gbcorp.c362.gemvault.pro.ui.viewmodel.GVLTCheckoutViewModel
import gbcorp.c362.gemvault.pro.ui.viewmodel.GVLTOnboardingVM
import gbcorp.c362.gemvault.pro.ui.viewmodel.GVLTOrderViewModel
import gbcorp.c362.gemvault.pro.ui.viewmodel.GVLTProductDetailsViewModel
import gbcorp.c362.gemvault.pro.ui.viewmodel.GVLTProductViewModel
import gbcorp.c362.gemvault.pro.ui.viewmodel.GVLTSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        GVLTAppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        GVLTSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        GVLTOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        GVLTProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        GVLTProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        GVLTCheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        GVLTCartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        GVLTOrderViewModel(
            orderRepository = get(),
        )
    }
}