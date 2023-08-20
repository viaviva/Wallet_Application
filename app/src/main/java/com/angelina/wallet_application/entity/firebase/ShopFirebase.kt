package com.angelina.wallet_application.entity.firebase

import com.google.firebase.database.PropertyName

data class ShopFirebase(
    @get:PropertyName("imageUrl") @set:PropertyName("imageUrl") var imageUrl: String = "",
    @get:PropertyName("name") @set:PropertyName("name") var name: String = "",
    @get:PropertyName("id") @set:PropertyName("id") var id: Long = 0,
    @get:PropertyName("logoImage") @set:PropertyName("logoImage") var logoImage: String = "",
    @get:PropertyName("color") @set:PropertyName("backgroundImage") var color: Long = 0,
)