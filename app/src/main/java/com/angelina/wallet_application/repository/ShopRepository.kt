package com.angelina.wallet_application.repository

import android.util.Log
import com.angelina.wallet_application.entity.firebase.ShopFirebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShopRepository @Inject constructor(
    private val database: DatabaseReference
) {

    val listOfShops = arrayListOf<ShopFirebase>()

    init {
        database.child("shops").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    for (i in p0.children) {
                        val itm = i.getValue<ShopFirebase>()
                        listOfShops.add(itm!!)
                    }
                } else {
                    Log.e("NEW", "Unknown error occurred")
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

}