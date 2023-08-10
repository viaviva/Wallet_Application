package com.angelina.wallet_application.repository

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val auth: FirebaseAuth
) {

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (error: Exception?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                onError(task.exception)
            }
        }
    }

    fun registration(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (error: Exception?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                onError(task.exception)
            }
        }
    }
}