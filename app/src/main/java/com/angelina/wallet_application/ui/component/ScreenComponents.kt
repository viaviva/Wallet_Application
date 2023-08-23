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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.angelina.wallet_application.R
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
            .padding(bottom = 46.dp)
            .fillMaxSize()
    ) {

        Text(
            text = stringResource(id = text),
            style = Typography.labelSmall,
            modifier = Modifier.padding(end = 3.dp)
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
    textFieldBottomPadding: Dp = 0.dp,
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
        modifier = Modifier.padding(bottom = 6.dp)
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
            .height(54.dp)
            .border(
                shape = RoundedCornerShape(10.dp),
                width = 1.dp,
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
    topPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp,
    containerColor: Color = Purple40,
    contentColor: Color = Color.White,
    borderColor: Color = Purple40,
    text: Int
) {

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(top = topPadding, bottom = bottomPadding)
            .fillMaxWidth()
            .height(54.dp),
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text = stringResource(id = text),
            fontFamily = textFontFamily,
            fontSize = 16.sp
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
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 1.dp, color = color),
        color = color,
        modifier = Modifier
            .size(40.dp, 40.dp)
            .clickable(onClick = onClick)
    ) {
        Icon(
            painter = painterResource(id = arrow),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier.padding(9.dp, 9.dp, 9.dp, 9.dp)
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
                    .padding(end = 10.dp)
                    .clickable {
                        onDismissButtonClick()
                    }
            )
        }
    )
}

