package com.angelina.wallet_application.model

data class Card(
    val idCard: Long,
    val idShop: Long,
    val cardNumber: String,
    val countOfClicks: Long = 0
)