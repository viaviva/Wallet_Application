package com.angelina.wallet_application.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.angelina.wallet_application.R
import com.angelina.wallet_application.ui.component.BottomText
import com.angelina.wallet_application.ui.component.CommonButton
import com.angelina.wallet_application.ui.component.TextField
import com.angelina.wallet_application.ui.theme.Typography

@Composable
fun RegistrationScreen(
    onHaveAnAccountTextClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
    ) {

        Text(
            text = stringResource(id = R.string.registration),
            style = Typography.titleMedium,
            modifier = Modifier.padding(top = 110.dp, bottom = 38.dp)
        )

        TextField(R.string.your_name, R.string.user_name, 22.dp)
        TextField(R.string.email, R.string.email_example, 22.dp)
        TextField(R.string.inv_pass, R.string.password_validation, 22.dp, true)
        TextField(R.string.check_pass, R.string.repeat_password, 22.dp, true)

        CommonButton(text = R.string.create_acc, topPadding = 38.dp, bottomPadding = 38.dp)

    }

    BottomText(
        R.string.have_an_account, R.string.enter
    ) {
        onHaveAnAccountTextClick()
    }

}