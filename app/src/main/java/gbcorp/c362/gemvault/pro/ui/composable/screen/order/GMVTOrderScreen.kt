package gbcorp.c362.gemvault.pro.ui.composable.screen.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import gbcorp.c362.gemvault.pro.R
import gbcorp.c362.gemvault.pro.data.entity.GMVTOrderEntity
import gbcorp.c362.gemvault.pro.ui.composable.shared.GMVTContentWrapper
import gbcorp.c362.gemvault.pro.ui.composable.shared.GMVTEmptyView
import gbcorp.c362.gemvault.pro.ui.state.GMVTDataUiState
import gbcorp.c362.gemvault.pro.ui.theme.BackgroundLight
import gbcorp.c362.gemvault.pro.ui.theme.Charcoal
import gbcorp.c362.gemvault.pro.ui.theme.Gold
import gbcorp.c362.gemvault.pro.ui.theme.MutedGray
import gbcorp.c362.gemvault.pro.ui.theme.SurfaceWhite
import gbcorp.c362.gemvault.pro.ui.viewmodel.GMVTOrderViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: GMVTOrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: GMVTDataUiState<List<GMVTOrderEntity>>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        GMVTContentWrapper(
            dataState = ordersState,
            dataPopulated = {
                val orders = (ordersState as GMVTDataUiState.Populated).data
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
                ) {
                    items(orders.sortedByDescending { it.timestamp }) { order ->
                        OrderCard(order = order)
                    }
                }
            },
            dataEmpty = {
                GMVTEmptyView(
                    primaryText = stringResource(R.string.orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun OrderCard(order: GMVTOrderEntity) {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.order_number, order.orderNumber),
                    style = MaterialTheme.typography.titleMedium,
                    color = Charcoal
                )
                SuggestionChip(
                    onClick = {},
                    label = {
                        Text(
                            text = "Reserved",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF2E7D32)
                        )
                    },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = Color(0xFFE8F5E9)
                    ),
                    border = SuggestionChipDefaults.suggestionChipBorder(
                        enabled = true,
                        borderColor = Color(0xFF81C784)
                    )
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = order.timestamp.format(formatter),
                style = MaterialTheme.typography.bodyMedium,
                color = MutedGray
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.order_customer, order.customerFirstName, order.customerLastName),
                style = MaterialTheme.typography.bodyMedium,
                color = MutedGray
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = order.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Charcoal,
                maxLines = 3
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Total: £${String.format("%.2f", order.price)}",
                style = MaterialTheme.typography.titleMedium,
                color = Gold
            )
        }
    }
}
