package com.angelina.wallet_application.screen.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.angelina.wallet_application.R
import com.angelina.wallet_application.ui.theme.Dimens
import com.angelina.wallet_application.ui.theme.Typography

@Composable
fun ProfileScreen(
    onLogOutClick: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    var expanded by remember { mutableStateOf(false) }
    val countOfCards = viewModel.countOfCards.observeAsState()

    viewModel.getCountOfCards()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.sdp_22)
    ) {

        Box(
            modifier = Modifier
                .padding(vertical = Dimens.dp_20)
                .align(Alignment.End)
        ) {

            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Text(
                    stringResource(id = R.string.delete),
                    fontSize = Dimens.sp_18,
                    modifier = Modifier
                        .padding(horizontal = Dimens.sdp_10)
                        .clickable(onClick = {
                            onLogOutClick()
                            viewModel.deleteUser()
                        })
                )

                Divider(modifier = Modifier.padding(vertical = Dimens.dp_12))

                Text(
                    stringResource(id = R.string.log_out),
                    fontSize = Dimens.sp_18,
                    modifier = Modifier
                        .padding(horizontal = Dimens.sdp_10)
                        .clickable(onClick = {
                            onLogOutClick()
                            viewModel.logOut()
                        })
                )
            }
        }

        Text(
            text = stringResource(id = R.string.profile),
            style = Typography.titleMedium,
            modifier = Modifier.padding(top = Dimens.sdp_28, bottom = Dimens.dp_20)
        )

        UserData(
            username = viewModel.getUsername(),
            email = viewModel.getEmail(),
            countOfCards = countOfCards.value.toString()
        )

    }

}

@Composable
fun UserData(
    username: String,
    email: String,
    countOfCards: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(Dimens.dp_20),
        shadowElevation = Dimens.sdp_6,
        color = Color.White
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = Dimens.dp_16)
                .padding(vertical = Dimens.dp_16)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.username),
                    style = Typography.titleMedium,
                    fontSize = Dimens.sp_18,
                    modifier = Modifier.padding(bottom = Dimens.dp_12)
                )

                Text(
                    text = username,
                    style = Typography.titleSmall,
                    modifier = Modifier.padding(bottom = Dimens.dp_12)
                )
            }

            Divider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.email_profile),
                    style = Typography.titleMedium,
                    fontSize = Dimens.sp_18,
                    modifier = Modifier.padding(vertical = Dimens.dp_12)
                )

                Text(
                    text = email,
                    style = Typography.titleSmall,
                    modifier = Modifier.padding(vertical = Dimens.dp_12)
                )
            }

            Divider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.cards),
                    style = Typography.titleMedium,
                    fontSize = Dimens.sp_18,
                    modifier = Modifier.padding(top = Dimens.dp_12)
                )

                Text(
                    text = countOfCards,
                    style = Typography.titleSmall,
                    modifier = Modifier.padding(top = Dimens.dp_12)
                )
            }

        }

    }
}