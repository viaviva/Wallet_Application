package com.angelina.wallet_application.screen

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.angelina.wallet_application.screen.card.CardViewModel
import com.angelina.wallet_application.ui.component.BackArrow
import com.angelina.wallet_application.ui.component.DeleteAlertDialog
import com.angelina.wallet_application.ui.theme.Typography
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CardScreen(
    id: Long,
    onBackArrowClick: () -> Unit,
    viewModel: CardViewModel = hiltViewModel()
) {

    var expanded by remember { mutableStateOf(false) }
    var openDialog = remember { mutableStateOf(false) }


    val card = viewModel.card.observeAsState()
    viewModel.getCard(id)

    Column(
        modifier = Modifier
            .padding(horizontal = 22.dp)
            .padding(top = 22.dp)
            .fillMaxSize()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BackArrow(
                onClick = { onBackArrowClick() }
            )

            Box {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    Text(
                        "Удалить",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .clickable(onClick = { openDialog.value = true })
                    )
                }
            }
        }

        Column(
            modifier = Modifier.padding(vertical = 22.dp)
        ) {
            AsyncImage(
                model = viewModel.getShopImage(card.value?.idShop ?: 0),
                contentScale = ContentScale.FillWidth,
                contentDescription = "",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .height(204.dp)
                    .background(Color.Black)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "Данные карты",
                    style = Typography.labelMedium,
                    fontSize = 24.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .align(Alignment.CenterHorizontally)
                ) {

                    Image(
                        painter = rememberQrBitmapPainter(card.value?.cardNumber.toString()),
                        contentDescription = "",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(196.dp)
                            .padding(all = 10.dp)
                            .align(Alignment.CenterHorizontally),
                    )

                    Text(
                        text = card.value?.cardNumber.toString(),
                        style = Typography.labelMedium,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }

    }

    val content = LocalContext.current

    if (openDialog.value) {
        DeleteAlertDialog(
            onDismissButtonClick = { openDialog.value = false },
            onConfirmButtonClick = {
                viewModel.deleteCard(id)
                onBackArrowClick()
                Toast.makeText(content, "Карта удалена", Toast.LENGTH_LONG).show()
            }
        )
    }
}

@Composable
fun rememberQrBitmapPainter(
    content: String,
    size: Dp = 150.dp,
    padding: Dp = 0.dp
): BitmapPainter {

    val density = LocalDensity.current
    val sizePx = with(density) { size.roundToPx() }
    val paddingPx = with(density) { padding.roundToPx() }

    var bitmap by remember(content) { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(bitmap) {
        if (bitmap != null) return@LaunchedEffect

        launch(Dispatchers.IO) {
            val qrCodeWriter = QRCodeWriter()

            val encodeHints = mutableMapOf<EncodeHintType, Any?>()
                .apply {
                    this[EncodeHintType.MARGIN] = paddingPx
                }

            val bitmapMatrix = try {
                qrCodeWriter.encode(
                    content, BarcodeFormat.QR_CODE,
                    sizePx, sizePx, encodeHints
                )
            } catch (ex: WriterException) {
                null
            }

            val matrixWidth = bitmapMatrix?.width ?: sizePx
            val matrixHeight = bitmapMatrix?.height ?: sizePx

            val newBitmap = Bitmap.createBitmap(
                bitmapMatrix?.width ?: sizePx,
                bitmapMatrix?.height ?: sizePx,
                Bitmap.Config.ARGB_8888,
            )

            for (x in 0 until matrixWidth) {
                for (y in 0 until matrixHeight) {
                    val shouldColorPixel = bitmapMatrix?.get(x, y) ?: false
                    val pixelColor =
                        if (shouldColorPixel) android.graphics.Color.BLACK else android.graphics.Color.WHITE

                    newBitmap.setPixel(x, y, pixelColor)
                }
            }

            bitmap = newBitmap
        }
    }

    return remember(bitmap) {
        val currentBitmap = bitmap ?: Bitmap.createBitmap(
            sizePx, sizePx,
            Bitmap.Config.ARGB_8888,
        ).apply { eraseColor(android.graphics.Color.TRANSPARENT) }

        BitmapPainter(currentBitmap.asImageBitmap())
    }
}