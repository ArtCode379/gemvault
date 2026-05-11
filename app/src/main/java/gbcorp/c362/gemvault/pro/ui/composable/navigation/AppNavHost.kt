package gbcorp.c362.gemvault.pro.ui.composable.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import gbcorp.c362.gemvault.pro.ui.composable.screen.cart.CartScreen
import gbcorp.c362.gemvault.pro.ui.composable.screen.checkout.CheckoutScreen
import gbcorp.c362.gemvault.pro.ui.composable.screen.home.HomeScreen
import gbcorp.c362.gemvault.pro.ui.composable.screen.onboarding.OnboardingScreen
import gbcorp.c362.gemvault.pro.ui.composable.screen.order.OrdersScreen
import gbcorp.c362.gemvault.pro.ui.composable.screen.productdetails.ProductDetailsScreen
import gbcorp.c362.gemvault.pro.ui.composable.screen.settings.SettingsScreen
import gbcorp.c362.gemvault.pro.ui.composable.screen.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Splash,
        modifier = modifier,
    ) {
        composable<NavRoute.Splash> {
            SplashScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(route = NavRoute.Home) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToOnboarding = {
                    navController.navigate(route = NavRoute.Onboarding) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.Onboarding> {
            OnboardingScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(NavRoute.Home) {
                        popUpTo(NavRoute.Onboarding) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.Home> {
            HomeScreen(
                onNavigateToProductDetails = { id: Int ->
                    navController.navigate(
                        route = NavRoute.ProductDetails(id = id)
                    )
                }
            )
        }

        composable<NavRoute.ProductDetails> { backStackEntry ->
            val productDetails: NavRoute.ProductDetails = backStackEntry.toRoute()
            ProductDetailsScreen(
                productId = productDetails.id,
            )
        }

        composable<NavRoute.Cart> {
            CartScreen(
                onNavigateToCheckoutScreen = {
                    navController.navigate(NavRoute.Checkout)
                }
            )
        }

        composable<NavRoute.Checkout> {
            CheckoutScreen(
                onNavigateToOrdersScreen = {
                    navController.navigate(NavRoute.Orders) {
                        popUpTo(NavRoute.Home) { inclusive = false }
                    }
                }
            )
        }

        composable<NavRoute.Orders> {
            OrdersScreen()
        }

        composable<NavRoute.Settings> {
            SettingsScreen()
        }
    }
}