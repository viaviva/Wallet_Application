package com.angelina.wallet_application.screen.listCards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var stateRefresh by mutableStateOf(false)

    private val listOfShops = shopRepository.listOfShops

    fun getAllCards() {
        stateRefresh = true
        viewModelScope.async {
            listOfCards.postValue(cardRepository.getAllCards())
            stateRefresh = false
        }.onAwait
    }

    fun getShop(id: Long) = listOfShops.find { it.id == id } ?: ShopFirebase()

}