package com.ayush.dietsnap.presentation.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ayush.dietsnap.presentation.screens.food_info.FoodInfoScreen
import com.ayush.dietsnap.presentation.screens.home.HomeScreen
import com.ayush.dietsnap.presentation.screens.home.HomeUiState
import com.ayush.dietsnap.presentation.screens.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            val viewModel: HomeViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsState()
            val context = LocalContext.current
            when (val state = uiState) {
                is HomeUiState.Loading -> LoadingScreen()
                is HomeUiState.Error -> ErrorScreen(state.error.toString(context)) {
                    viewModel.fetchHomePageData()
                }
                is HomeUiState.Success -> HomeScreen(
                    homeData = state.data,
                    onFindDietsClick = {
                        navController.navigate("foodInfo")
                    },
                    onFindNutritionistClick = {
                        Toast.makeText(context, "Find Nutritionist Clicked", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
        composable("foodInfo") {
            FoodInfoScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}