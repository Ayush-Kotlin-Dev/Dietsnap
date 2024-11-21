package com.ayush.dietsnap.presentation.screens.food_info

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ayush.dietsnap.domain.model.FoodInfo
import com.ayush.dietsnap.domain.model.NutritionInfo
import com.ayush.dietsnap.domain.model.SimilarItem
import com.ayush.dietsnap.presentation.components.ErrorScreen
import com.ayush.dietsnap.presentation.components.LoadingScreen
import com.ayush.dietsnap.presentation.components.toString
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun FoodInfoScreen(viewModel: FoodInfoViewModel = koinViewModel(), onBackClick: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.loadFoodInfo()
    }

    val context = LocalContext.current
    when (val state = uiState) {
        is FoodInfoUiState.Loading -> LoadingScreen()
        is FoodInfoUiState.Success -> FoodInfoContent(state.foodInfo, onBackClick)
        is FoodInfoUiState.Error -> ErrorScreen(state.error.toString(context)) {
            viewModel.loadFoodInfo()
        }
    }
}
@Composable
fun FoodInfoContent(foodInfo: FoodInfo, onBackClick: () -> Unit) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            FoodHeader(foodInfo) {
                onBackClick()
            }
        }
        item {
            ContentSection(
                title = "Description",
                content = { Text(text = foodInfo.description) }
            )
        }
        item {
            ContentSection(
                title = "Macro Nutrients",
                content = { NutritionTable(foodInfo.nutritionInfo) }
            )
        }
        item {
            ContentSection(
                title = "Facts",
                content = {
                    if (foodInfo.genericFacts.isNotEmpty()) {
                        FactsSection(foodInfo.genericFacts)
                    } else {
                        Text("No facts available")
                    }
                }
            )
        }
        item {
            ContentSection(
                title = "Similar Items",
                content = {
                    SimilarItemsGrid(foodInfo.similarItems)
                }
            )
        }
    }
}

@Composable
fun FoodHeader(foodInfo: FoodInfo, onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://firebasestorage.googleapis.com/v0/b/geeksforgeeks-98cf0.appspot.com/o/Chicken-Biryani-Recipe.jpg?alt=media&token=e564c04f-fd4d-4ed6-b06d-3aae14ca5d8b"),
            contentDescription = foodInfo.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.7f)
                        ),
                        startY = 0f
                    )
                )
        )
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Black.copy(alpha = 0.4f), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Back",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = "Food Information",
                style = MaterialTheme.typography.labelLarge,
                color = Color.White.copy(alpha = 0.7f)
            )
            Text(
                text = foodInfo.name,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(64.dp)
                .background(Color(0x8B8B8BA6).copy(alpha = 0.3f), shape = RoundedCornerShape(8.dp))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${foodInfo.healthRating.toInt()}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "out of 100",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
            }
        }
    }
}
@Composable
fun ContentSection(title: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        content()
    }
}

@Composable
fun NutritionTable(nutritionInfo: List<NutritionInfo>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFE8E8)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "",
                    modifier = Modifier.weight(1.5f),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Per Serve",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Per 250gm",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color.LightGray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))
            nutritionInfo.take(6).forEach { info ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = info.name,
                        modifier = Modifier.weight(1.5f),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${info.value.roundToDecimal(2)} ${info.units}",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "${(info.value * 2.5).roundToDecimal(2)} ${info.units}",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }

}

// Helper function to round doubles to a specific number of decimal places
fun Double.roundToDecimal(decimals: Int): String {
    return "%.${decimals}f".format(this)
}
@Composable
fun FactsSection(facts: List<String>) {
    val pagerState = rememberPagerState { facts.size }

    // Auto-scroll effect
    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) { page ->
            FactCard(facts[page])
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
}

@Composable
fun FactCard(fact: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFC107).copy(alpha = 0.5f)),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Did you know?",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = fact,
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun SimilarItemsGrid(items: List<SimilarItem>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(items) { item ->
            SimilarItemCard(item)
        }
    }
}

@Composable
fun SimilarItemCard(item: SimilarItem) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(100.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberAsyncImagePainter(getImageUrl(item.image)),
                contentDescription = item.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.2f))
            )
            Text(
                text = item.name,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }
    }
}
// Helper function to get image URL based on image name because the image URLs are not provided in the API response
fun getImageUrl(imageName: String): String {
    return when (imageName) {
        "veg_biryani.png" -> "https://firebasestorage.googleapis.com/v0/b/geeksforgeeks-98cf0.appspot.com/o/VegBiryani.jpg?alt=media&token=31c5af54-feea-4abf-b927-ca3653b75462"
        "paneer_biryani.png" -> "https://firebasestorage.googleapis.com/v0/b/geeksforgeeks-98cf0.appspot.com/o/paneer-biryani_-9.jpg?alt=media&token=aeaf79ef-e331-4b00-b231-f059a814b517"
        "mutton_biryani.png" -> "https://firebasestorage.googleapis.com/v0/b/geeksforgeeks-98cf0.appspot.com/o/MuttonBiryani.jpg?alt=media&token=6f65036c-00c8-4f2e-a064-b6e8f60716bb"
        else -> "" // You might want to provide a default image URL here
    }
}