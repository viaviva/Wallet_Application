package com.angelina.wallet_application.entity

import com.google.firebase.database.PropertyName

class ShopFirebase(
    @get:PropertyName("imageUrl") @set:PropertyName("imageUrl") var imageUrl: String = "",
    @get:PropertyName("name") @set:PropertyName("name") var name: String = ""
)