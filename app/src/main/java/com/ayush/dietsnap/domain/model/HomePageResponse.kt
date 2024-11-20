package com.ayush.dietsnap.domain.model


data class HomePage(
    val currentPlan: CurrentPlan,
    val workoutSummary: WorkoutSummary,
    val recommendedPlans: List<RecommendedPlan>,
    val categories: List<ExerciseCategory>
)

data class CurrentPlan(
    val name: String,
    val progress: Int
)

data class WorkoutSummary(
    val accuracy: Int,
    val duration: String,
    val reps: Int,
    val caloriesBurned: Int
)

data class RecommendedPlan(
    val name: String,
    val difficulty: String
)

data class ExerciseCategory(
    val name: String,
    val exerciseCount: Int
)