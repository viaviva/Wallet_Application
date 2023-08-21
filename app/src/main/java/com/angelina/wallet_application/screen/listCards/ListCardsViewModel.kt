package com.angelina.wallet_application.screen.listCards

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelina.wallet_application.entity.firebase.ShopFirebase
import com.angelina.wallet_application.model.Card
import com.angelina.wallet_application.repository.CardRepository
import com.angelina.wallet_application.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class ListCardsViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val shopRepository: ShopRepository,
) : ViewModel() {

    val listOfCards = MutableLiveData<List<Card>>()

    private val listOfShops = shopRepository.listOfShops

    fun getAllCards() {
        viewModelScope.async {
            listOfCards.postValue(cardRepository.getAllCards())
            Log.e("listOfCards.toString()", listOfCards.toString())
        }.onAwait
    }

    fun getShop(id: Long) = listOfShops.find { it.id == id } ?: ShopFirebase()

}