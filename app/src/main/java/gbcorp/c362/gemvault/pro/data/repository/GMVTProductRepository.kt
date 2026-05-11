package gbcorp.c362.gemvault.pro.data.repository

import gbcorp.c362.gemvault.pro.data.model.Product
import gbcorp.c362.gemvault.pro.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GMVTProductRepository {
    private val products: List<Product> = listOf(
        Product(
            id = 1,
            title = "Royal Oak GMVT Watch",
            description = "An extraordinary timepiece featuring an open-worked skeleton dial that reveals the intricate mechanical movement within. Swiss-made automatic caliber with 40-hour power reserve, housed in a polished 18k gold case. The epitome of horological artistry.",
            category = ProductCategory.WATCHES,
            price = 4850.00,
            imageUrl = "https://gandgtimepieces.com/cdn/shop/files/C218980E-6201-4AD6-833C-199BC3B2B0A8.jpg"
        ),
        Product(
            id = 2,
            title = "Diamond Solitaire Ring",
            description = "A timeless 1.2-carat round brilliant diamond set in a classic 18k white gold prong setting. GIA-certified D-colour, VS1 clarity. Each diamond is hand-selected for exceptional fire and brilliance. Perfect for engagements and lifelong milestones.",
            category = ProductCategory.RINGS,
            price = 6200.00,
            imageUrl = "https://d34qiagx43sg99.cloudfront.net/1035096-alt1-1490.jpg"
        ),
        Product(
            id = 3,
            title = "Pearl Strand Necklace",
            description = "An elegant 18-inch strand of AAA-grade Akoya cultured pearls, each ranging from 7–7.5mm in diameter. Uniform luster and flawless surface make this a treasured heirloom. Secured by an 18k gold diamond-set clasp.",
            category = ProductCategory.NECKLACES,
            price = 1850.00,
            imageUrl = "https://images-cdn.ubuy.co.in/66b795d43c97db761f4a6268-the-pearl-source-real-pearl-necklace-for.jpg"
        ),
        Product(
            id = 4,
            title = "Sapphire Drop Earrings",
            description = "Stunning oval Ceylon sapphires totalling 3.4 carats, surrounded by a halo of pavé-set diamonds in 18k white gold. The rich cornflower blue stones are complemented by the sparkle of 48 brilliant-cut diamonds. A masterpiece for formal occasions.",
            category = ProductCategory.EARRINGS,
            price = 3400.00,
            imageUrl = "https://images.unsplash.com/photo-1535632066927-ab7c9ab60908?auto=format&fit=crop&w=800&q=80"
        ),
        Product(
            id = 5,
            title = "Gold Tennis Bracelet",
            description = "A classic 7-inch tennis bracelet featuring 52 round brilliant diamonds, totalling 5.0 carats, set in a continuous line of 18k yellow gold. Each stone is individually secured with a four-prong setting for maximum security and brilliance.",
            category = ProductCategory.BRACELETS,
            price = 7900.00,
            imageUrl = "https://image.flawlessfinejewelry.com/wp-content/uploads/2022/12/Bra01_YG.jpg"
        ),
        Product(
            id = 6,
            title = "Chronograph Sports Watch",
            description = "A precision Swiss chronograph with a 42mm stainless steel case, sapphire crystal, and 300m water resistance. Features a tachymeter bezel, three sub-dials and a date window. Combines sportive functionality with refined aesthetic.",
            category = ProductCategory.WATCHES,
            price = 2750.00,
            imageUrl = "https://anthonyjameswatches.com/cdn/shop/files/AJ012N_4.jpg?v=1745523620&width=1946"
        ),
        Product(
            id = 7,
            title = "Men's Cufflinks Set",
            description = "Refined 18k gold cufflinks featuring hand-engraved geometric patterns inspired by Art Deco architecture. Each piece is individually hand-finished by master craftsmen. Presented in a luxury leather presentation box — a distinguished gift for the discerning gentleman.",
            category = ProductCategory.MENS,
            price = 680.00,
            imageUrl = "https://m.media-amazon.com/images/I/61A3mlA2xNL._AC_UL960_QL65_.jpg"
        ),
        Product(
            id = 8,
            title = "Emerald Cluster Ring",
            description = "An opulent ring featuring a 2.1-carat Colombian emerald of deep vivid green, surrounded by a double halo of round brilliant diamonds set in 18k yellow gold. A bold statement of colour and craftsmanship from our signature Heritage collection.",
            category = ProductCategory.RINGS,
            price = 5100.00,
            imageUrl = "https://i.pinimg.com/originals/4c/1f/d0/4c1fd09b12e63002682f34247f01582e.jpg"
        ),
        Product(
            id = 9,
            title = "Diamond Pendant Necklace",
            description = "A delicate 18k white gold pendant suspending a pear-shaped diamond of 0.85 carats on a fine 16-inch chain. The asymmetric cut catches light beautifully, creating a graceful dance of brilliance with every movement.",
            category = ProductCategory.NECKLACES,
            price = 2200.00,
            imageUrl = "https://cdn11.bigcommerce.com/s-y73h7mn55y/images/stencil/1280x1280/products/95322/34041/c14622%20front__97576.1756930689.jpg?c=2"
        ),
        Product(
            id = 10,
            title = "Ruby Eternity Band",
            description = "A full eternity band set with 18 oval rubies and 18 brilliant-cut diamonds alternating in 18k white gold. Burmese rubies of deep pigeon-blood red, totalling 2.8 carats. A celebration of love and enduring commitment.",
            category = ProductCategory.RINGS,
            price = 3800.00,
            imageUrl = "https://www.baroquejewellery.com/wp-content/uploads/2024/08/Eternity-Fishtail-18R-with-rubies-EXPORT-3.jpg"
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
