package gbcorp.c362.gemvault.pro.data.model

import androidx.annotation.StringRes
import gbcorp.c362.gemvault.pro.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    WATCHES(R.string.category_watches),
    RINGS(R.string.category_rings),
    NECKLACES(R.string.category_necklaces),
    EARRINGS(R.string.category_earrings),
    BRACELETS(R.string.category_bracelets),
    MENS(R.string.category_mens),
}
