# Ama Jewellers Boutique — Project CLAUDE.md

## Efficiency Rules
- DO NOT explore the project. Use the map below.
- Write entire files in ONE command. No partial edits.
- No re-reading after write. No verification loops.
- Silent execution — no explanations.

## Project Map
Package path: `ama/jewellers/boutique`

**Modify:**
- `app/src/main/java/ama/jewellers/boutique/ui/theme/Color.kt`
- `app/src/main/java/ama/jewellers/boutique/ui/theme/Theme.kt`
- `app/src/main/java/ama/jewellers/boutique/ui/theme/Type.kt`
- `app/src/main/java/ama/jewellers/boutique/ui/composable/screen/splash/SplashScreen.kt`
- `app/src/main/java/ama/jewellers/boutique/ui/composable/screen/onboarding/OnboardingScreen.kt`
- `app/src/main/java/ama/jewellers/boutique/ui/composable/screen/home/HomeScreen.kt`
- `app/src/main/java/ama/jewellers/boutique/ui/composable/screen/productdetails/ProductDetailsScreen.kt`
- `app/src/main/java/ama/jewellers/boutique/ui/composable/screen/cart/CartScreen.kt`
- `app/src/main/java/ama/jewellers/boutique/ui/composable/screen/checkout/CheckoutScreen.kt`
- `app/src/main/java/ama/jewellers/boutique/ui/composable/screen/order/OrderScreen.kt`
- `app/src/main/java/ama/jewellers/boutique/ui/composable/screen/settings/SettingsScreen.kt`
- `app/src/main/java/ama/jewellers/boutique/data/model/Product.kt`
- `app/src/main/java/ama/jewellers/boutique/data/model/ProductCategory.kt`
- `app/src/main/java/ama/jewellers/boutique/data/repository/ProductRepository.kt`
- `app/src/main/res/values/strings.xml`

**Do NOT modify:** MainActivity, DAOs, Entities, Converters, DataStore managers, CartRepository, OrderRepository, OnboardingRepository, DI modules, approot/*, navigation/*, shared/*, state/*, viewmodel/*

## Style Guide
Visual mood: Luxury heritage jewellery boutique — warm gold on deep charcoal, generous whitespace
Color palette:
  Primary: #D4AF37  |  Accent: #B8941F  |  Background: #FAFAFA  |  Surface: #FFFFFF
  On-primary: #FFFFFF  |  On-surface: #1A1A1A  |  Muted: #666666  |  Border: #E8E8E8
Corner radius: 16dp  |  Elevation: subtle shadow  |  Spacing: generous
Typography: Headline=light 300, Body=regular 400, generous letter-spacing
Buttons: charcoal bg, white text, pill-shaped  |  Cards: white bg, 1dp border, hover shadow  |  Icons: outlined
Images: product photography, editorial style
Feel: Sophisticated curation of fine timepieces and exquisite jewellery. Exclusive, timeless.
