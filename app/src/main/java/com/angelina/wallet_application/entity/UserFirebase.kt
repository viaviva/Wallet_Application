package com.angelina.wallet_application.entity

import com.google.firebase.database.PropertyName

data class UserFirebase(
    @get:PropertyName("username") @set:PropertyName("username") var username: String = "",
    @get:PropertyName("cards") @set:PropertyName("cards") var cards: List<CardFirebase> = listOf()
)