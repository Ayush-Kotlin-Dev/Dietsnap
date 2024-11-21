package com.ayush.dietsnap.di

import com.ayush.dietsnap.data.networking.HttpClientFactory
import com.ayush.dietsnap.data.repository.FoodInfoRepositoryImpl
import com.ayush.dietsnap.data.repository.HomeRepositoryImpl
import com.ayush.dietsnap.domain.repository.FoodInfoRepository
import com.ayush.dietsnap.domain.repository.HomeRepository
import com.ayush.dietsnap.presentation.screens.food_info.FoodInfoViewModel
import com.ayush.dietsnap.presentation.screens.home.HomeViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<FoodInfoRepository> { FoodInfoRepositoryImpl(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel{ FoodInfoViewModel(get()) }

}