package com.ayush.dietsnap.presentation.screens.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayush.dietsnap.R
import com.ayush.dietsnap.domain.model.HomePage
import com.ayush.dietsnap.presentation.components.BottomNavigation
import com.ayush.dietsnap.presentation.components.TopBar
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun HomeScreen(homeData: HomePage, onFindDietsClick: () -> Unit , onFindNutritionistClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                item { TopBar() }
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item { TodaySection() }
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item { CircularProgressSection() }
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item { AdditionalPointsSection(homeData) }
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item { YourGoalsSection(homeData) }
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item { ExploreSection(onFindDietsClick, onFindNutritionistClick) }
                item { Spacer(modifier = Modifier.height(80.dp)) }
            }
        }
        BottomNavigation(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }
}


@Composable
fun TodaySection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Today",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "Thursday, 22nd Oct",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}
@Composable
fun CircularProgressSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.width(24.dp))
            Box(
                modifier = Modifier.size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(200.dp)) {
                    val strokeWidth = 20f
                    val centerX = size.width / 2
                    val centerY = size.height / 2
                    val outerRadius = (size.minDimension - strokeWidth) / 2
                    val innerRadius = outerRadius - 30f

                    // Outer circle background
                    drawArc(
                        color = Color(0xFFFFE4B5),
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth)
                    )

                    // Inner circle background
                    drawArc(
                        color = Color(0xFFF0E0FF),
                        startAngle = -90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth),
                        size = Size(size.width - 60f, size.height - 60f),
                        topLeft = Offset(30f, 30f)
                    )

                    // Outer arc progress
                    drawArc(
                        color = Color(0xFFFFAA00),
                        startAngle = -90f,
                        sweepAngle = 300f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth)
                    )

                    // Inner arc progress
                    drawArc(
                        color = Color(0xFFE6A8D7),
                        startAngle = -90f,
                        sweepAngle = 240f,
                        useCenter = false,
                        style = Stroke(width = strokeWidth),
                        size = Size(size.width - 60f, size.height - 60f),
                        topLeft = Offset(30f, 30f)
                    )

                    // Small circles at the end of each arc
                    val outerAngle = 300f * Math.PI.toFloat() / 180f - Math.PI.toFloat() / 2f
                    val innerAngle = 240f * Math.PI.toFloat() / 180f - Math.PI.toFloat() / 2f

                    drawCircle(
                        color = Color(0xFFFFAA00),
                        radius = 10f,
                        center = Offset(
                            centerX + cos(outerAngle) * outerRadius,
                            centerY + sin(outerAngle) * outerRadius
                        )
                    )

                    drawCircle(
                        color = Color(0xFFE6A8D7),
                        radius = 10f,
                        center = Offset(
                            centerX + cos(innerAngle) * innerRadius,
                            centerY + sin(innerAngle) * innerRadius
                        )
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "SET GOAL!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Set Goal",
                tint = Color(0xFFFFA500),
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ProgressLabel(
                icon = R.drawable.ic_diet_pts,
                label = "Diet Pts",
                color = Color(0xFFFFAA00)
            )
            Spacer(modifier = Modifier.width(24.dp))
            ProgressLabel(
                icon = R.drawable.ic_exercise,
                label = "Exercise Pts",
                color = Color(0xFFE6A8D7)
            )
        }
    }
}

@Composable
fun ProgressLabel(icon: Int, label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = label,
            fontSize = 15.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun AdditionalPointsSection(homeData: HomePage) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceAround
        ) {
            AdditionalPointItem(
                value = "1500",
                subLabel = "Cal",
                color = Color(0xFFFFAA00)
            )
            AdditionalPointItem(
                value = "3/5",
                subLabel = "Days",
                color = Color(0xFFFFAA00)
            )
            AdditionalPointItem(
                value = "88",
                subLabel = "Health Score",
                color = Color(0xFFFFAA00)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color(0xFFFFA500), CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color.LightGray, CircleShape)
            )
        }
    }
}

@Composable
fun AdditionalPointItem(
    value: String,
    subLabel: String,
    color: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = color
        )
        Text(
            text = subLabel,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun YourGoalsSection(homeData: HomePage) {
    Text(
        text = "Your Goals",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(8.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(0.1f)) // Set the card background to white

    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_keto),
                contentDescription = "Keto",
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Keto Weight loss plan",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(text = "Current Major Goal", fontSize = 12.sp, color = Color.Gray)
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(60.dp)
            ) {
                CircularProgressIndicator(
                    progress = homeData.currentPlan.progress / 100f,
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFFA500),
                    strokeWidth = 4.dp
                )
                Text(
                    text = "${homeData.currentPlan.progress}%",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFA500)
                )
            }
        }
    }
}

@Composable
fun ExploreSection(onFindDietsClick: () -> Unit, onFindNutritionistClick: () -> Unit) {
    Text(
        text = "Explore",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(8.dp))
    ExploreItem(
        icon = R.drawable.ic_diet,
        title = "Find Diets",
        description = "Find premade diets according to your cuisine",
        onClick = onFindDietsClick
    )
    Spacer(modifier = Modifier.height(8.dp))
    ExploreItem(
        icon = R.drawable.ic_nutritionist,
        title = "Find Nutritionist",
        description = "Get customized diets to achieve your health goal",
        onClick = { onFindNutritionistClick() }
    )
}
@Composable
fun ExploreItem(icon: Int, title: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = title,
                modifier = Modifier.size(60.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = description, fontSize = 12.sp, color = Color.Gray)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Navigate to $title",
                tint = Color.Gray
            )
        }
    }
}