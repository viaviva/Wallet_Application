package com.angelina.wallet_application.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.angelina.wallet_application.R

val titleFontFamily = FontFamily(Font(R.font.inter_semi_bold))

val textFontFamily = FontFamily(Font(R.font.inter_regular))

// Set of Material typography styles to start with
val Typography = Typography(

    //main text in screen
    titleMedium = TextStyle(
        fontSize = Dimens.sp_30,
        fontFamily = titleFontFamily,
        letterSpacing = Dimens.sp_03
    ),

    //subtext on screen
    titleSmall = TextStyle(
        fontSize = Dimens.sp_18,
        fontFamily = textFontFamily
    ),

    //title in alert dialog
    labelMedium = TextStyle(
        fontSize = Dimens.sp_24,
        fontFamily = titleFontFamily,
        letterSpacing = Dimens.sp_024
    ),

    //subtext in alert dialog and textField title
    labelSmall = TextStyle(
        fontSize = Dimens.sp_14,
        fontFamily = textFontFamily,
    )
)