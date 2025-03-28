package com.example.makanyuk.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.makanyuk.R

// Set of Material typography styles to start with


val Poppins = FontFamily(
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
    Font(R.font.poppins_black, FontWeight.Black)
)
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Poppins
    ),
    displayMedium = TextStyle(
        fontFamily = Poppins
    ),
    displaySmall = TextStyle(
        fontFamily = Poppins
    ),
    headlineLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold
    ),
    titleLarge = TextStyle(
        fontFamily = Poppins
    ),
    titleMedium = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 27.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Poppins
    ),
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Poppins,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Poppins,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Poppins
    ),
    labelMedium = TextStyle(
        fontFamily = Poppins,
        fontSize = 11.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Poppins
    ),

    )