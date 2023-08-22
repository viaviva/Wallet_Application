package com.angelina.wallet_application.screen.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelina.wallet_application.repository.CardRepository
import com.angelina.wallet_application.repository.LoginRepository
import com.angelina.wallet_application.repository.SharedPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sharedPreferenceRepository: SharedPreferenceRepository,
    private val cardRepository: CardRepository,
    private val loginRepository: LoginRepository
) : ViewModel() {

    var countOfCards = MutableLiveData<String>("0")

    fun getUsername() = sharedPreferenceRepository.getUsername()

    fun getEmail() = sharedPreferenceRepository.getEmail()

    fun getCountOfCards() {
        viewModelScope.launch {
            if (!sharedPreferenceRepository.getNoCards()) {
                countOfCards.postValue(cardRepository.getAllCards().size.toString())
            }
        }
    }

    fun logOut() {
        loginRepository.logOut()
        deleteCardsFromRoom()
    }

    fun deleteUser() {
        loginRepository.deleteUser()
        deleteCardsFromRoom()
    }

    private fun deleteCardsFromRoom() = viewModelScope.launch {
        cardRepository.deleteAllCards()
    }

}