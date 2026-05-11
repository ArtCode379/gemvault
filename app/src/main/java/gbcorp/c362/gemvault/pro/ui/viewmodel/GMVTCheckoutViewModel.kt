package gbcorp.c362.gemvault.pro.ui.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gbcorp.c362.gemvault.pro.data.entity.GMVTOrderEntity
import gbcorp.c362.gemvault.pro.data.repository.GMVTCartRepository
import gbcorp.c362.gemvault.pro.data.repository.GMVTOrderRepository
import gbcorp.c362.gemvault.pro.data.repository.GMVTProductRepository
import gbcorp.c362.gemvault.pro.ui.state.GMVTDataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class GMVTCheckoutViewModel(
    private val cartRepository: GMVTCartRepository,
    private val productRepository: GMVTProductRepository,
    private val orderRepository: GMVTOrderRepository,
) : ViewModel() {
    private val _orderState = MutableStateFlow<GMVTDataUiState<GMVTOrderEntity>>(GMVTDataUiState.Empty)
    val orderState: StateFlow<GMVTDataUiState<GMVTOrderEntity>>
        get() = _orderState.asStateFlow()

    private val _emailInvalidState = MutableStateFlow(false)
    val emailInvalidState: StateFlow<Boolean>
        get() = _emailInvalidState.asStateFlow()

    var customerFirstName by mutableStateOf("")
        private set

    var customerLastName by mutableStateOf("")
        private set

    var customerEmail by mutableStateOf("")
        private set

    fun updateCustomerFirstName(input: String) {
        customerFirstName = input
    }

    fun updateCustomerLastName(input: String) {
        customerLastName = input
    }

    fun updateCustomerEmail(input: String) {
        customerEmail = input
        if (_emailInvalidState.value) {
            _emailInvalidState.value = false
        }
    }

    fun placeOrder() {
        viewModelScope.launch {
            if (isEmailValid()) {
                _emailInvalidState.update { false }
                val order = GMVTOrderEntity(
                    orderNumber = generateOrderNumber(),
                    description = formOrderDescription(),
                    customerFirstName = customerFirstName,
                    customerLastName = customerLastName,
                    customerEmail = customerEmail,
                    price = calculateOrderPrice(),
                    timestamp = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                )

                orderRepository.save(order)
                cartRepository.deleteAll()
                _orderState.update { GMVTDataUiState.Populated(data = order) }
            } else {
                _emailInvalidState.update { true }
            }
        }
    }

    private fun isEmailValid(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(customerEmail).matches()
    }

    private fun generateOrderNumber(): String {
        val chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        return (1..8)
            .map { chars.random() }
            .joinToString("")
    }

    private suspend fun formOrderDescription(): String {
        return cartRepository.getAll().joinToString(", ") { cartItem ->
            val product = productRepository.getById(cartItem.id)
            "${product?.title} x ${cartItem.quantity}"
        }
    }

    private suspend fun calculateOrderPrice(): Double {
        return cartRepository.getAll().sumOf { cartItem ->
            val product = productRepository.getById(cartItem.id)
            val price = product?.price ?: 0.0
            cartItem.quantity * price
        }
    }
}