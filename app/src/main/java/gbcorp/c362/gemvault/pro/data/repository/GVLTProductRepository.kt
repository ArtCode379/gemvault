package gbcorp.c362.gemvault.pro.data.repository

import gbcorp.c362.gemvault.pro.data.model.Product
import gbcorp.c362.gemvault.pro.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GVLTProductRepository {
    private val products: List<Product> = listOf(
        Product(
            id = 1,
            title = "Celestial Tourbillon Watch",
            description = "A hand-wound tourbillon movement in a platinum case with a hand-guilloché silver dial. Limited to 50 pieces worldwide. Features a 72-hour power reserve and double-axis cage rotating at one revolution per minute.",
            category = ProductCategory.WATCHES,
            price = 6400.00,
            imageUrl = "https://images.unsplash.com/photo-1523170335258-f5ed11844a49?w=800"
        ),
        Product(
            id = 2,
            title = "Cushion Sapphire Solitaire",
            description = "A 2.4-carat unheated Ceylon sapphire of velvety royal blue, set in a hand-fabricated platinum crown with tapered baguette diamond shoulders. AGL-certified with no evidence of heat treatment.",
            category = ProductCategory.RINGS,
            price = 8750.00,
            imageUrl = "https://images.unsplash.com/photo-1605100804763-247f67b3557e?w=800"
        ),
        Product(
            id = 3,
            title = "Graduated Tahitian Pearl Strand",
            description = "An 18-inch graduated strand of natural Tahitian pearls, ranging from 9–12mm, displaying deep peacock overtones. Each pearl is individually knotted on silk. Clasp set in 18k white gold with pavé diamonds.",
            category = ProductCategory.NECKLACES,
            price = 3100.00,
            imageUrl = "https://images.unsplash.com/photo-1515562141207-7a88fb7ce338?w=800"
        ),
        Product(
            id = 4,
            title = "Alexandrite Halo Earrings",
            description = "Genuine Brazilian alexandrite centre stones totalling 1.8 carats, surrounded by a double halo of brilliant-cut diamonds in 18k white gold. The alexandrite shifts from vivid teal to raspberry under incandescent light.",
            category = ProductCategory.EARRINGS,
            price = 5200.00,
            imageUrl = "https://images.unsplash.com/photo-1535632066927-ab7c9ab60908?w=800"
        ),
        Product(
            id = 5,
            title = "Pavé Diamond Flex Bracelet",
            description = "A flexible open-cuff bracelet set with 7.2 carats of pavé-set round brilliants in 18k white gold. The articulated links create a supple fit that conforms to the wrist. A contemporary take on a timeless estate silhouette.",
            category = ProductCategory.BRACELETS,
            price = 9200.00,
            imageUrl = "https://images.unsplash.com/photo-1573408301185-9519f94815f8?w=800"
        ),
        Product(
            id = 6,
            title = "Platinum Dress Watch",
            description = "An ultra-thin manual-wound movement in a 38mm solid platinum case. Lacquered white dial with applied gold baton indices. A refined choice for the modern connoisseur who values restraint and horological excellence.",
            category = ProductCategory.WATCHES,
            price = 4100.00,
            imageUrl = "https://images.unsplash.com/photo-1594534475808-b18fc33b045e?w=800"
        ),
        Product(
            id = 7,
            title = "Signet Ring with Intaglio Crest",
            description = "A bespoke 18k yellow gold signet ring featuring a hand-engraved intaglio crest on a 17mm oval table. Modelled after Edwardian estate ring templates. Presented in a burled walnut keepsake box.",
            category = ProductCategory.MENS,
            price = 1480.00,
            imageUrl = "https://images.unsplash.com/photo-1611591437281-460bfbe1220a?w=800"
        ),
        Product(
            id = 8,
            title = "Paraiba Tourmaline Cocktail Ring",
            description = "An electric-blue Paraiba tourmaline of 3.6 carats from Mozambique, mounted in an asymmetric 18k yellow gold setting with a split diamond-set shank. One of the rarest gems on earth, prized for its neon luminescence.",
            category = ProductCategory.RINGS,
            price = 12800.00,
            imageUrl = "https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?w=800"
        ),
        Product(
            id = 9,
            title = "Emerald-Cut Diamond Rivière",
            description = "A rivière necklace set with 15 emerald-cut diamonds totalling 9.3 carats, all D–F colour and VS clarity, set in open-back 18k white gold collets on a delicate chain. Each stone precisely matched for symmetry and brilliance.",
            category = ProductCategory.NECKLACES,
            price = 18500.00,
            imageUrl = "https://images.unsplash.com/photo-1547996160-81dfa63595aa?w=800"
        ),
        Product(
            id = 10,
            title = "Burma Ruby Eternity Ring",
            description = "Twenty oval Burmese rubies of pigeon-blood red alternating with round brilliant diamonds set in a classic milgrain 18k white gold eternity band. Total ruby weight 3.2 carats. Accompanied by Gübelin laboratory report.",
            category = ProductCategory.RINGS,
            price = 7600.00,
            imageUrl = "https://images.unsplash.com/photo-1625591340248-14acc7c9e7c6?w=800"
        ),
    )

    fun observeById(id: Int): Flow<Product?> {
        val item = products.find { it.id == id }
        return flowOf(item)
    }

    fun getById(id: Int): Product? {
        return products.find { it.id == id }
    }

    fun observeAll(): Flow<List<Product>> {
        return flowOf(products)
    }
}
