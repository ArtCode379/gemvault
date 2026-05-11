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

class GMVTProductDetailsViewModel(
    private val productRepository: GMVTProductRepository,
    private val cartRepository: GMVTCartRepository,
) : ViewModel() {
    private val _productDetailState =
        MutableStateFlow<GMVTDataUiState<Product>>(GMVTDataUiState.Initial)
    val productDetailsState: StateFlow<GMVTDataUiState<Product>>
        get() = _productDetailState.asStateFlow()

    fun observeProductDetails(productId: Int) {
        viewModelScope.launch {
            productRepository.observeById(productId).collect { product ->
                _productDetailState.update {
                    GMVTDataUiState.from(product)
                }
            }
        }
    }

    fun addProductToCart() {
        viewModelScope.launch {
            val state = _productDetailState.value
            if (state is GMVTDataUiState.Populated) {
                cartRepository.incrementProductQuantityOrAdd(state.data)
            }
        }
    }
}