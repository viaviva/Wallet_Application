package com.angelina.wallet_application.screen.addCard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelina.wallet_application.model.Card
import com.angelina.wallet_application.repository.CardRepository
import com.angelina.wallet_application.repository.SharedPreferenceRepository
import com.angelina.wallet_application.repository.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val sharedPreferenceRepository: SharedPreferenceRepository,
    private val shopRepository: ShopRepository,
    private val cardRepository: CardRepository
) : ViewModel() {

    val listOfShops = arrayListOf<String>()

    var countOfCards = MutableLiveData<Int>()

    var barcode by mutableStateOf("")
        private set

    var shop by mutableStateOf(-1)
        private set

    init {
        shopRepository.listOfShops.forEach {
            listOfShops.add(it.name)
        }
    }

    fun getCountOfCards() {
        viewModelScope.launch {
            countOfCards.postValue(cardRepository.getCountOfCards())
        }
    }

    fun updateBarcode(input: String) {
        barcode = input
    }

    fun updateShop(id: Int) {
        shop = id
    }

    fun setBarcodeFromScanner(barcode: String) {
        sharedPreferenceRepository.run {
            if (getScanner()) {
                updateShop(getShop())
                updateBarcode(barcode)

                setScanner(false)
                setShop(-1)
            }
        }
    }

    fun setShop() = sharedPreferenceRepository.setShop(shop)

    fun addCard() {
        viewModelScope.launch {
            if (countOfCards.value != null) {
                cardRepository.addCard(
                    Card(
                        countOfCards.value!!.toLong(),
                        shop.toLong(),
                        barcode
                    )
                )
            }
        }
    }

}