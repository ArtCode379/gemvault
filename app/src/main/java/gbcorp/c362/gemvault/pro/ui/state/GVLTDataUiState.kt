package gbcorp.c362.gemvault.pro.ui.state

sealed class GVLTDataUiState<out T> {

    object Initial : GVLTDataUiState<Nothing>()
    object Empty : GVLTDataUiState<Nothing>()

    data class Populated<T : Any>(val data: T) : GVLTDataUiState<T>()

    companion object {

        fun <T> from(list: List<T>) = if (list.isEmpty()) Empty else Populated(list)

        fun <T : Any> from(data: T?): GVLTDataUiState<T> = if (data == null) Empty else Populated(data)
    }
}