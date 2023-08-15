package com.angelina.wallet_application.screen.listCards

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelina.wallet_application.entity.CardFirebase
import com.angelina.wallet_application.entity.UserFirebase
import com.angelina.wallet_application.repository.CardRepository
import com.angelina.wallet_application.repository.SharedPreferenceRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCardsViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val database: DatabaseReference,
    private val sharedPreferenceRepository: SharedPreferenceRepository
) : ViewModel() {

    val listOfCards = MutableLiveData<List<CardFirebase>>()

    fun getUserCards() {
        viewModelScope.launch(Dispatchers.IO) {
            database.child("users").child(sharedPreferenceRepository.getUserId()).get().addOnSuccessListener {
                listOfCards.postValue(it.getValue<UserFirebase>()?.cards)
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data")
            }
        }
    }

    fun getShopImageById(id: Long?): String {
        var image = ""
        Log.e("firebase", id.toString())
        viewModelScope.launch(Dispatchers.IO) {
            database.child("shops").child(id.toString()).child("imageUrl").get().addOnSuccessListener {
                image = it.value.toString()
                Log.e("firebase", image)
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data")
            }
        }
        return image
    }
}