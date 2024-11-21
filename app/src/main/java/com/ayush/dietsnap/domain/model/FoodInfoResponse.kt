package com.ayush.dietsnap.domain.model

data class FoodInfo(
    val id: String,
    val name: String,
    val description: String,
    val cuisine: String,
    val healthRating: Double,
    val image: String,
    val ingredients: List<Ingredient>,
    val nutritionInfo: List<NutritionInfo>,
    val servingSizes: List<ServingSize>,
    val similarItems: List<SimilarItem>,
    val genericFacts: List<String>
)

data class Ingredient(
    val id: String,
    val name: String,
    val value: Double
)

data class NutritionInfo(
    val name: String,
    val value: Double,
    val units: String
)

data class ServingSize(
    val units: String,
    val name: String,
    val value: Double
)

data class SimilarItem(
    val id: String,
    val name: String,
    val image: String
)