package gbcorp.c362.gemvault.pro.ui.state

sealed class GMVTDataUiState<out T> {

    object Initial : GMVTDataUiState<Nothing>()
    object Empty : GMVTDataUiState<Nothing>()

    data class Populated<T : Any>(val data: T) : GMVTDataUiState<T>()

    companion object {

        fun <T> from(list: List<T>) = if (list.isEmpty()) Empty else Populated(list)

        fun <T : Any> from(data: T?): GMVTDataUiState<T> = if (data == null) Empty else Populated(data)
    }
}