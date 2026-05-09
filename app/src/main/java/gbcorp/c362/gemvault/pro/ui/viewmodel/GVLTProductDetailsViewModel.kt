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

class GVLTProductDetailsViewModel(
    private val productRepository: GVLTProductRepository,
    private val cartRepository: GVLTCartRepository,
) : ViewModel() {
    private val _productDetailState =
        MutableStateFlow<GVLTDataUiState<Product>>(GVLTDataUiState.Initial)
    val productDetailsState: StateFlow<GVLTDataUiState<Product>>
        get() = _productDetailState.asStateFlow()

    fun observeProductDetails(productId: Int) {
        viewModelScope.launch {
            productRepository.observeById(productId).collect { product ->
                _productDetailState.update {
                    GVLTDataUiState.from(product)
                }
            }
        }
    }

    fun addProductToCart() {
        viewModelScope.launch {
            val state = _productDetailState.value
            if (state is GVLTDataUiState.Populated) {
                cartRepository.incrementProductQuantityOrAdd(state.data)
            }
        }
    }
}