package com.example.a30daysofnature.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.a30daysofnature.R

val HomemadeAppleRegular = FontFamily(
    Font(R.font.homemade_apple_regular)
)

val Urbanist = FontFamily(
    Font(R.font.urbanist_regular),
    Font(R.font.urbanist_bold, FontWeight.Bold),
    Font(R.font.urbanist_italic)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    displayLarge = TextStyle(
        fontFamily = HomemadeAppleRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 16.sp
        ),
    headlineMedium = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    )
)