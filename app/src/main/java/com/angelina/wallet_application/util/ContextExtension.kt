package com.angelina.wallet_application.util

import android.content.Context
import android.net.ConnectivityManager

fun Context.getNetworkStatus(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.getActiveNetworkInfo()
    return netInfo != null && netInfo.isConnected
}