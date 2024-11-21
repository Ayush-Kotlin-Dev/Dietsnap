package com.ayush.dietsnap.data.mapper

import com.ayush.dietsnap.data.model.FoodInfoDataModel
import com.ayush.dietsnap.data.model.IngredientDataModel
import com.ayush.dietsnap.data.model.NutritionInfoDataModel
import com.ayush.dietsnap.data.model.ServingSizeDataModel
import com.ayush.dietsnap.data.model.SimilarItemDataModel
import com.ayush.dietsnap.domain.model.*

fun FoodInfoDataModel.toDomainModel() = FoodInfo(
    id = id,
    name = name,
    description = description,
    cuisine = cuisine,
    healthRating = healthRating,
    image = image,
    ingredients = ingredients.map { it.toDomainModel() },
    nutritionInfo = nutritionInfoScaled.map { it.toDomainModel() },
    servingSizes = servingSizes.map { it.toDomainModel() },
    similarItems = similarItems.map { it.toDomainModel() },
    genericFacts = genericFacts
)

fun IngredientDataModel.toDomainModel() = Ingredient(
    id = ingid,
    name = name,
    value = value
)

fun NutritionInfoDataModel.toDomainModel() = NutritionInfo(
    name = name,
    value = value,
    units = units
)

fun ServingSizeDataModel.toDomainModel() = ServingSize(
    units = units,
    name = name,
    value = value
)

fun SimilarItemDataModel.toDomainModel() = SimilarItem(
    id = id,
    name = name,
    image = image
)