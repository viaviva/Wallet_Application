package com.angelina.wallet_application.model

data class Card(
    val idCard: String,
    val idShop: String,
    val cardNumber: String,
    val countOfClicks: Int = 0,
    val idUser: String
)
