package gbcorp.c362.gemvault.pro.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gbcorp.c362.gemvault.pro.data.repository.GMVTCartRepository
import gbcorp.c362.gemvault.pro.ui.state.GMVTDataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GMVTAppViewModel(
    private val cartRepository: GMVTCartRepository,
) : ViewModel() {
    private val _cartPopulatedState =
        MutableStateFlow<GMVTDataUiState<Unit>>(GMVTDataUiState.Initial)
    val cartPopulatedState: StateFlow<GMVTDataUiState<Unit>>
        get() = _cartPopulatedState.asStateFlow()

    private val _itemsInCartState = MutableStateFlow<Int>(0)
    val itemsInCartState: StateFlow<Int>
        get() = _itemsInCartState.asStateFlow()

    init {
        observeCart()
    }

    private fun observeCart() {
        viewModelScope.launch {
            cartRepository.observeAll().collect { items ->
                _cartPopulatedState.update {
                    if (items.isNotEmpty()) {
                        GMVTDataUiState.Populated(Unit)
                    } else {
                        GMVTDataUiState.Empty
                    }
                }
                _itemsInCartState.update { items.sumOf { it.quantity } }
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            cartRepository.deleteAll()
        }
    }
}