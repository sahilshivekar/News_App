package com.learn.mynewsapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learn.mynewsapp.R

val Poppins = FontFamily(
    fonts = listOf(
        Font(R.font.poppins_regular, FontWeight.Normal),
        Font(R.font.poppins_semibold, FontWeight.SemiBold),
        Font(R.font.poppins_bold, FontWeight.Bold)
    )
)

object NewsTypography {
    val newsPrimaryButton = TextStyle(
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold, // he wrote semibold
        fontFamily = Poppins
    )

    val newsTextButton = TextStyle(
        color = Color.Gray,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,// he wrote semibold
        fontFamily = Poppins
    )

    val headlineBold = TextStyle(
        fontSize = 24.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp,
    )

    val bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    )
}

// Set of Material typography styles to start with
val Typography = Typography()