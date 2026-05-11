package gbcorp.c362.gemvault.pro.ui.composable.screen.settings

import gbcorp.c362.gemvault.pro.R
import gbcorp.c362.gemvault.pro.ui.theme.BackgroundLight
import gbcorp.c362.gemvault.pro.ui.theme.BorderGray
import gbcorp.c362.gemvault.pro.ui.theme.Charcoal
import gbcorp.c362.gemvault.pro.ui.theme.Gold
import gbcorp.c362.gemvault.pro.ui.theme.MutedGray
import gbcorp.c362.gemvault.pro.ui.theme.SurfaceWhite
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "About",
            style = MaterialTheme.typography.labelLarge,
            color = MutedGray,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
        )
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column {
                SettingsRow(
                    icon = Icons.Outlined.Info,
                    label = stringResource(R.string.settings_screen_company_label),
                    value = "AMA JEWELLERS LTD"
                )
                HorizontalDivider(
                    modifier = Modifier.padding(start = 56.dp),
                    color = BorderGray
                )
                SettingsRow(
                    icon = Icons.Outlined.Language,
                    label = stringResource(R.string.settings_screen_version_label),
                    value = stringResource(R.string.app_version)
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Support",
            style = MaterialTheme.typography.labelLarge,
            color = MutedGray,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
        )
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            SettingsRowClickable(
                icon = Icons.Outlined.SupportAgent,
                label = stringResource(R.string.settings_screen_customer_support_label),
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://aamajewellers.uk"))
                    context.startActivity(intent)
                }
            )
        }

        Spacer(Modifier.height(40.dp))

        Text(
            text = "Ama Jewellers Boutique",
            style = MaterialTheme.typography.bodyMedium,
            color = BorderGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Curators of Fine Timepieces & Exquisite Jewellery",
            style = MaterialTheme.typography.labelSmall,
            color = BorderGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
private fun SettingsRow(
    icon: ImageVector,
    label: String,
    value: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Gold,
            modifier = Modifier.size(22.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = Charcoal
            )
        }
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MutedGray
        )
    }
}

@Composable
private fun SettingsRowClickable(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Gold,
            modifier = Modifier.size(22.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Charcoal,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = MutedGray,
            modifier = Modifier.size(20.dp)
        )
    }
}
