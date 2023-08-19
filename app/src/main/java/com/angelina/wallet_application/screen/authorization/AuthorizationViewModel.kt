package com.angelina.wallet_application.screen.authorization

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelina.wallet_application.repository.CardRepository
import com.angelina.wallet_application.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val cardRepository: CardRepository,
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateEmail(input: String) {
        email = input
    }

    fun updatePassword(input: String) {
        password = input
    }


    val error = MutableLiveData<String>()

    var successLogin: (() -> Unit)? = null

    fun login() {
        loginRepository.login(email, password, {
            getCardsFromFirebase()
            successLogin?.invoke()
            Log.e("firebase", "OK 2")
        }, {
            error.value = "Bad credentials"
        })
    }

    private fun getCardsFromFirebase() {
        viewModelScope.launch(Dispatchers.IO) {
            cardRepository.getCardsFromFirebase()
        }
    }
}
