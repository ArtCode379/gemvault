package gbcorp.c362.gemvault.pro.ui.composable.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import gbcorp.c362.gemvault.pro.ui.state.GMVTDataUiState

@Composable
fun <T> GMVTContentWrapper(
    modifier: Modifier = Modifier,
    dataState: GMVTDataUiState<T>,
    dataPopulated: @Composable (() -> Unit),
    dataEmpty: @Composable (() -> Unit),
    dataInitial: @Composable (() -> Unit) = {},
) {
    Box(modifier = modifier) {
        when (dataState) {
            is GMVTDataUiState.Populated -> {
                dataPopulated()
            }

            GMVTDataUiState.Empty -> {
                dataEmpty()
            }

            GMVTDataUiState.Initial -> {
                dataInitial()
            }
        }
    }
}