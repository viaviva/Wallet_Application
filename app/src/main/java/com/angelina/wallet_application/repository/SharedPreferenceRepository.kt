package com.angelina.wallet_application.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val USER_PREF_FILE = "userPrefFile"

private const val USERNAME = "username"
private const val DEFAULT_USERNAME = "username"

private const val USER_ID = "user_id"
private const val DEFAULT_USER_ID = "user_id"

@Singleton
class SharedPreferenceRepository @Inject constructor(
    @ApplicationContext context: Context
) {

    private var userPreferences: SharedPreferences? = null

    init {
        userPreferences = context.getSharedPreferences(USER_PREF_FILE, Context.MODE_PRIVATE)
    }

    fun setUsername(username: String) {
        userPreferences?.edit {
            putString(USERNAME, username)
        }
    }

    fun getUsername(): String =
        userPreferences?.getString(USERNAME, DEFAULT_USERNAME) ?: DEFAULT_USERNAME

    fun setUserId(id: String) {
        userPreferences?.edit {
            putString(USER_ID, id)
        }
    }

    fun getUserId(): String =
        userPreferences?.getString(USER_ID, DEFAULT_USER_ID) ?: DEFAULT_USER_ID

}
