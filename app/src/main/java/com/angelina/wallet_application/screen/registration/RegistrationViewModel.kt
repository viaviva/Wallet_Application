package com.angelina.wallet_application.screen.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.angelina.wallet_application.repository.LoginRepository
import com.angelina.wallet_application.repository.SharedPreferenceRepository
import com.angelina.wallet_application.validation.textFieldValidation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val sharedPreferenceRepository: SharedPreferenceRepository
) : ViewModel() {

    var name by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    private val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()

    var noInternet: (() -> Unit)? = null

    var emptyFields: (() -> Unit)? = null

    var successRegistration: (() -> Unit)? = null

    var errorData: (() -> Unit)? = null

    fun updateName(input: String) {
        name = input
    }

    fun updateEmail(input: String) {
        email = input
    }

    fun updatePassword(input: String) {
        password = input
    }

    fun registration() {
        if (isFieldsNoEmpty() && errorData()) {
            if (sharedPreferenceRepository.getIsNoInternet()) {
                loginRepository.registration(
                    email, password, name, {
                        successRegistration?.invoke()
                    }, {
                        errorData?.invoke()
                    }
                )
            } else {
                noInternet?.invoke()
            }
        }
    }

    private fun isFieldsNoEmpty(): Boolean {
        return if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            true
        } else {
            emptyFields?.invoke()
            false
        }
    }

    private fun errorData(): Boolean {
        return if (textFieldValidation(password) && textFieldValidation(email) && email.matches(
                emailRegex
            ).not()
        ) {
            errorData?.invoke()
            false
        } else {
            true
        }
    }

}