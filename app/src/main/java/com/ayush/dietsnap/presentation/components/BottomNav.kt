package com.ayush.dietsnap.presentation.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomNavigation(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val selectedItem = remember { mutableStateOf("Activity") }

    Surface(
        modifier = modifier,
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BottomNavItem(Icons.Default.Home, "Activity",
                selected = selectedItem.value == "Activity",
                onClick = {
                    selectedItem.value = "Activity"
                }
            )
            BottomNavItem(Icons.Default.BarChart, "Goals",
                selected = selectedItem.value == "Goals",
                onClick = {
                    selectedItem.value = "Goals"
                    Toast.makeText(context, "TODO: Navigate to Goals", Toast.LENGTH_SHORT).show()
                }
            )
            BottomNavItem(Icons.Default.PhotoCamera, "Camera",
                selected = selectedItem.value == "Camera",
                onClick = {
                    selectedItem.value = "Camera"
                    Toast.makeText(context, "TODO: Open Camera", Toast.LENGTH_SHORT).show()
                }
            )
            BottomNavItem(Icons.Default.List, "Feed",
                selected = selectedItem.value == "Feed",
                onClick = {
                    selectedItem.value = "Feed"
                    Toast.makeText(context, "TODO: Navigate to Feed", Toast.LENGTH_SHORT).show()
                }
            )
            BottomNavItem(Icons.Default.Person, "Profile",
                selected = selectedItem.value == "Profile",
                onClick = {
                    selectedItem.value = "Profile"
                    Toast.makeText(context, "TODO: Navigate to Profile", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected) Color(0xFFFFA500) else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = if (selected) Color(0xFFFFA500) else Color.Gray
        )
    }
}