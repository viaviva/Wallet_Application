package com.angelina.wallet_application.screen.authorization

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelina.wallet_application.repository.CardRepository
import com.angelina.wallet_application.repository.LoginRepository
import com.angelina.wallet_application.repository.SharedPreferenceRepository
import com.angelina.wallet_application.validation.emailValidation
import com.angelina.wallet_application.validation.textFieldValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val cardRepository: CardRepository,
    private val sharedPreferenceRepository: SharedPreferenceRepository
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var successLogin: (() -> Unit)? = null

    var noInternet: (() -> Unit)? = null

    var errorData: (() -> Unit)? = null

    var emptyFields: (() -> Unit)? = null

    fun updateEmail(input: String) {
        email = input
    }

    fun updatePassword(input: String) {
        password = input
    }

    fun login() {
        if (isFieldsNoEmpty() && errorData()) {
            if (sharedPreferenceRepository.getIsNoInternet()) {
                loginRepository.login(email, password, {
                    getCardsFromFirebase()
                    successLogin?.invoke()
                }, {
                    errorData?.invoke()
                })
            } else {
                noInternet?.invoke()
            }
        }
    }

    private fun getCardsFromFirebase() {
        viewModelScope.launch(Dispatchers.IO) {
            cardRepository.getCardsFromFirebase()
        }
    }

    private fun isFieldsNoEmpty(): Boolean {
        return if (email.isNotEmpty() && password.isNotEmpty()) {
            true
        } else {
            emptyFields?.invoke()
            false
        }
    }

    private fun errorData(): Boolean {
        return if (emailValidation(email) && textFieldValidation(password)) {
            errorData?.invoke()
            false
        } else {
            true
        }
    }

}
