package com.ayush.dietsnap.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayush.dietsnap.R

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Dietsnap",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFFFFA500)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bell),
                contentDescription = "Notifications",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_award),
                contentDescription = "Bookmarks",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_message),
                contentDescription = "Menu",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
