package com.ayush.dietsnap.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.dietsnap.data.networking.util.NetworkError
import com.ayush.dietsnap.data.networking.util.Result
import com.ayush.dietsnap.domain.model.HomePage
import com.ayush.dietsnap.domain.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        fetchHomePageData()
    }

    fun fetchHomePageData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            when (val result = homeRepository.getHomePage()) {
                is Result.Success -> _uiState.value = HomeUiState.Success(result.data)
                is Result.Error -> _uiState.value = HomeUiState.Error(result.error)
            }
        }
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val data: HomePage) : HomeUiState()
    data class Error(val error: NetworkError) : HomeUiState()
}