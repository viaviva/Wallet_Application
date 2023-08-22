package com.angelina.wallet_application.screen.addCard

import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.angelina.wallet_application.R
import com.angelina.wallet_application.ui.component.CommonButton
import com.angelina.wallet_application.ui.component.TextField
import com.angelina.wallet_application.ui.theme.Typography

@ExperimentalGetImage
@Composable
fun AddCardScreen(
    barcode: String,
    onScannerButtonClick: () -> Unit,
    onAddCardButtonClick: () -> Unit,
    viewModel: AddCardViewModel = hiltViewModel()
) {

    viewModel.run {
        setBarcodeFromScanner(barcode)
        getCountOfCards()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp)
    ) {

        Text(
            text = stringResource(id = R.string.addCard),
            style = Typography.titleMedium,
            modifier = Modifier.padding(top = 110.dp, bottom = 26.dp)
        )

        LargeDropdownMenu(
            label = "Магазин",
            items = viewModel.listOfShops,
            selectedIndex = viewModel.shop,
            onItemSelected = { index, _ -> viewModel.updateShop(index) },
        )

        TextField(
            viewModel.barcode,
            R.string.card_number,
            R.string.card_number,
            isScanner = true,
            onScannerButtonClick = {
                viewModel.setShop()
                onScannerButtonClick()
            }
        ) { barcode -> viewModel.updateBarcode(barcode) }

        CommonButton(text = R.string.add, topPadding = 40.dp, bottomPadding = 22.dp,
            onClick = {
                viewModel.addCard()
                onAddCardButtonClick()
            }
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> LargeDropdownMenu(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String,
    notSetLabel: String? = null,
    items: List<T>,
    selectedIndex: Int = -1,
    onItemSelected: (index: Int, item: T) -> Unit,
    selectedItemToString: (T) -> String = { it.toString() },
    drawItem: @Composable (T, Boolean, Boolean, () -> Unit) -> Unit = { item, selected, itemEnabled, onClick ->
        LargeDropdownMenuItem(
            text = item.toString(),
            selected = selected,
            enabled = itemEnabled,
            onClick = onClick,
        )
    },
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .padding(bottom = 12.dp)
            .height(IntrinsicSize.Min)
    ) {
        Column {

            Text(
                text = label,
                style = Typography.labelSmall,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            OutlinedTextField(
                placeholder = { Text(label) },
                value = items.getOrNull(selectedIndex)?.let { selectedItemToString(it) } ?: "",
                enabled = enabled,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Unspecified,
                    unfocusedBorderColor = Color.Unspecified,
                    cursorColor = Color.Black
                ),
                trailingIcon = {
                    val icon = Icons.Filled.ArrowDropDown
                    Icon(icon, "")
                },
                onValueChange = { },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .border(
                        shape = RoundedCornerShape(10.dp),
                        width = 1.dp,
                        color = Color.LightGray
                    )
            )

        }

        // Transparent clickable surface on top of OutlinedTextField
        Surface(
            modifier = Modifier
                .padding(top = 25.dp)
                .height(54.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxSize()
                .clickable(enabled = enabled) { expanded = true },
            color = Color.Transparent,
        ) {}
    }

    if (expanded) {
        Dialog(
            onDismissRequest = { expanded = false },
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
            ) {
                val listState = rememberLazyListState()
                if (selectedIndex > -1) {
                    LaunchedEffect("ScrollToSelected") {
                        listState.scrollToItem(index = selectedIndex)
                    }
                }

                LazyColumn(modifier = Modifier.fillMaxWidth(), state = listState) {
                    if (notSetLabel != null) {
                        item {
                            LargeDropdownMenuItem(
                                text = notSetLabel,
                                selected = false,
                                enabled = false,
                                onClick = { },
                            )
                        }
                    }
                    itemsIndexed(items) { index, item ->
                        val selectedItem = index == selectedIndex
                        drawItem(
                            item,
                            selectedItem,
                            true
                        ) {
                            onItemSelected(index, item)
                            expanded = false
                        }

                        if (index < items.lastIndex) {
                            Divider(modifier = Modifier.padding(horizontal = 16.dp))
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun LargeDropdownMenuItem(
    text: String,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val contentColor = when {
        !enabled -> MaterialTheme.colorScheme.onSurface.copy()
        selected -> MaterialTheme.colorScheme.primary.copy()
        else -> MaterialTheme.colorScheme.onSurface.copy()
    }

    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Row(modifier = Modifier
            .clickable(enabled) { onClick() }
            .fillMaxWidth()
            .padding(16.dp)) {
            AsyncImage(
                model = "https://listovki.zabava.by/upload/iblock/3f0/3f0b29739dd7672614481f354e0bf336.png",
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 6.dp)
                    .size(24.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}