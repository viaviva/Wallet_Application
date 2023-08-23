package com.angelina.wallet_application.screen.authorization

import android.widget.Toast
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.angelina.wallet_application.R
import com.angelina.wallet_application.ui.component.BottomText
import com.angelina.wallet_application.ui.component.CommonButton
import com.angelina.wallet_application.ui.component.TextField
import com.angelina.wallet_application.ui.theme.Dimens
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
            .padding(horizontal = Dimens.sdp_22)
    ) {

        Text(
            text = stringResource(id = R.string.authorization),
            style = Typography.titleMedium,
            modifier = Modifier.padding(top = Dimens.sdp_110, bottom = Dimens.sdp_26)
        )

        TextField(
            viewModel.email,
            R.string.email,
            R.string.email_example,
            Dimens.dp_12
        ) { email -> viewModel.updateEmail(email) }

        TextField(
            viewModel.password,
            R.string.password,
            R.string.password_lower_case,
            isPasswordField = true
        ) { password -> viewModel.updatePassword(password) }

        CommonButton(text = R.string.enter, topPadding = Dimens.sdp_32, bottomPadding = Dimens.sdp_22,
            onClick = {
                viewModel.login()
            }
        )

    }

    BottomText(
        R.string.no_account,
        R.string.sign_up
    ) { onNoAccountTextClick() }

}