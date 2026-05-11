package gbcorp.c362.gemvault.pro.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Gold,
    onPrimary = OnGold,
    secondary = DarkGold,
    onSecondary = OnGold,
    tertiary = MutedGray,
    background = BackgroundLight,
    onBackground = Charcoal,
    surface = SurfaceWhite,
    onSurface = Charcoal,
    surfaceVariant = LightGray,
    onSurfaceVariant = MutedGray,
    outline = BorderGray,
)

@Composable
fun ProductAppGMVTTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
