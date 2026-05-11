package gbcorp.c362.gemvault.pro.data.repository

import gbcorp.c362.gemvault.pro.data.model.Product
import gbcorp.c362.gemvault.pro.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GMVTProductRepository {
    private val products: List<Product> = listOf(
        Product(
            id = 1,
            title = "Celestial Tourbillon",
            description = "An extraordinary complication showcasing a flying tourbillon at 6 o'clock through an open sapphire dial. Swiss-made hand-wound caliber with 72-hour power reserve, encased in a polished 18k white gold 41mm case. The pinnacle of mechanical artistry from our vault collection.",
            category = ProductCategory.WATCHES,
            price = 52_400.00,
            imageUrl = "https://images.unsplash.com/photo-1523170335258-f5ed11844a49?w=800"
        ),
        Product(
            id = 2,
            title = "Vivid Ceylon Emerald Ring",
            description = "A resplendent 3.6-carat natural Ceylon emerald of intense vivid green, cradled in a cathedral-style platinum setting and encircled by 1.2 carats of brilliant-cut diamonds. Accompanied by a GRS certificate of origin confirming exceptional clarity and saturation.",
            category = ProductCategory.RINGS,
            price = 18_750.00,
            imageUrl = "https://images.unsplash.com/photo-1605100804763-247f67b3557e?w=800"
        ),
        Product(
            id = 3,
            title = "Golden South Sea Pendant",
            description = "A luminous 14mm golden South Sea pearl suspended from a hand-forged 18k yellow gold bail, set with a pavé diamond halo of 0.45 carats. Each pearl is cultured over five years in the warm waters of Indonesia, prized for its extraordinary deep lustre and satiny surface.",
            category = ProductCategory.NECKLACES,
            price = 6_900.00,
            imageUrl = "https://images.unsplash.com/photo-1515562141207-7a88fb7ce338?w=800"
        ),
        Product(
            id = 4,
            title = "Alexandrite Chandelier Drops",
            description = "Captivating colour-change alexandrite drops of 2.8 carats total — shifting from verdant emerald in daylight to deep raspberry under incandescent light. Set in 18k rose gold with cascading pavé-diamond links, each earring moves with elegant fluidity. A gemological rarity of the highest order.",
            category = ProductCategory.EARRINGS,
            price = 14_200.00,
            imageUrl = "https://images.unsplash.com/photo-1596944924616-7b38e7cfac36?w=800"
        ),
        Product(
            id = 5,
            title = "Pavé Diamond Tennis Cuff",
            description = "A bold 18k white gold cuff set with 8.4 carats of round brilliant diamonds in a seamless micro-pavé configuration. The rigid half-bangle profile ensures a perfect fit while showcasing an unbroken river of radiance across the wrist — a defining piece of wearable sculpture.",
            category = ProductCategory.BRACELETS,
            price = 23_600.00,
            imageUrl = "https://images.unsplash.com/photo-1611085583191-a3b181a88401?w=800"
        ),
        Product(
            id = 6,
            title = "Midnight Blue Chronograph",
            description = "A statement 44mm chronograph with a deep midnight blue galvanised dial, three-register layout, and polished titanium case achieving 200m water resistance. Swiss automatic column-wheel movement with vertical clutch system delivers flawless chronograph engagement. Presented on a black alligator leather strap.",
            category = ProductCategory.WATCHES,
            price = 8_950.00,
            imageUrl = "https://images.unsplash.com/photo-1587836374828-4dbafa94cf0e?w=800"
        ),
        Product(
            id = 7,
            title = "Black Onyx Cocktail Ring",
            description = "A commanding cocktail ring centred on a cushion-cut black onyx of 9 carats, flanked by tapered baguette diamonds totalling 1.8 carats in oxidised 18k gold. Designed for maximum visual impact with bold Art Deco geometry and a polished bezel setting — a true conversation piece.",
            category = ProductCategory.RINGS,
            price = 4_400.00,
            imageUrl = "https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?w=800"
        ),
        Product(
            id = 8,
            title = "Tanzanite Station Necklace",
            description = "Seven oval tanzanites of matched deep violet-blue hue — totalling 6.2 carats — are spaced at equal intervals along a fine 18k gold wheat chain. Each stone is individually bezel-set with a diamond accent, creating a constellation of colour against the skin. A collector's heirloom in the finest sense.",
            category = ProductCategory.NECKLACES,
            price = 9_200.00,
            imageUrl = "https://images.unsplash.com/photo-1573408301185-9519f7872b58?w=800"
        ),
        Product(
            id = 9,
            title = "Tahitian Pearl Drop Earrings",
            description = "Baroque Tahitian pearls of 12–13mm, displaying an iridescent peacock-green overtone, are suspended from 18k white gold hooks set with diamond trefoils. No two pearls are identical — their organic, sculpted forms lend each pair a singular and wholly unique character.",
            category = ProductCategory.EARRINGS,
            price = 5_850.00,
            imageUrl = "https://images.unsplash.com/photo-1602173574767-37ac01994b2a?w=800"
        ),
        Product(
            id = 10,
            title = "Rose Gold Charm Bangle",
            description = "An 18k rose gold rigid bangle set with twelve individually articulated charms: stars, crescent moons, and hearts, each pavé-set with pink and white diamonds. The charms move and chime gently with the wrist, making this piece as enchanting to wear as it is to behold.",
            category = ProductCategory.BRACELETS,
            price = 7_100.00,
            imageUrl = "https://images.unsplash.com/photo-1611652022419-a9419f74343d?w=800"
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
