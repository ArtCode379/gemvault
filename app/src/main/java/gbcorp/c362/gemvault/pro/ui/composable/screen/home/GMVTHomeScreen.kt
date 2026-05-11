package gbcorp.c362.gemvault.pro.ui.composable.screen.home

import gbcorp.c362.gemvault.pro.R
import gbcorp.c362.gemvault.pro.data.model.Product
import gbcorp.c362.gemvault.pro.data.model.ProductCategory
import gbcorp.c362.gemvault.pro.ui.composable.shared.GMVTContentWrapper
import gbcorp.c362.gemvault.pro.ui.composable.shared.GMVTEmptyView
import gbcorp.c362.gemvault.pro.ui.state.GMVTDataUiState
import gbcorp.c362.gemvault.pro.ui.theme.BackgroundLight
import gbcorp.c362.gemvault.pro.ui.theme.BorderGray
import gbcorp.c362.gemvault.pro.ui.theme.Charcoal
import gbcorp.c362.gemvault.pro.ui.theme.Gold
import gbcorp.c362.gemvault.pro.ui.theme.SurfaceWhite
import gbcorp.c362.gemvault.pro.ui.viewmodel.GMVTProductViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: GMVTProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val productsState by viewModel.productsState.collectAsState()

    HomeContent(
        productsState = productsState,
        modifier = modifier,
        onNavigateToProductDetails = onNavigateToProductDetails,
        onAddProductToCart = viewModel::addToCart,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    productsState: GMVTDataUiState<List<Product>>,
    modifier: Modifier = Modifier,
    onNavigateToProductDetails: (productId: Int) -> Unit,
    onAddProductToCart: (productId: Int) -> Unit,
) {
    var selectedCategory by remember { mutableStateOf<ProductCategory?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        GMVTContentWrapper(
            dataState = productsState,
            dataPopulated = {
                val products = (productsState as GMVTDataUiState.Populated).data
                val featured = products.take(4)
                val filtered = if (selectedCategory != null)
                    products.filter { it.category == selectedCategory }
                else products

                androidx.compose.foundation.lazy.grid.LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(bottom = 16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
                        FeaturedCarousel(
                            products = featured,
                            onProductClick = onNavigateToProductDetails
                        )
                    }
                    item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
                        CategoryRow(
                            selectedCategory = selectedCategory,
                            onCategorySelected = { cat ->
                                selectedCategory = if (selectedCategory == cat) null else cat
                            }
                        )
                    }
                    items(filtered) { product ->
                        ProductCard(
                            product = product,
                            onClick = { onNavigateToProductDetails(product.id) }
                        )
                    }
                }
            },
            dataEmpty = {
                GMVTEmptyView(
                    primaryText = stringResource(R.string.products_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun FeaturedCarousel(
    products: List<Product>,
    onProductClick: (Int) -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { products.size })

    LaunchedEffect(Unit) {
        while (true) {
            delay(4000)
            val next = (pagerState.currentPage + 1) % products.size
            pagerState.animateScrollToPage(next)
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) { page ->
            val product = products[page]
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onProductClick(product.id) }
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Charcoal.copy(alpha = 0.35f))
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Text(
                        text = "£${String.format("%.2f", product.price)}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Gold
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(products.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(if (pagerState.currentPage == index) 8.dp else 6.dp)
                        .clip(CircleShape)
                        .background(if (pagerState.currentPage == index) Gold else BorderGray)
                )
            }
        }
    }
}

@Composable
private fun CategoryRow(
    selectedCategory: ProductCategory?,
    onCategorySelected: (ProductCategory) -> Unit,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(ProductCategory.entries) { category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = { onCategorySelected(category) },
                label = {
                    Text(
                        text = stringResource(category.titleRes),
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Gold,
                    selectedLabelColor = Charcoal,
                    containerColor = SurfaceWhite,
                    labelColor = Charcoal
                ),
                border = FilterChipDefaults.filterChipBorder(
                    enabled = true,
                    selected = selectedCategory == category,
                    borderColor = BorderGray,
                    selectedBorderColor = Gold
                )
            )
        }
    }
}

@Composable
private fun ProductCard(
    product: Product,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = stringResource(product.category.titleRes),
                    style = MaterialTheme.typography.labelSmall,
                    color = Gold
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Charcoal,
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "£${String.format("%.2f", product.price)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Gold
                )
            }
        }
    }
}
