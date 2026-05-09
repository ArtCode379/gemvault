package gbcorp.c362.gemvault.pro.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gbcorp.c362.gemvault.pro.data.model.Product
import gbcorp.c362.gemvault.pro.data.repository.GVLTCartRepository
import gbcorp.c362.gemvault.pro.data.repository.GVLTProductRepository
import gbcorp.c362.gemvault.pro.ui.state.GVLTDataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GVLTProductViewModel(
    private val productRepository: GVLTProductRepository,
    private val cartRepository: GVLTCartRepository,
) : ViewModel() {
    private val _productsState = MutableStateFlow<GVLTDataUiState<List<Product>>>(GVLTDataUiState.Initial)
    val productsState: StateFlow<GVLTDataUiState<List<Product>>>
        get() = _productsState.asStateFlow()

    init {
        observeProducts()
    }

    private fun observeProducts() {
        viewModelScope.launch {
            productRepository.observeAll().collect { products ->
                _productsState.update { GVLTDataUiState.from(products) }
            }
        }
    }

    fun addToCart(productId: Int) {
        viewModelScope.launch {
            val products = _productsState.value
            if (products is GVLTDataUiState.Populated) {
                val product = products.data.find { it.id == productId } ?: return@launch
                cartRepository.incrementProductQuantityOrAdd(product)
            }
        }
    }
}