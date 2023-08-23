package com.angelina.wallet_application.screen.registration

import android.widget.Toast
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.angelina.wallet_application.R
import com.angelina.wallet_application.ui.component.BottomText
import com.angelina.wallet_application.ui.component.CommonButton
import com.angelina.wallet_application.ui.component.TextField
import com.angelina.wallet_application.ui.theme.Typography

@ExperimentalGetImage
@Composable
fun RegistrationScreen(
    onHaveAnAccountTextClick: () -> Unit,
    onRegistrationButtonClick: () -> Unit,
    viewModel: RegistrationViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val noInternetConnection = stringResource(id = R.string.no_internet)
    val fillFields = stringResource(id = R.string.fill_fields)
    val incorrectFields = stringResource(id = R.string.incorrect_fields)

    viewModel.run {
        noInternet = { Toast.makeText(context, noInternetConnection, Toast.LENGTH_LONG).show() }
        emptyFields = { Toast.makeText(context, fillFields, Toast.LENGTH_LONG).show() }
        errorData = { Toast.makeText(context, incorrectFields, Toast.LENGTH_LONG).show() }

        successRegistration = onRegistrationButtonClick
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp)
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
            ) { updateName(it) }

            TextField(
                email,
                R.string.email,
                R.string.email_example,
                12.dp,
            ) { updateEmail(it) }

            TextField(
                password,
                R.string.inv_pass,
                R.string.password_validation,
                12.dp,
                true
            ) { updatePassword(it) }

            TextField(
                confirmPassword,
                R.string.check_pass,
                R.string.repeat_password,
                isPasswordField = true
            ) { updateConfirmPassword(it) }

            CommonButton(
                onClick = { registration() },
                text = R.string.create_acc,
                topPadding = 32.dp,
                bottomPadding = 38.dp
            )
        }

    }

    BottomText(
        R.string.have_an_account, R.string.enter
    ) { onHaveAnAccountTextClick() }

}