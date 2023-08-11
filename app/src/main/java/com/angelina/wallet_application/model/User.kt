package com.angelina.wallet_application.model

data class User(
    val idUser: String,
    val username: String,
    val cards: List<Card> = listOf()
)
