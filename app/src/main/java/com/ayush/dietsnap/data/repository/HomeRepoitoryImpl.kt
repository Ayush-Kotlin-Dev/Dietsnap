package com.ayush.dietsnap.data.repository

import com.ayush.dietsnap.data.model.HomePageResponse
import com.ayush.dietsnap.data.networking.constructUrl
import com.ayush.dietsnap.data.networking.responseToResult
import com.ayush.dietsnap.data.networking.util.NetworkError
import com.ayush.dietsnap.data.networking.util.Result
import com.ayush.dietsnap.data.networking.util.map
import com.ayush.dietsnap.data.toDomainModel
import com.ayush.dietsnap.domain.model.HomePage
import com.ayush.dietsnap.domain.repository.HomeRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.serialization.SerializationException
import java.io.IOException

class HomeRepositoryImpl(private val httpClient: HttpClient) : HomeRepository {
    override suspend fun getHomePage(): Result<HomePage, NetworkError> {
        return try {
            val url = constructUrl("homepage_v2/")
            val response = httpClient.get(url)
            responseToResult<HomePageResponse>(response).map { it.data.toDomainModel() }
        } catch (e: Exception) {
            when (e) {
                is IOException -> Result.Error(NetworkError.NO_INTERNET)
                is SerializationException -> Result.Error(NetworkError.SERIALIZATION)
                else -> Result.Error(NetworkError.UNKNOWN)
            }
        }
    }
}