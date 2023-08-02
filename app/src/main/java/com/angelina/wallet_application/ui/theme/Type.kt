package com.angelina.wallet_application.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.angelina.wallet_application.R

val titleFontFamily = FontFamily(Font(R.font.inter_semi_bold))

val textFontFamily = FontFamily(Font(R.font.inter_regular))

// Set of Material typography styles to start with
val Typography = Typography(

    //main text in screen
    titleMedium = TextStyle(
        fontSize = 30.sp,
        fontFamily = titleFontFamily,
        letterSpacing = (-0.3).sp
    ),

    //subtext on screen
    titleSmall = TextStyle(
        fontSize = 17.sp,
        fontFamily = textFontFamily
    ),

    //title in alert dialog
    labelMedium = TextStyle(
        fontSize = 24.sp,
        fontFamily = titleFontFamily,
        letterSpacing = (-0.24).sp
    ),

    //subtext in alert dialog and textField title
    labelSmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = textFontFamily,
    )
)