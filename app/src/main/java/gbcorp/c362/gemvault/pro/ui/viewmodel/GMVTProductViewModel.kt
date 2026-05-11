package gbcorp.c362.gemvault.pro.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gbcorp.c362.gemvault.pro.data.model.Product
import gbcorp.c362.gemvault.pro.data.repository.GMVTCartRepository
import gbcorp.c362.gemvault.pro.data.repository.GMVTProductRepository
import gbcorp.c362.gemvault.pro.ui.state.GMVTDataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GMVTProductViewModel(
    private val productRepository: GMVTProductRepository,
    private val cartRepository: GMVTCartRepository,
) : ViewModel() {
    private val _productsState = MutableStateFlow<GMVTDataUiState<List<Product>>>(GMVTDataUiState.Initial)
    val productsState: StateFlow<GMVTDataUiState<List<Product>>>
        get() = _productsState.asStateFlow()

    init {
        observeProducts()
    }

    private fun observeProducts() {
        viewModelScope.launch {
            productRepository.observeAll().collect { products ->
                _productsState.update { GMVTDataUiState.from(products) }
            }
        }
    }

    fun addToCart(productId: Int) {
        viewModelScope.launch {
            val products = _productsState.value
            if (products is GMVTDataUiState.Populated) {
                val product = products.data.find { it.id == productId } ?: return@launch
                cartRepository.incrementProductQuantityOrAdd(product)
            }
        }
    }
}