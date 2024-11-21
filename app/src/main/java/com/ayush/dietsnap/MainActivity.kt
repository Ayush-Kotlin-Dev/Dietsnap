package com.ayush.dietsnap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.ayush.dietsnap.presentation.components.ErrorScreen
import com.ayush.dietsnap.presentation.components.LoadingScreen
import com.ayush.dietsnap.presentation.components.toString
import com.ayush.dietsnap.presentation.screens.food_info.FoodInfoScreen
import com.ayush.dietsnap.presentation.screens.food_info.FoodInfoUiState
import com.ayush.dietsnap.presentation.screens.food_info.FoodInfoViewModel
import com.ayush.dietsnap.presentation.screens.home.HomeUiState
import com.ayush.dietsnap.presentation.screens.home.HomeViewModel
import com.ayush.dietsnap.presentation.screens.home.HomeScreen
import com.ayush.dietsnap.ui.theme.DietsnapTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DietsnapTheme {
//                val viewModel: HomeViewModel = koinViewModel()
//                val uiState by viewModel.uiState.collectAsState()
//                val context = LocalContext.current
//                when (val state = uiState) {
//                    is HomeUiState.Loading -> LoadingScreen()
//                    is HomeUiState.Error -> ErrorScreen(state.error.toString(context)) {
//                        viewModel.fetchHomePageData()
//                    }
//
//                    is HomeUiState.Success -> HomeScreen(state.data)
//                }
                val viewModel: FoodInfoViewModel = koinViewModel()
                FoodInfoScreen(viewModel)
            }
        }
    }
}