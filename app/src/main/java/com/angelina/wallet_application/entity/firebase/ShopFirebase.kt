package com.angelina.wallet_application.entity.firebase

import com.google.firebase.database.PropertyName

data class ShopFirebase(
    @get:PropertyName("imageUrl") @set:PropertyName("imageUrl") var imageUrl: String = "",
    @get:PropertyName("name") @set:PropertyName("name") var name: String = "",
    @get:PropertyName("id") @set:PropertyName("id") var id: Long = 0
)