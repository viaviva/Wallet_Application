package com.angelina.wallet_application.screen.onboarding

import android.Manifest
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.angelina.wallet_application.R
import com.angelina.wallet_application.ui.theme.Typography
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPermissionsApi::class)
@ExperimentalPagerApi
@Composable
fun OnBoardingScreen(
    onSkipClick: () -> Unit
) {

    val items = OnBoardingItems.getData()
    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState()
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    Column(modifier = Modifier.fillMaxSize()) {
        TopSection(
            onBackClick = {
                if (pageState.currentPage + 1 > 1) scope.launch {
                    pageState.scrollToPage(0)
                }
            },
            onSkipClick = {
                onSkipClick()
                cameraPermissionState.launchPermissionRequest()
            },
            isLastPage = pageState.currentPage == items.size - 1
        )

        HorizontalPager(
            count = items.size,
            state = pageState,
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .fillMaxHeight(0.9f)
                .fillMaxWidth()
        ) { page ->
            OnBoardingItem(items = items[page])
        }

        BottomSection(size = items.size, index = pageState.currentPage)
    }

}

@ExperimentalPagerApi
@Composable
fun TopSection(
    onBackClick: () -> Unit = {},
    onSkipClick: () -> Unit = {},
    isLastPage: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        // Back button
        IconButton(onClick = onBackClick, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(imageVector = Icons.Outlined.KeyboardArrowLeft, contentDescription = null)
        }

        // Skip Button
        TextButton(
            onClick = onSkipClick,
            modifier = Modifier.align(Alignment.CenterEnd),
            contentPadding = PaddingValues(0.dp)
        ) {
            val text = if (!isLastPage) {
                stringResource(id = R.string.skip)
            } else {
                stringResource(id = R.string.done)
            }

            Text(
                text = text,
                style = Typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(end = 10.dp)
            )
        }
    }
}

@Composable
fun BottomSection(
    size: Int,
    index: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        // Indicators
        Indicators(size, index)
    }
}

@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.Center)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color(0XFFF8E2E7)
            )
    ) {

    }
}

@Composable
fun OnBoardingItem(items: OnBoardingItems) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = items.image),
            contentDescription = "Image1",
            modifier = Modifier.padding(start = 50.dp, end = 50.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = stringResource(id = items.title),
            style = Typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = items.desc),
            style = Typography.titleSmall,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center
        )
    }
}