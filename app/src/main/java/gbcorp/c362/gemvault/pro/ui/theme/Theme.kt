package gbcorp.c362.gemvault.pro.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = EmeraldDeep,
    onPrimary = PureWhite,
    primaryContainer = EmeraldContainer,
    onPrimaryContainer = OnEmeraldContainer,
    secondary = EmeraldMedium,
    onSecondary = PureWhite,
    secondaryContainer = MintVariant,
    onSecondaryContainer = EmeraldDark,
    tertiary = SlateTertiary,
    onTertiary = PureWhite,
    background = IvoryBackground,
    onBackground = GemOnSurface,
    surface = PureWhite,
    onSurface = GemOnSurface,
    surfaceVariant = MintVariant,
    onSurfaceVariant = GreenMuted,
    outline = GreenBorder,
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
