package com.ayush.dietsnap.data.repository

import android.util.Log
import com.ayush.dietsnap.data.model.HomePageResponse
import com.ayush.dietsnap.data.networking.constructUrl
import com.ayush.dietsnap.data.networking.util.NetworkError
import com.ayush.dietsnap.data.networking.util.Result
import com.ayush.dietsnap.data.toDomainModel
import com.ayush.dietsnap.domain.model.HomePage
import com.ayush.dietsnap.domain.repository.HomeRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepositoryImpl(private val httpClient: HttpClient) : HomeRepository {
    companion object {
        private const val TAG = "HomeRepositoryImpl"
    }

    override suspend fun getHomePage(): Result<HomePage, NetworkError> {
        return withContext(Dispatchers.IO) {
            try {
                val url = constructUrl("homepage_v2/")
                Log.d(TAG, "Requesting URL: $url")

                val response: HttpResponse = httpClient.get(url)
                Log.d(TAG, "Response status: ${response.status}")

                val responseBody = response.body<HomePageResponse>()
                Log.d(TAG, "Response body: $responseBody")

                if (responseBody.success) {
                    val homePage = responseBody.data.toDomainModel()
                    Log.d(TAG, "Successfully mapped to HomePage: $homePage")
                    Result.Success(homePage)
                } else {
                    Log.e(TAG, "API returned success=false. Message: ${responseBody.message}")
                    Result.Error(NetworkError.SERVER_ERROR)
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching home page", e)
                when (e) {
                    is io.ktor.client.network.sockets.ConnectTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                    is io.ktor.client.plugins.HttpRequestTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
                    is java.net.UnknownHostException -> Result.Error(NetworkError.NO_INTERNET)
                    is kotlinx.serialization.SerializationException -> {
                        Log.e(TAG, "Serialization error", e)
                        Result.Error(NetworkError.SERIALIZATION)
                    }
                    else -> Result.Error(NetworkError.UNKNOWN)
                }
            }
        }
    }
}