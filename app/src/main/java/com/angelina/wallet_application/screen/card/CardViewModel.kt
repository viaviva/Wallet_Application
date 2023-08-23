package com.angelina.wallet_application.screen.card

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelina.wallet_application.model.Card
import com.angelina.wallet_application.repository.CardRepository
import com.angelina.wallet_application.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val shopRepository: ShopRepository
) : ViewModel() {

    val card = MutableLiveData<Card>()

    private val listOfShops = shopRepository.listOfShops

    fun getCard(id: Long) {
        viewModelScope.launch {
            card.postValue(cardRepository.getCard(id.toString()))
        }
    }

    fun getShopImage(id: Long) = listOfShops.find { it.id == id }?.imageUrl ?: ""

    fun deleteCard(id: Long) {
        viewModelScope.launch {
            cardRepository.deleteCard(id)
        }
    }

}