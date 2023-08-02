package com.angelina.wallet_application.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.angelina.wallet_application.R
import com.angelina.wallet_application.ui.theme.BorderTextFieldColor
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
            .padding(bottom = 58.dp)
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
@Composable
fun TextField(
    labelText: Int,
    textFieldText: Int,
    textFieldBottomPadding: Dp,
    isPasswordField: Boolean = false,
    isVerified: Boolean = false
) {

    var text by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var hasFocus by remember { mutableStateOf(false) }

    Text(
        text = stringResource(id = labelText),
        style = Typography.labelSmall,
        modifier = Modifier.padding(bottom = 6.dp)
    )

    OutlinedTextField(
        value = text, onValueChange = {
            text = it
        },
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

            if (isVerified) {
                val image = R.drawable.ic_verified

                IconButton(onClick = {}) {
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
            .height(56.dp)
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
            cursorColor = Color.Black
        )
    )

}

@Composable
fun CommonButton(
    onClick: () -> Unit = {},
    topPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp,
    containerColor: Color = Color.Black,
    contentColor: Color = Color.White,
    borderColor: Color = Color.Black,
    text: Int
) {

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(top = topPadding, bottom = bottomPadding)
            .fillMaxWidth()
            .height(56.dp),
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
fun TextWithDivider() {
    Divider(
        color = BorderTextFieldColor,
        thickness = 1.dp,
        modifier = Modifier.width(138.dp)
    )
    Text(
        text = "или",
        style = Typography.labelSmall,
        modifier = Modifier.padding(horizontal = 10.dp),
    )
    Divider(
        color = BorderTextFieldColor,
        thickness = 1.dp,
        modifier = Modifier.width(138.dp)
    )
}

@Composable
fun SignUpWithButton(
    icon: Int
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 1.dp, color = BorderTextFieldColor),
        color = Color.White,
        modifier = Modifier.size(100.dp, 52.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier.padding(horizontal = 40.dp, vertical = 16.dp)
        )
    }
}

@Composable
fun BackArrow(
    onClick: () -> Unit = {},
    color: Color
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        color = color,
        modifier = Modifier
            .size(52.dp, 36.dp)
            .clickable(onClick = onClick)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(id = R.string.google_icon),
            tint = Color.Unspecified,
            modifier = Modifier.absolutePadding(9.dp, 9.dp, 9.dp, 9.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextCode() {

    var hasFocus by remember { mutableStateOf(false) }
    var num by remember { mutableStateOf(TextFieldValue("")) }
    val mMaxLength = 1

    OutlinedTextField(
        singleLine = true,
        value = num,
        onValueChange = {
            if (it.text.length <= mMaxLength) num = it
        },
        modifier = Modifier
            .size(width = 71.dp, height = 70.dp)
            .border(
                shape = RoundedCornerShape(10.dp),
                width = 1.dp,
                color = if (hasFocus) Color.Black else Color.LightGray

            )
            .onFocusChanged { focusState -> hasFocus = focusState.hasFocus },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Unspecified,
            unfocusedBorderColor = Color.Unspecified,
            cursorColor = Color.Black
        ),
        textStyle = TextStyle(
            fontSize = 30.sp, color = Color.Black, lineHeight = 35.7.sp,
            fontFamily = textFontFamily,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )
    )

}

@Composable
fun ButtonMainScreen(onClick: () -> Unit = {}, color: Int, icon: Int) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = colorResource(id = color),
        modifier = Modifier
            .size(52.dp, 36.dp)
            .clickable(onClick = onClick)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = R.string.google_icon),
            tint = Color.Unspecified,
            modifier = Modifier.absolutePadding(6.dp, 6.dp, 6.dp, 6.dp)
        )
    }
}