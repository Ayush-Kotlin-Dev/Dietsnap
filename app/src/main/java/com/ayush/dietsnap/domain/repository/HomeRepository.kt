package com.ayush.dietsnap.domain.repository

import com.ayush.dietsnap.domain.model.HomePage
import com.ayush.dietsnap.data.networking.util.NetworkError
import com.ayush.dietsnap.data.networking.util.Result

interface HomeRepository {
    suspend fun getHomePage(): Result<HomePage, NetworkError>
}