package com.angelina.wallet_application.screen.listCards

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.angelina.wallet_application.R
import com.angelina.wallet_application.entity.firebase.ShopFirebase
import com.angelina.wallet_application.ui.theme.Dimens
import com.angelina.wallet_application.ui.theme.Typography
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListCardsScreen(
    viewModel: ListCardsViewModel = hiltViewModel(),
    onItemClick: (Long) -> Unit = {}
) {

    val listOfCards = viewModel.listOfCards.observeAsState()
    var refreshing by remember { mutableStateOf(false) }

    viewModel.getAllCards()

    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(1000)
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = {
            refreshing = true
            viewModel.getAllCards()
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.sdp_22)
        ) {

            Text(
                text = stringResource(id = R.string.my_cards),
                style = Typography.titleMedium,
                modifier = Modifier.padding(top = Dimens.dp_30, bottom = Dimens.dp_20)
            )

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = Dimens.sdp_10,
                horizontalArrangement = Arrangement.spacedBy(Dimens.sdp_10)
            ) {
                items(listOfCards.value ?: arrayListOf()) {
                    CardItem(
                        viewModel.getShop(it.idShop),
                    ) {
                        onItemClick(it.idCard)
                    }
                }
            }

        }
    }

}

@Composable
fun CardItem(
    shop: ShopFirebase,
    onCLick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.selectable(
            true, onClick = onCLick
        ),
        shape = RoundedCornerShape(Dimens.dp_20),
        shadowElevation = Dimens.sdp_6,
        color = Color.White
    ) {
        Column {
            AsyncImage(
                model = shop.imageUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(Dimens.dp_16))
                    .width(Dimens.sdp_178)
                    .height(Dimens.sdp_124)
                    .background(Color(shop.color))
            )
        }
    }
}