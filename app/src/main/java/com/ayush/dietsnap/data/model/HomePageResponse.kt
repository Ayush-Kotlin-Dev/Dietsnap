package com.ayush.dietsnap.data.model


import kotlinx.serialization.Serializable

@Serializable
data class HomePageResponse(
    val success: Boolean,
    val data: HomePageData,
    val message: String
)

@Serializable
data class HomePageData(
    val section_1: Section1,
    val section_2: Section2,
    val section_3: Section3,
    val section_4: Section4
)

@Serializable
data class Section1(
    val plan_name: String,
    val progress: String
)

@Serializable
data class Section2(
    val accuracy: String,
    val workout_duration: String,
    val reps: Int,
    val calories_burned: Int
)

@Serializable
data class Section3(
    val plan_1: Plan,
    val plan_2: Plan
)

@Serializable
data class Section4(
    val category_1: Category,
    val category_2: Category
)

@Serializable
data class Plan(
    val plan_name: String,
    val difficulty: String
)

@Serializable
data class Category(
    val category_name: String,
    val no_of_exercises: String
)