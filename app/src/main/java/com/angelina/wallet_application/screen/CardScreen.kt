package com.angelina.wallet_application.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.angelina.wallet_application.ui.component.BackArrow
import com.angelina.wallet_application.ui.theme.Typography

@Composable
fun CardScreen() {
    Column(
        modifier = Modifier
            .padding(horizontal = 22.dp)
            .padding(top = 28.dp)
            .fillMaxSize()
    ) {

        BackArrow(
//            onClick = { onBackArrowClick() }
        )

        Column(
            modifier = Modifier.padding(vertical = 26.dp)
        ) {
            AsyncImage(
                model = "https://upload.wikimedia.org/wikipedia/commons/c/c0/Logo_euroopt.png",
                contentScale = ContentScale.FillWidth,
                contentDescription = "",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .height(204.dp)
                    .background(Color.Black)
            )


            Surface(
                modifier = Modifier
//                    .padding(horizontal = 6.dp, vertical = 20.dp)
                    .clip(RoundedCornerShape(16.dp)),
                color = Color.LightGray
            ) {
                Column {
                    Text(
                        text = "Данные карты",
                        style = Typography.labelMedium,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp),
                        fontSize = 20.sp
                    )

                    AsyncImage(
                        model = "https://blog.nzcouriers.co.nz/wp-content/uploads/sites/15/2022/06/iStock-1338854699-1024x683.jpg",
                        contentScale = ContentScale.FillWidth,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .fillMaxWidth()
                            .height(130.dp)
                    )
                }

            }
        }

    }
}

@Composable
@Preview
fun CardScreenPreview() {
    CardScreen()
}