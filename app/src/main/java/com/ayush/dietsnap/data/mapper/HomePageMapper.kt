package com.ayush.dietsnap.data.mapper


import com.ayush.dietsnap.data.model.HomePageData
import com.ayush.dietsnap.domain.model.*

fun HomePageData.toDomainModel(): HomePage {
    return HomePage(
        currentPlan = CurrentPlan(
            name = section_1.plan_name,
            progress = section_1.progress.removeSuffix("%").toInt()
        ),
        workoutSummary = WorkoutSummary(
            accuracy = section_2.accuracy.removeSuffix("%").toInt(),
            duration = section_2.workout_duration,
            reps = section_2.reps,
            caloriesBurned = section_2.calories_burned
        ),
        recommendedPlans = listOf(
            RecommendedPlan(section_3.plan_1.plan_name, section_3.plan_1.difficulty),
            RecommendedPlan(section_3.plan_2.plan_name, section_3.plan_2.difficulty)
        ),
        categories = listOf(
            ExerciseCategory(section_4.category_1.category_name, section_4.category_1.no_of_exercises.toInt()),
            ExerciseCategory(section_4.category_2.category_name, section_4.category_2.no_of_exercises.toInt())
        )
    )
}