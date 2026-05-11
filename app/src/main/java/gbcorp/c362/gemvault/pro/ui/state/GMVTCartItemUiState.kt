package gbcorp.c362.gemvault.pro.ui.state

data class GMVTCartItemUiState(
    val productId: Int,
    val productTitle: String,
    val productPrice: Double,
    val quantity: Int,
    val productImageUrl: String? = null,
)