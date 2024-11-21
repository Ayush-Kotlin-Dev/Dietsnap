package com.ayush.dietsnap.domain.repository

import com.ayush.dietsnap.data.networking.util.NetworkError
import com.ayush.dietsnap.domain.model.FoodInfo
import com.ayush.dietsnap.data.networking.util.Result

interface FoodInfoRepository {
    suspend fun getFoodInfo(): Result<FoodInfo, NetworkError>
}