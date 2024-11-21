package com.ayush.dietsnap.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ayush.dietsnap.R
import com.ayush.dietsnap.domain.model.CurrentPlan
import com.ayush.dietsnap.domain.model.ExerciseCategory
import com.ayush.dietsnap.domain.model.HomePage
import com.ayush.dietsnap.domain.model.RecommendedPlan
import com.ayush.dietsnap.domain.model.WorkoutSummary
import com.ayush.dietsnap.presentation.components.ErrorScreen
import com.ayush.dietsnap.presentation.components.LoadingScreen
import com.ayush.dietsnap.presentation.components.toString
import com.ayush.dietsnap.presentation.screens.food_info.ContentSection
import com.ayush.dietsnap.presentation.screens.home.HomeUiState
import com.ayush.dietsnap.presentation.screens.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel


// This is a screen where data is fetched from API and displayed.
// On the assignment sheet, the home screen UI doesn't have any data given in the API response,
// so a separate screen was created for this purpose.

@Composable
fun DataScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    when (val state = uiState) {
        is HomeUiState.Loading -> LoadingScreen()
        is HomeUiState.Error -> ErrorScreen(state.error.toString(context)) {
            viewModel.fetchHomePageData()
        }

        is HomeUiState.Success -> DataContent(state.data, modifier)
    }
}

@Composable
fun DataContent(data: HomePage, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            DataHeader(data.currentPlan.name)
        }
        item {
            ContentSection(
                title = "Current Plan",
                content = { CurrentPlanSection(data.currentPlan) }
            )
        }
        item {
            ContentSection(
                title = "Workout Summary",
                content = { WorkoutSummarySection(data.workoutSummary) }
            )
        }
        item {
            ContentSection(
                title = "Recommended Plans",
                content = { RecommendedPlansSection(data.recommendedPlans) }
            )
        }
        item {
            ContentSection(
                title = "Exercise Categories",
                content = { CategoriesSection(data.categories) }
            )
        }
    }
}

@Composable
fun DataHeader(planName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_diet), // Replace with your background image
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                        startY = 100f
                    )
                )
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = "Current Workout Plan",
                style = MaterialTheme.typography.labelLarge,
                color = Color.White.copy(alpha = 0.7f)
            )
            Text(
                text = planName,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CurrentPlanSection(currentPlan: CurrentPlan) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = currentPlan.name, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = currentPlan.progress / 100f,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "${currentPlan.progress} complete",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun WorkoutSummarySection(summary: WorkoutSummary) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SummaryItem("Accuracy", summary.accuracy.toString())
                SummaryItem("Duration", summary.duration)
                SummaryItem("Reps", summary.reps.toString())
                SummaryItem("Calories", "${summary.caloriesBurned} cal")
            }
        }
    }
}

@Composable
fun RecommendedPlansSection(plans: List<RecommendedPlan>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            PlanItem(plans[0])
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            PlanItem(plans[1])
        }
    }
}

@Composable
fun CategoriesSection(categories: List<ExerciseCategory>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            CategoryItem(categories[0])
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            CategoryItem(categories[1])
        }
    }
}


@Composable
fun SummaryItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.bodySmall)
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PlanItem(plan: RecommendedPlan) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = plan.name, style = MaterialTheme.typography.bodyMedium)
        Text(text = plan.difficulty, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun CategoryItem(category: ExerciseCategory) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = category.name, style = MaterialTheme.typography.bodyMedium)
        Text(
            text = "${category.exerciseCount} exercises",
            style = MaterialTheme.typography.bodySmall
        )
    }
}