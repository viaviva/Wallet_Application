package com.angelina.wallet_application.ui.component

import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import com.angelina.wallet_application.R
import com.angelina.wallet_application.ui.theme.Dimens
import com.angelina.wallet_application.ui.theme.Purple40
import com.angelina.wallet_application.ui.theme.Typography
import com.angelina.wallet_application.ui.theme.textFontFamily

@Composable
fun BottomText(
    text: Int,
    clickableText: Int,
    onClick: () -> Unit = {}
) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .padding(bottom = Dimens.sdp_46)
            .fillMaxSize()
    ) {

        Text(
            text = stringResource(id = text),
            style = Typography.labelSmall,
            modifier = Modifier.padding(end = Dimens.sdp_2)
        )

        Text(
            text = stringResource(id = clickableText),
            style = Typography.labelSmall,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable(onClick = onClick)
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalGetImage
@Composable
fun TextField(
    text: String,
    labelText: Int,
    textFieldText: Int,
    textFieldBottomPadding: Dp = Dimens.sdp_0,
    isPasswordField: Boolean = false,
    isScanner: Boolean = false,
    isError: Boolean = false,
    onScannerButtonClick: () -> Unit = {},
    onValueChange: (String) -> Unit,
) {

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var hasFocus by remember { mutableStateOf(false) }

    Text(
        text = stringResource(id = labelText),
        style = Typography.labelSmall,
        modifier = Modifier.padding(bottom = Dimens.sdp_6)
    )

    OutlinedTextField(
        value = text, onValueChange = {
            onValueChange(it)
        },
        isError = isError,
        placeholder = {
            Text(text = stringResource(id = textFieldText))
        },
        visualTransformation = if (passwordVisible || !isPasswordField) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            if (isPasswordField) {
                val image = if (passwordVisible) R.drawable.ic_visibility
                else R.drawable.ic_visibility_off

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(id = image),
                        contentDescription = ""
                    )
                }
            }

            if (isScanner) {
                val image = R.drawable.ic_scanner2

                IconButton(
                    onClick = onScannerButtonClick
                ) {
                    Icon(
                        painter = painterResource(id = image),
                        contentDescription = ""
                    )
                }
            }
        },
        modifier = Modifier
            .padding(bottom = textFieldBottomPadding)
            .fillMaxWidth()
            .height(Dimens.sdp_54)
            .border(
                shape = RoundedCornerShape(Dimens.sdp_10),
                width = Dimens.sdp_1,
                color = if (hasFocus) Color.Black else Color.LightGray
            )
            .onFocusChanged { focusState -> hasFocus = focusState.hasFocus },
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Unspecified,
            unfocusedBorderColor = Color.Unspecified,
            cursorColor = Color.Black,
            errorBorderColor = Color.Red
        )
    )

}

@Composable
fun CommonButton(
    onClick: () -> Unit = {},
    topPadding: Dp = Dimens.sdp_0,
    bottomPadding: Dp = Dimens.sdp_0,
    containerColor: Color = Purple40,
    contentColor: Color = Color.White,
    borderColor: Color = Purple40,
    text: Int
) {

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(Dimens.sdp_10),
        modifier = Modifier
            .padding(top = topPadding, bottom = bottomPadding)
            .fillMaxWidth()
            .height(Dimens.sdp_54),
        border = BorderStroke(Dimens.sdp_1, borderColor),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text = stringResource(id = text),
            fontFamily = textFontFamily,
            fontSize = Dimens.sp_16
        )
    }

}

@Composable
fun BackArrow(
    onClick: () -> Unit = {},
    color: Color = Color.Black,
    arrow: Int = R.drawable.ic_arrow_white
) {
    Surface(
        shape = RoundedCornerShape(Dimens.sdp_10),
        border = BorderStroke(width = Dimens.sdp_1, color = color),
        color = color,
        modifier = Modifier
            .size(Dimens.sdp_40, Dimens.sdp_40)
            .clickable(onClick = onClick)
    ) {
        Icon(
            painter = painterResource(id = arrow),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier.padding(Dimens.sdp_10)
        )
    }
}

@Composable
fun DeleteAlertDialog(
    onDismissButtonClick: () -> Unit,
    onConfirmButtonClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissButtonClick,
        title = {
            Text(
                text = stringResource(id = R.string.confirm_delete),
                style = Typography.titleSmall,
            )
        },
        confirmButton = {
            Text(
                text = stringResource(R.string.yes),
                style = Typography.titleSmall,
                modifier = Modifier.clickable {
                    onConfirmButtonClick()
                }
            )
        },
        dismissButton = {
            Text(
                text = stringResource(R.string.no),
                style = Typography.titleSmall,
                modifier = Modifier
                    .padding(end = Dimens.sdp_10)
                    .clickable {
                        onDismissButtonClick()
                    }
            )
        }
    )
}

