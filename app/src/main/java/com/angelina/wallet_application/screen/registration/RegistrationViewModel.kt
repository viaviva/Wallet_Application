package com.angelina.wallet_application.screen.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angelina.wallet_application.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    var name by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set


    fun updateName(input: String) {
        name = input
    }

    fun updateEmail(input: String) {
        email = input
    }

    fun updatePassword(input: String) {
        password = input
    }

    fun updateConfirmPassword(input: String) {
        confirmPassword = input
    }

    val isLoading = MutableLiveData<Boolean>()

    val error = MutableLiveData<String>()

    var successRegistration: (() -> Unit)? = null

    fun registration() {
        isLoading.value = true
        loginRepository.registration(
            email, password, {
                isLoading.value = false
                successRegistration?.invoke()
            }, {
                isLoading.value = false
                error.value = "Bad credentials"
            }
        )
    }
}