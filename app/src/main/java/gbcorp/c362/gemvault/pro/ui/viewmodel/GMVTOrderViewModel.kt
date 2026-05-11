package gbcorp.c362.gemvault.pro.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gbcorp.c362.gemvault.pro.data.entity.GMVTOrderEntity
import gbcorp.c362.gemvault.pro.data.repository.GMVTOrderRepository
import gbcorp.c362.gemvault.pro.ui.state.GMVTDataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GMVTOrderViewModel(
    private val orderRepository: GMVTOrderRepository,
) : ViewModel() {
    private val _ordersState =
        MutableStateFlow<GMVTDataUiState<List<GMVTOrderEntity>>>(GMVTDataUiState.Initial)
    val ordersState: StateFlow<GMVTDataUiState<List<GMVTOrderEntity>>>
        get() = _ordersState.asStateFlow()

    init {
        observeOrders()
    }

    private fun observeOrders() {
        viewModelScope.launch {
            orderRepository.observeAll().collect { orders ->
                _ordersState.update { GMVTDataUiState.from(orders) }
            }
        }
    }

    fun deleteOrder(orderNumber: String) {
        viewModelScope.launch {
            orderRepository.deleteByNumber(orderNumber)
        }
    }
}