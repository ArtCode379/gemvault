package gbcorp.c362.gemvault.pro.ui.composable.screen.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import gbcorp.c362.gemvault.pro.R
import gbcorp.c362.gemvault.pro.data.entity.GMVTOrderEntity
import gbcorp.c362.gemvault.pro.ui.theme.Charcoal
import gbcorp.c362.gemvault.pro.ui.theme.Gold
import gbcorp.c362.gemvault.pro.ui.theme.MutedGray

@Composable
fun CheckoutDialog(
    order: GMVTOrderEntity,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                text = stringResource(R.string.checkout_dialog_title),
                style = MaterialTheme.typography.headlineMedium,
                color = Charcoal,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Column {
                Text(
                    text = stringResource(R.string.checkout_dialog_order_number, order.orderNumber),
                    style = MaterialTheme.typography.titleMedium,
                    color = Gold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.checkout_dialog_processing_message),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MutedGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Charcoal)
            ) {
                Text(
                    text = "VIEW MY ORDERS",
                    style = MaterialTheme.typography.labelLarge,
                    color = Gold
                )
            }
        },
        shape = RoundedCornerShape(16.dp),
        containerColor = MaterialTheme.colorScheme.surface
    )
}
