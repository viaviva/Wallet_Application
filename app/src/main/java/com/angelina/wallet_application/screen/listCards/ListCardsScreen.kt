package com.angelina.wallet_application.screen.listCards

import android.util.Log
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.angelina.wallet_application.R
import com.angelina.wallet_application.entity.firebase.ShopFirebase
import com.angelina.wallet_application.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListCardsScreen(
    viewModel: ListCardsViewModel = hiltViewModel(),
    onItemClick: (Long) -> Unit = {}
) {

    val listOfCards = viewModel.listOfCards.observeAsState()
    viewModel.getAllCards()

    Log.e("getAllCards", listOfCards.toString())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp)
    ) {

        Text(
            text = stringResource(id = R.string.my_cards),
            style = Typography.titleMedium,
            modifier = Modifier.padding(top = 30.dp, bottom = 20.dp)
        )

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
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

@Composable
fun CardItem(
    shop: ShopFirebase,
    onCLick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.selectable(
            true, onClick = onCLick
        )
    ) {
        Column {
            AsyncImage(
                model = shop.imageUrl,
                contentScale = ContentScale.FillWidth,
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .width(178.dp)
                    .height(124.dp)
                    .background(Color(shop.color))
            )
        }
    }
}