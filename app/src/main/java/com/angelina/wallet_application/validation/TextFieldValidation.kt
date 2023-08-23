package com.angelina.wallet_application.validation

fun textFieldValidation(input: String): Boolean{
    return input.length < 6
}