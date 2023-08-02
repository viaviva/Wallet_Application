package com.angelina.wallet_application.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.angelina.wallet_application.R
import com.angelina.wallet_application.ui.component.BottomText
import com.angelina.wallet_application.ui.component.CommonButton
import com.angelina.wallet_application.ui.component.SignUpWithButton
import com.angelina.wallet_application.ui.component.TextField
import com.angelina.wallet_application.ui.component.TextWithDivider
import com.angelina.wallet_application.ui.theme.Typography

@Composable
fun AuthorizationScreen(
    onForgetPasswordTextClick: () -> Unit,
    onLogInTextClick: () -> Unit,
    onNoAccountTextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 26.dp)
    ) {

        Text(
            text = stringResource(id = R.string.authorization),
            style = Typography.titleMedium,
            modifier = Modifier.padding(top = 110.dp, bottom = 38.dp)
        )

        TextField(R.string.email, R.string.email_example, 22.dp, isVerified = true)
        TextField(R.string.password, R.string.password_lower_case, 16.dp, true)

        CommonButton(text = R.string.enter, topPadding = 38.dp, bottomPadding = 38.dp,
            onClick = {
                onLogInTextClick()
            }
        )


        Row(verticalAlignment = Alignment.CenterVertically) { TextWithDivider() }

        Row(
            modifier = Modifier.padding(top = 22.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SignUpWithButton(R.drawable.ic_facebook)
            SignUpWithButton(R.drawable.ic_google)
            SignUpWithButton(R.drawable.ic_apple)
        }

    }

    BottomText(
        R.string.no_account,
        R.string.sign_up
    ) {
        onNoAccountTextClick()
    }

}