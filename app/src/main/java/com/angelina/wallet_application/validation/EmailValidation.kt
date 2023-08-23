package com.angelina.wallet_application.validation

private val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()

fun emailValidation(email: String): Boolean {
    return email.matches(emailRegex).not()
}