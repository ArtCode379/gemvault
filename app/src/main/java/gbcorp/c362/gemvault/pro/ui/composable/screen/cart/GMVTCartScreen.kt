package gbcorp.c362.gemvault.pro.ui.composable.screen.cart

import gbcorp.c362.gemvault.pro.R
import gbcorp.c362.gemvault.pro.ui.composable.shared.GMVTContentWrapper
import gbcorp.c362.gemvault.pro.ui.composable.shared.GMVTEmptyView
import gbcorp.c362.gemvault.pro.ui.state.GMVTCartItemUiState
import gbcorp.c362.gemvault.pro.ui.state.GMVTDataUiState
import gbcorp.c362.gemvault.pro.ui.theme.BackgroundLight
import gbcorp.c362.gemvault.pro.ui.theme.BorderGray
import gbcorp.c362.gemvault.pro.ui.theme.Charcoal
import gbcorp.c362.gemvault.pro.ui.theme.Gold
import gbcorp.c362.gemvault.pro.ui.theme.MutedGray
import gbcorp.c362.gemvault.pro.ui.theme.SurfaceWhite
import gbcorp.c362.gemvault.pro.ui.viewmodel.GMVTCartViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: GMVTCartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val cartItemsState by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val totalPrice by viewModel.totalPrice.collectAsStateWithLifecycle()

    CartScreenContent(
        cartItemsState = cartItemsState,
        modifier = modifier,
        totalPrice = totalPrice,
        onPlusItemClick = viewModel::incrementProductInCart,
        onMinusItemClick = viewModel::decrementItemInCart,
        onCompleteOrderButtonClick = onNavigateToCheckoutScreen,
    )
}

@Composable
private fun CartScreenContent(
    cartItemsState: GMVTDataUiState<List<GMVTCartItemUiState>>,
    modifier: Modifier = Modifier,
    totalPrice: Double,
    onPlusItemClick: (Int) -> Unit,
    onMinusItemClick: (Int) -> Unit,
    onCompleteOrderButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        GMVTContentWrapper(
            dataState = cartItemsState,
            dataPopulated = {
                val items = (cartItemsState as GMVTDataUiState.Populated).data
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(items, key = { it.productId }) { item ->
                            CartItemRow(
                                item = item,
                                onPlus = { onPlusItemClick(item.productId) },
                                onMinus = { onMinusItemClick(item.productId) }
                            )
                        }
                    }

                    // Summary footer
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Subtotal",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MutedGray
                                )
                                Text(
                                    text = "£${String.format("%.2f", totalPrice)}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Charcoal
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                            HorizontalDivider(color = BorderGray)
                            Spacer(Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Total",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Charcoal,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "£${String.format("%.2f", totalPrice)}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Gold,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(Modifier.height(16.dp))
                            Button(
                                onClick = onCompleteOrderButtonClick,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                shape = RoundedCornerShape(28.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Charcoal)
                            ) {
                                Text(
                                    text = "PROCEED TO CHECKOUT",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = Gold
                                )
                            }
                        }
                    }
                }
            },
            dataEmpty = {
                GMVTEmptyView(
                    primaryText = stringResource(R.string.cart_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun CartItemRow(
    item: GMVTCartItemUiState,
    onPlus: () -> Unit,
    onMinus: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.productImageUrl,
                contentDescription = item.productTitle,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(BorderGray)
            )
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.productTitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Charcoal,
                    maxLines = 2
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "£${String.format("%.2f", item.productPrice)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Gold
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(onClick = onMinus, modifier = Modifier.size(32.dp)) {
                    Icon(
                        imageVector = if (item.quantity <= 1) Icons.Outlined.Delete else Icons.Outlined.Remove,
                        contentDescription = stringResource(R.string.decrease_quantity_icon_description),
                        tint = if (item.quantity <= 1) MutedGray else Charcoal,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Text(
                    text = "${item.quantity}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Charcoal
                )
                IconButton(onClick = onPlus, modifier = Modifier.size(32.dp)) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = stringResource(R.string.increase_quantity_icon_description),
                        tint = Charcoal,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
