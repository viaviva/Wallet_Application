package com.angelina.wallet_application.model

data class User(
    var isNoCards: Boolean = true,
    val cards: List<Card>? = listOf(),
    val username: String
)
