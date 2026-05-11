package gbcorp.c362.gemvault.pro.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gbcorp.c362.gemvault.pro.data.repository.GMVTCartRepository
import gbcorp.c362.gemvault.pro.data.repository.GMVTProductRepository
import gbcorp.c362.gemvault.pro.ui.state.GMVTCartItemUiState
import gbcorp.c362.gemvault.pro.ui.state.GMVTDataUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class GMVTCartViewModel(
    private val cartRepository: GMVTCartRepository,
    private val productRepository: GMVTProductRepository,
) : ViewModel() {
    private val _cartItemsState =
        MutableStateFlow<GMVTDataUiState<List<GMVTCartItemUiState>>>(GMVTDataUiState.Initial)
    val cartItemsState: StateFlow<GMVTDataUiState<List<GMVTCartItemUiState>>>
        get() = _cartItemsState.asStateFlow()

    private val _totalPrice = MutableStateFlow<Double>(0.0)
    val totalPrice: StateFlow<Double>
        get() = _totalPrice.asStateFlow()

    init {
        observeCartItems()
    }

    private fun observeCartItems() {
        viewModelScope.launch {
            combine(
                cartRepository.observeAll(),
                productRepository.observeAll()
            ) { cartItems, products ->

                val productsMap = products.associateBy { it.id }

                val uiItems = cartItems.mapNotNull { cartItem ->
                    productsMap[cartItem.id]?.let { product ->
                        GMVTCartItemUiState(
                            productId = product.id,
                            productTitle = product.title,
                            productPrice = product.price,
                            quantity = cartItem.quantity,
                            productImageUrl = product.imageUrl,
                        )
                    }
                }

                if (uiItems.isEmpty()) GMVTDataUiState.Empty
                else {
                    _totalPrice.value = calculateTotalPrice(uiItems)
                    GMVTDataUiState.Populated(uiItems)
                }
            }.collect { state ->
                _cartItemsState.value = state
            }
        }
    }

    fun incrementProductInCart(productId: Int) {
        viewModelScope.launch {
            cartRepository.incrementQuantity(productId)
        }
    }

    fun deleteFromCart(productId: Int) {
        viewModelScope.launch {
            cartRepository.deleteById(productId)
        }
    }

    fun decrementItemInCart(productId: Int) {
        viewModelScope.launch {
            productRepository.getById(productId)?.let { product ->
                cartRepository.decrementProductQuantityOrRemove(product)
            }
        }
    }

    private fun calculateTotalPrice(items: List<GMVTCartItemUiState>): Double = items.sumOf { item ->
        item.productPrice * item.quantity
    }
}