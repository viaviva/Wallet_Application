package com.angelina.wallet_application.entity

import com.google.firebase.database.PropertyName

data class CardFirebase(
    @get:PropertyName("idCard") @set:PropertyName("idCard") var idCard: Long? = null,
    @get:PropertyName("idShop") @set:PropertyName("idShop") var idShop: Long? = null,
    @get:PropertyName("cardNumber") @set:PropertyName("cardNumber") var cardNumber: String = "",
    @get:PropertyName("countOfClicks") @set:PropertyName("countOfClicks") var countOfClicks: Long? = null
)