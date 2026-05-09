package gbcorp.c362.gemvault.pro.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gbcorp.c362.gemvault.pro.data.entity.GVLTOrderEntity
import gbcorp.c362.gemvault.pro.data.repository.GVLTOrderRepository
import gbcorp.c362.gemvault.pro.ui.state.GVLTDataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GVLTOrderViewModel(
    private val orderRepository: GVLTOrderRepository,
) : ViewModel() {
    private val _ordersState =
        MutableStateFlow<GVLTDataUiState<List<GVLTOrderEntity>>>(GVLTDataUiState.Initial)
    val ordersState: StateFlow<GVLTDataUiState<List<GVLTOrderEntity>>>
        get() = _ordersState.asStateFlow()

    init {
        observeOrders()
    }

    private fun observeOrders() {
        viewModelScope.launch {
            orderRepository.observeAll().collect { orders ->
                _ordersState.update { GVLTDataUiState.from(orders) }
            }
        }
    }

    fun deleteOrder(orderNumber: String) {
        viewModelScope.launch {
            orderRepository.deleteByNumber(orderNumber)
        }
    }
}