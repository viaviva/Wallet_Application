package com.angelina.wallet_application.screen.authorization

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angelina.wallet_application.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val loginRepository: LoginRepository
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

    val isLoading = MutableLiveData<Boolean>()

    val error = MutableLiveData<String>()

    var successLogin: (() -> Unit)? = null

    fun login(
        email: String,
        password: String
    ) {
        isLoading.value = true
        loginRepository.login(
            email, password, {
            isLoading.value = false
            successLogin?.invoke()
        }, {
            isLoading.value = false
            error.value = "Bad credentials"
        })
    }

}