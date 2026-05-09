package gbcorp.c362.gemvault.pro.ui.composable.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import gbcorp.c362.gemvault.pro.ui.state.GVLTDataUiState

@Composable
fun <T> GVLTContentWrapper(
    modifier: Modifier = Modifier,
    dataState: GVLTDataUiState<T>,
    dataPopulated: @Composable (() -> Unit),
    dataEmpty: @Composable (() -> Unit),
    dataInitial: @Composable (() -> Unit) = {},
) {
    Box(modifier = modifier) {
        when (dataState) {
            is GVLTDataUiState.Populated -> {
                dataPopulated()
            }

            GVLTDataUiState.Empty -> {
                dataEmpty()
            }

            GVLTDataUiState.Initial -> {
                dataInitial()
            }
        }
    }
}