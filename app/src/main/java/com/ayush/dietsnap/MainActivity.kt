package com.ayush.dietsnap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ayush.dietsnap.presentation.components.AppNavigation
import com.ayush.dietsnap.ui.theme.DietsnapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DietsnapTheme {
                AppNavigation()
            }
        }
    }
}