package com.angelina.wallet_application.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.angelina.wallet_application.R
import com.angelina.wallet_application.screen.registration.RegistrationViewModel
import com.angelina.wallet_application.ui.component.BottomText
import com.angelina.wallet_application.ui.component.CommonButton
import com.angelina.wallet_application.ui.component.TextField
import com.angelina.wallet_application.ui.theme.Typography

@Composable
fun RegistrationScreen(
    onHaveAnAccountTextClick: () -> Unit,
    onRegistrationButtonClick: () -> Unit,
    viewModel: RegistrationViewModel = hiltViewModel()
) {

    viewModel.successRegistration = onRegistrationButtonClick
    val error = viewModel.error.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
    ) {

        Text(
            text = stringResource(id = R.string.registration),
            style = Typography.titleMedium,
            modifier = Modifier.padding(top = 76.dp, bottom = 26.dp)
        )

        viewModel.run {

            TextField(
                name,
                R.string.your_name,
                R.string.user_name,
                12.dp,
            ) { name -> updateName(name) }

            TextField(
                email,
                R.string.email,
                R.string.email_example,
                12.dp
            ) { email -> updateEmail(email) }

            TextField(
                password,
                R.string.inv_pass,
                R.string.password_validation,
                12.dp,
                true
            ) { email -> updateEmail(email) }

            TextField(
                confirmPassword,
                R.string.check_pass,
                R.string.repeat_password,
                isPasswordField = true
            ) { confirmPassword -> updateConfirmPassword(confirmPassword) }

        }

        CommonButton(
            onClick = {
                viewModel.registration()
            },
            text = R.string.create_acc,
            topPadding = 32.dp,
            bottomPadding = 38.dp
        )

    }

    BottomText(
        R.string.have_an_account, R.string.enter
    ) {
        onHaveAnAccountTextClick()
    }

}