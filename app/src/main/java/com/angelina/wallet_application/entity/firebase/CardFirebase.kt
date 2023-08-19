package com.angelina.wallet_application.entity.firebase

import com.google.firebase.database.PropertyName

data class CardFirebase(
    @get:PropertyName("idCard") @set:PropertyName("idCard") var idCard: Long = 0,
    @get:PropertyName("idShop") @set:PropertyName("idShop") var idShop: Long = 0,
    @get:PropertyName("cardNumber") @set:PropertyName("cardNumber") var cardNumber: String = "",
    @get:PropertyName("countOfClicks") @set:PropertyName("countOfClicks") var countOfClicks: Long = 0
)