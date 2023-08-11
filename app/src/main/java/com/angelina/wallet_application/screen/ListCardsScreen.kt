package com.angelina.wallet_application.screen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.angelina.wallet_application.R
import com.angelina.wallet_application.model.Shop
import com.angelina.wallet_application.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListCardsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp)
    ) {

        val listOfShops = listOf(
            Shop(1, "https://upload.wikimedia.org/wikipedia/commons/c/c0/Logo_euroopt.png", "Кофеек"),
            Shop(2, "https://upload.wikimedia.org/wikipedia/commons/c/c0/Logo_euroopt.png", "Кофеек"),
            Shop(3, "https://upload.wikimedia.org/wikipedia/commons/c/c0/Logo_euroopt.png", "Кофеек"),
        )

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
            items(listOfShops) {
                CardItem(card = it) {
//                    navController.navigate("$ITEM_SCREEN/${it.id}")
                }
            }
        }

    }


}

@Composable
fun CardItem(
    card: Shop,
    onCLick: () -> Unit = {}
) {
    Column(
        modifier = Modifier.selectable(
            true,
            onClick = onCLick
        )
    ) {
        Column {
            AsyncImage(
                model = card.imageUml,
                contentScale = ContentScale.FillWidth,
                contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .width(178.dp)
                    .height(124.dp)
                    .background(Color.Black)
            )
        }
    }
}