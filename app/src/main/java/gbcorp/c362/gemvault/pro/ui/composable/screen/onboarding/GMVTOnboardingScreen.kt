package gbcorp.c362.gemvault.pro.ui.composable.screen.onboarding

import gbcorp.c362.gemvault.pro.ui.theme.BackgroundLight
import gbcorp.c362.gemvault.pro.ui.theme.BorderGray
import gbcorp.c362.gemvault.pro.ui.theme.Charcoal
import gbcorp.c362.gemvault.pro.ui.theme.Gold
import gbcorp.c362.gemvault.pro.ui.theme.MutedGray
import gbcorp.c362.gemvault.pro.ui.viewmodel.GMVTOnboardingVM
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

private data class OnboardingSlide(
    val imageUrl: String,
    val title: String,
    val description: String,
)

private val slides = listOf(
    OnboardingSlide(
        imageUrl = "https://i.pinimg.com/originals/5b/35/05/5b3505ba50d1a3f1d9e219f931870962.jpg",
        title = "Curated Fine Jewellery",
        description = "Discover an exclusive collection of handcrafted diamond rings, sapphire earrings, and pearl necklaces — each piece a testament to timeless craftsmanship."
    ),
    OnboardingSlide(
        imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjQvoEQwEHyS0Pmna8g2eOsYFcDsvAFbjqYEmVvPK7VGEVrSux3PRJEqG-yEDIm_ZV6Su2QTjK9IfxvVOe1PNwlFYxzP2EXO0Oq-hYFIkXDGAax5JnS2slJthRfz3qIy0Dmj6mij5Jl5XHHfXLdlXtjtVvs3asETejraFRnTd1B-96QgSL94Fu0mkI2/s1366/Christies_The%20Collection%20of%20a%20Lifetime.jpg",
        title = "Exceptional Timepieces",
        description = "Explore our selection of luxury Swiss watches, from elegant dress watches to precision chronographs. Heritage meets horological artistry."
    ),
    OnboardingSlide(
        imageUrl = "https://www.vintagewatchcompany.com/img/db/slideshow/slidehomepage-shop-front.jpg",
        title = "Reserve Your Piece",
        description = "Browse, select, and reserve your chosen jewellery or timepiece. Our specialists will have it waiting for you at the boutique within 24 hours."
    ),
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: GMVTOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val onboardingSetState by viewModel.onboardingSetState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { slides.size })
    val scope = rememberCoroutineScope()

    LaunchedEffect(onboardingSetState) {
        if (onboardingSetState) {
            onNavigateToHomeScreen()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundLight),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            val slide = slides[page]
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = slide.imageUrl,
                    contentDescription = slide.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = slide.title,
                        style = MaterialTheme.typography.headlineLarge,
                        color = Charcoal,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = slide.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MutedGray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // Dot indicators
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(slides.size) { index ->
                Box(
                    modifier = Modifier
                        .size(if (pagerState.currentPage == index) 10.dp else 7.dp)
                        .clip(CircleShape)
                        .background(if (pagerState.currentPage == index) Gold else BorderGray)
                )
            }
        }

        // Button
        Box(modifier = Modifier.padding(horizontal = 24.dp, vertical = 24.dp)) {
            Button(
                onClick = {
                    if (pagerState.currentPage < slides.size - 1) {
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    } else {
                        viewModel.setOnboarded()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Charcoal)
            ) {
                Text(
                    text = if (pagerState.currentPage < slides.size - 1) "NEXT" else "GET STARTED",
                    style = MaterialTheme.typography.labelLarge,
                    color = Gold
                )
            }
        }
    }
}
