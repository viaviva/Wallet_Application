package com.angelina.wallet_application.repository

import android.util.Log
import com.angelina.wallet_application.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: DatabaseReference,
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (error: Exception?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser

                user?.let {
                    sharedPreferenceRepository.run {
                        setIsUserLogIn(true)
                        setEmail(it.email.toString())
                        setUserId(it.uid)
                    }

                    database.child("users").child(it.uid).child("username").get()
                        .addOnSuccessListener { username ->
                            sharedPreferenceRepository.setUsername(username.value.toString())
                        }.addOnFailureListener {
                            Log.e("firebase", "Error getting data")
                        }

                    database.child("users").child(it.uid).child("noCards").get()
                        .addOnSuccessListener { noCards ->
                            sharedPreferenceRepository.setNoCards(noCards.getValue<Boolean>() == true)
                            Log.e("firebase", sharedPreferenceRepository.getNoCards().toString())
                        }.addOnFailureListener {
                            Log.e("firebase", "Error getting data")
                        }
                }

                onSuccess()
            } else {
                onError(task.exception)
            }
        }
    }

    fun registration(
        email: String,
        password: String,
        username: String,
        onSuccess: () -> Unit,
        onError: (error: Exception?) -> Unit
    ) {
        Log.e("USER", "$email $password $username")

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser

                user?.let {
                    sharedPreferenceRepository.run {
                        setIsUserLogIn(true)
                        setUserId(it.uid)
                        setEmail(it.email.toString())
                        setNoCards(true)
                    }

                    database.child("users").child(it.uid).setValue(User(username = username))
                        .addOnSuccessListener {
                            sharedPreferenceRepository.setUsername(username)
                            Log.e("SP", sharedPreferenceRepository.getUsername())

                            Log.i("firebase", "Got value")
                        }.addOnFailureListener {
                            Log.e("firebase", "Error getting data")
                        }
                }

                onSuccess()
            } else {
                onError(task.exception)
            }
        }
    }

    fun logOut() {
        auth.signOut()
        sharedPreferenceRepository.clearUserPreference()
    }

    fun deleteUser() {
        auth.currentUser!!.delete().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("DELETE USER", "User account deleted.")
            }
        }
        sharedPreferenceRepository.clearUserPreference()
    }

}