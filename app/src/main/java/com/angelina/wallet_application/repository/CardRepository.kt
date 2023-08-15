package com.angelina.wallet_application.repository

import android.util.Log
import com.angelina.wallet_application.entity.ShopFirebase
import com.angelina.wallet_application.entity.UserFirebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import javax.inject.Inject

class CardRepository @Inject constructor(
    private val database: DatabaseReference,
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    fun getUser(): UserFirebase? {
        var user: UserFirebase? = null

        database.child("users").child(sharedPreferenceRepository.getUserId()).get().addOnSuccessListener {
            user = it.getValue<UserFirebase>()
            Log.i("firebase", "Got value" + user.toString())

        }.addOnFailureListener {
            Log.e("firebase", "Error getting data")
        }

        Log.i("firebase", user.toString())
        return user
    }

//    fun getShopById(
//        id: Long?
//    ): ShopFirebase? {
//        var shop: ShopFirebase? = null
//
//        database.child("shops").child(id.toString()).get().addOnSuccessListener { it ->
//            shop = it.getValue<ShopFirebase>()
//        }.addOnFailureListener {
//            Log.e("firebase", "Error getting data")
//        }
//
//        return shop
//    }
}