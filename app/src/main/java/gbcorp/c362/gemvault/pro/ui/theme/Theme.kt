package gbcorp.c362.gemvault.pro.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = SapphireDeep,
    onPrimary = OnSapphire,
    secondary = GoldWarm,
    onSecondary = SapphireNavy,
    tertiary = SlateBlue,
    background = CreamBase,
    onBackground = InkDark,
    surface = PureWhite,
    onSurface = InkDark,
    surfaceVariant = PowderBlue,
    onSurfaceVariant = SlateBlue,
    outline = SkyMist,
    primaryContainer = SkyMist,
    onPrimaryContainer = SapphireNavy,
)

@Composable
fun ProductAppGVLTTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
