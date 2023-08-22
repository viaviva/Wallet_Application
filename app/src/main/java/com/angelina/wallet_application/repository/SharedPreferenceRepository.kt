package com.angelina.wallet_application.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val USER_PREF_FILE = "userPrefFile"
private const val SHARED_PREF_FILE = "sharedPrefFile"

private const val IS_FIRST_OPEN = "isFirstOpen"
private const val IS_USER_LOG_IN = "isLogIn"

private const val USERNAME = "username"
private const val DEFAULT_USERNAME = "username"

private const val USER_ID = "user_id"
private const val DEFAULT_USER_ID = "user_id"

private const val EMAIL = "email"
private const val DEFAULT_EMAIL = "email"

private const val NO_CARDS = "noCards"

private const val SCANNER_OPEN = "scannerOpen"

private const val SHOP = "shop"

@Singleton
class SharedPreferenceRepository @Inject constructor(
    @ApplicationContext context: Context
) {

    private var userPreferences: SharedPreferences? = null
    private var sharedPreferences: SharedPreferences? = null

    init {
        userPreferences = context.getSharedPreferences(USER_PREF_FILE, Context.MODE_PRIVATE)
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
    }

    fun setIsFirstOpen() {
        sharedPreferences?.edit {
            putBoolean(IS_FIRST_OPEN, true)
        }
    }

    fun getIsFirstOpen(): Boolean = sharedPreferences?.getBoolean(IS_FIRST_OPEN, false) ?: false

    fun setIsUserLogIn(isLogin: Boolean) {
        userPreferences?.edit {
            putBoolean(IS_USER_LOG_IN, isLogin)
        }
    }

    fun getIsUserLogIn(): Boolean = userPreferences?.getBoolean(IS_USER_LOG_IN, false) ?: false

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

    fun setScanner(isOpen: Boolean) {
        userPreferences?.edit {
            putBoolean(SCANNER_OPEN, isOpen)
        }
    }

    fun getScanner(): Boolean =
        userPreferences?.getBoolean(SCANNER_OPEN, false) ?: false

    fun setNoCards(isOpen: Boolean) {
        userPreferences?.edit {
            putBoolean(NO_CARDS, isOpen)
        }
    }

    fun getNoCards(): Boolean =
        userPreferences?.getBoolean(NO_CARDS, false) ?: false

    fun setShop(id: Int) {
        userPreferences?.edit {
            putInt(SHOP , id)
        }
    }

    fun getShop(): Int =
        userPreferences?.getInt(SHOP, -1) ?: -1

    fun setEmail(email: String) {
        userPreferences?.edit {
            putString(EMAIL, email)
        }
    }

    fun getEmail(): String =
        userPreferences?.getString(EMAIL, DEFAULT_EMAIL) ?: DEFAULT_EMAIL

    fun clearUserPreference() {
        userPreferences?.edit {
            clear()
        }
    }

}
