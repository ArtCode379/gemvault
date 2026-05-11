package gbcorp.c362.gemvault.pro.ui.composable.screen.productdetails

import gbcorp.c362.gemvault.pro.R
import gbcorp.c362.gemvault.pro.data.model.Product
import gbcorp.c362.gemvault.pro.ui.composable.shared.GMVTContentWrapper
import gbcorp.c362.gemvault.pro.ui.composable.shared.GMVTEmptyView
import gbcorp.c362.gemvault.pro.ui.state.GMVTDataUiState
import gbcorp.c362.gemvault.pro.ui.theme.BackgroundLight
import gbcorp.c362.gemvault.pro.ui.theme.Charcoal
import gbcorp.c362.gemvault.pro.ui.theme.Gold
import gbcorp.c362.gemvault.pro.ui.theme.MutedGray
import gbcorp.c362.gemvault.pro.ui.viewmodel.GMVTProductDetailsViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: GMVTProductDetailsViewModel = koinViewModel(),
) {
    val productState by viewModel.productDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.observeProductDetails(productId)
    }

    ProductDetailsScreenContent(
        productState = productState,
        modifier = modifier,
        onAddToCart = viewModel::addProductToCart
    )
}

@Composable
private fun ProductDetailsScreenContent(
    productState: GMVTDataUiState<Product>,
    modifier: Modifier = Modifier,
    onAddToCart: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        GMVTContentWrapper(
            dataState = productState,
            dataPopulated = {
                val product = (productState as GMVTDataUiState.Populated).data
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    AsyncImage(
                        model = product.imageUrl,
                        contentDescription = stringResource(R.string.product_image_description),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    )
                    Column(modifier = Modifier.padding(20.dp)) {
                        SuggestionChip(
                            onClick = {},
                            label = {
                                Text(
                                    text = stringResource(product.category.titleRes),
                                    style = MaterialTheme.typography.labelLarge,
                                    color = Charcoal
                                )
                            },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = Gold.copy(alpha = 0.15f)
                            ),
                            border = SuggestionChipDefaults.suggestionChipBorder(
                                enabled = true,
                                borderColor = Gold
                            )
                        )
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text = product.title,
                            style = MaterialTheme.typography.headlineLarge,
                            color = Charcoal
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "£${String.format("%.2f", product.price)}",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Gold
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = product.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MutedGray
                        )
                        Spacer(Modifier.height(32.dp))
                        Button(
                            onClick = onAddToCart,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(28.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Charcoal)
                        ) {
                            Text(
                                text = stringResource(R.string.button_add_to_cart_label).uppercase(),
                                style = MaterialTheme.typography.labelLarge,
                                color = Gold
                            )
                        }
                        Spacer(Modifier.height(24.dp))
                    }
                }
            },
            dataEmpty = {
                GMVTEmptyView(
                    primaryText = stringResource(R.string.product_details_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}
