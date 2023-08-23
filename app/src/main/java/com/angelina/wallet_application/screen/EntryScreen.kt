package com.angelina.wallet_application.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.angelina.wallet_application.R
import com.angelina.wallet_application.ui.component.CommonButton
import com.angelina.wallet_application.ui.theme.BorderButtonColor
import com.angelina.wallet_application.ui.theme.Dimens
import com.angelina.wallet_application.ui.theme.Typography

@Composable
fun EntryScreen(
    onAuthButtonClick: () -> Unit,
    onRegButtonClick: () -> Unit
) {

    Column(

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = Dimens.sdp_26)
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.main_image),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(id = R.string.application_name),
            style = Typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = Dimens.dp_18, bottom = Dimens.sdp_22)
        )

        Text(
            text = stringResource(id = R.string.slogan_text),
            style = Typography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = Dimens.sdp_54)
                .width(Dimens.sdp_260)
        )

        CommonButton(
            onClick = {
                onAuthButtonClick()
            },
            text = R.string.authorization
        )

        CommonButton(
            onClick = {
                onRegButtonClick()
            },
            topPadding = Dimens.dp_16,
            containerColor = Color.White,
            contentColor = Color.Black,
            borderColor = BorderButtonColor,
            text = R.string.registration
        )

    }

}