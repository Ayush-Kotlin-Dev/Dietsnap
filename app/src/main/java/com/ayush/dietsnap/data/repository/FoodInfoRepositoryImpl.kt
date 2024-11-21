package com.ayush.dietsnap.data.repository

import com.ayush.dietsnap.data.model.FoodInfoResponse
import com.ayush.dietsnap.data.networking.constructUrl
import com.ayush.dietsnap.data.networking.responseToResult
import com.ayush.dietsnap.data.networking.util.NetworkError
import com.ayush.dietsnap.data.networking.util.Result
import com.ayush.dietsnap.data.networking.util.map
import com.ayush.dietsnap.data.mapper.toDomainModel
import com.ayush.dietsnap.domain.model.FoodInfo
import com.ayush.dietsnap.domain.repository.FoodInfoRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.serialization.SerializationException
import java.io.IOException

class FoodInfoRepositoryImpl(private val httpClient: HttpClient) : FoodInfoRepository {
    override suspend fun getFoodInfo(): Result<FoodInfo, NetworkError> {
        return try {
            val url = constructUrl("food_info/")
            val response = httpClient.get(url)
            responseToResult<FoodInfoResponse>(response).map { it.data.toDomainModel() }
        } catch (e: Exception) {
            when (e) {
                is IOException -> Result.Error(NetworkError.NO_INTERNET)
                is SerializationException -> Result.Error(NetworkError.SERIALIZATION)
                else -> Result.Error(NetworkError.UNKNOWN)
            }
        }
    }
}