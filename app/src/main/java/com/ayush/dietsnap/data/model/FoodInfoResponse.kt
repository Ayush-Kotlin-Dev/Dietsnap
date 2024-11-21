package com.ayush.dietsnap.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodInfoResponse(
    val success: Boolean,
    val data: FoodInfoDataModel,
    val message: String
)

@Serializable
data class FoodInfoDataModel(
    @SerialName("_id") val id: String,
    @SerialName("api_name") val apiName: List<String>,
    @SerialName("badge_indicator") val badgeIndicator: String,
    @SerialName("credits_url") val creditsUrl: String,
    val cuisine: String,
    val description: String,
    @SerialName("generic_facts") val genericFacts: List<String>,
    @SerialName("health_rating") val healthRating: Double,
    val image: String,
    @SerialName("image_url") val imageUrl: String,
    val ingredients: List<IngredientDataModel>,
    val itemtype: String,
    val name: String,
    @SerialName("no_of_servings") val noOfServings: Double,
    @SerialName("nutrition_facts") val nutritionFacts: String,
    @SerialName("nutrition_info") val nutritionInfo: String,
    @SerialName("nutrition_info_scaled") val nutritionInfoScaled: List<NutritionInfoDataModel>,
    @SerialName("serving_sizes") val servingSizes: List<ServingSizeDataModel>,
    @SerialName("similar_items") val similarItems: List<SimilarItemDataModel>,
    val type: String,
    @SerialName("default_unit_serving") val defaultUnitServing: String
)
@Serializable
data class IngredientDataModel(
    val ingid: String,
    val name: String,
    val value: Double
)

@Serializable
data class NutritionInfoDataModel(
    val name: String,
    val value: Double,
    val units: String
)

@Serializable
data class ServingSizeDataModel(
    val units: String,
    val name: String,
    val value: Double
)

@Serializable
data class SimilarItemDataModel(
    @SerialName("_id") val id: String,
    val name: String,
    val image: String
)