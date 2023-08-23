package com.angelina.wallet_application.screen.authorization

import android.widget.Toast
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.angelina.wallet_application.R
import com.angelina.wallet_application.ui.component.BottomText
import com.angelina.wallet_application.ui.component.CommonButton
import com.angelina.wallet_application.ui.component.SignUpWithButton
import com.angelina.wallet_application.ui.component.TextField
import com.angelina.wallet_application.ui.component.TextWithDivider
import com.angelina.wallet_application.ui.theme.Typography

@ExperimentalGetImage
@Composable
fun AuthorizationScreen(
    onLogInButtonClick: () -> Unit,
    onNoAccountTextClick: () -> Unit,
    viewModel: AuthorizationViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val noInternetConnection = stringResource(id = R.string.no_internet)
    val fillFields = stringResource(id = R.string.fill_fields)
    val incorrectFields = stringResource(id = R.string.incorrect_fields)

    viewModel.run {
        noInternet = { Toast.makeText(context, noInternetConnection, Toast.LENGTH_LONG).show() }
        emptyFields = { Toast.makeText(context, fillFields, Toast.LENGTH_LONG).show() }
        errorData = { Toast.makeText(context, incorrectFields, Toast.LENGTH_LONG).show() }

        successLogin = onLogInButtonClick
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp)
    ) {

        Text(
            text = stringResource(id = R.string.authorization),
            style = Typography.titleMedium,
            modifier = Modifier.padding(top = 110.dp, bottom = 26.dp)
        )

        TextField(
            viewModel.email,
            R.string.email,
            R.string.email_example,
            12.dp
        ) { email -> viewModel.updateEmail(email) }

        TextField(
            viewModel.password,
            R.string.password,
            R.string.password_lower_case,
            isPasswordField = true
        ) { password -> viewModel.updatePassword(password) }

        CommonButton(text = R.string.enter, topPadding = 32.dp, bottomPadding = 22.dp,
            onClick = {
                viewModel.login()
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
    ) { onNoAccountTextClick() }

}