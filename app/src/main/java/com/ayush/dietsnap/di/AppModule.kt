package com.ayush.dietsnap.di

import com.ayush.dietsnap.data.networking.HttpClientFactory
import com.ayush.dietsnap.data.repository.HomeRepositoryImpl
import com.ayush.dietsnap.domain.repository.HomeRepository
import com.ayush.dietsnap.presentation.screens.home.HomeViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    viewModel { HomeViewModel(get()) }
}