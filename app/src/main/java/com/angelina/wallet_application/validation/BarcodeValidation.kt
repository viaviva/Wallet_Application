package com.angelina.wallet_application.validation

fun barcodeValidation(barcode: String) : Boolean = barcode.toLongOrNull() == null