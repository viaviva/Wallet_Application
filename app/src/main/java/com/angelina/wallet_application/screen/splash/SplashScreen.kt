package com.angelina.wallet_application.screen.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.angelina.wallet_application.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashEnd: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        delay(1300)
        onSplashEnd()
    }
    Splash()
}

@Composable
fun Splash() {
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(120.dp),
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = ""
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash()
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SplashScreenDarkPreview() {
    Splash()
}