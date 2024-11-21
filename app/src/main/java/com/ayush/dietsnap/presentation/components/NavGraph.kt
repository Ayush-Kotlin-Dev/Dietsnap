package com.ayush.dietsnap.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ayush.dietsnap.presentation.screens.food_info.FoodInfoScreen
import com.ayush.dietsnap.presentation.screens.home.HomeScreen
import com.ayush.dietsnap.presentation.screens.workout.WorkoutScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onGoalWorkoutClick = {
                    navController.navigate("workout")
                },
                onFindDietsClick = {
                    navController.navigate("foodInfo")
                },
                onFindNutritionistClick = {
                    // Handle nutritionist click
                }
            )
        }
        composable("foodInfo") {
            FoodInfoScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable("workout") {
            WorkoutScreen()
        }
    }
}