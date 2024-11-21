package com.ayush.dietsnap.presentation.screens.food_info

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.dietsnap.domain.model.FoodInfo
import com.ayush.dietsnap.domain.repository.FoodInfoRepository
import com.ayush.dietsnap.data.networking.util.NetworkError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.ayush.dietsnap.data.networking.util.Result

class FoodInfoViewModel(private val repository: FoodInfoRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<FoodInfoUiState>(FoodInfoUiState.Loading)
    val uiState: StateFlow<FoodInfoUiState> = _uiState.asStateFlow()

    fun loadFoodInfo() {
        viewModelScope.launch {
            _uiState.value = FoodInfoUiState.Loading
            Log.d(TAG, "State changed to Loading")

            when (val result = repository.getFoodInfo()) {
                is Result.Success -> {
                    Log.d(TAG, "Successfully fetched food info: ${result.data}")
                    _uiState.value = FoodInfoUiState.Success(result.data)
                    Log.d(TAG, "State changed to Success")
                }
                is Result.Error -> {
                    Log.e(TAG, "Error fetching food info: ${result.error}")
                    _uiState.value = FoodInfoUiState.Error(result.error)
                    Log.d(TAG, "State changed to Error")
                }
            }
        }
    }

    companion object {
        private const val TAG = "FoodInfoViewModel"
    }
}

sealed class FoodInfoUiState {
    object Loading : FoodInfoUiState()
    data class Success(val foodInfo: FoodInfo) : FoodInfoUiState()
    data class Error(val error: NetworkError) : FoodInfoUiState()
}