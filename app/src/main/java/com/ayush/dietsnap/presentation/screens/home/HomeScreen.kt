package com.ayush.dietsnap.presentation.screens.home

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ayush.dietsnap.domain.model.CurrentPlan
import com.ayush.dietsnap.domain.model.ExerciseCategory
import com.ayush.dietsnap.domain.model.HomePage
import com.ayush.dietsnap.domain.model.RecommendedPlan
import com.ayush.dietsnap.domain.model.WorkoutSummary
import com.ayush.dietsnap.presentation.components.toString
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(
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

        is HomeUiState.Success -> HomeContent(state.data, modifier)
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(errorMessage: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorMessage)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@Composable
fun HomeContent(data: HomePage, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { CurrentPlanSection(data.currentPlan) }
        item { WorkoutSummarySection(data.workoutSummary) }
        item { RecommendedPlansSection(data.recommendedPlans) }
        item { CategoriesSection(data.categories) }
    }
}

@Composable
fun CurrentPlanSection(currentPlan: CurrentPlan) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Current Plan", style = MaterialTheme.typography.titleMedium)
            Text(text = currentPlan.name, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = currentPlan.progress / 100f,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "${currentPlan.progress}% complete",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun WorkoutSummarySection(summary: WorkoutSummary) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Workout Summary", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SummaryItem("Accuracy", "${summary.accuracy}%")
                SummaryItem("Duration", summary.duration)
                SummaryItem("Reps", summary.reps.toString())
                SummaryItem("Calories", "${summary.caloriesBurned} kcal")
            }
        }
    }
}

@Composable
fun SummaryItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.bodySmall)
        Text(text = value, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun RecommendedPlansSection(plans: List<RecommendedPlan>) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Recommended Plans", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            plans.forEach { plan ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = plan.name, style = MaterialTheme.typography.bodyMedium)
                    Text(text = plan.difficulty, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
fun CategoriesSection(categories: List<ExerciseCategory>) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Exercise Categories", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            categories.forEach { category ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = category.name, style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = "${category.exerciseCount} exercises",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
