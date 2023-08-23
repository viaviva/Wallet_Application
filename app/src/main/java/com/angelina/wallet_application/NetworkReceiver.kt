package com.angelina.wallet_application

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.angelina.wallet_application.repository.NetworkStatusRepository
import com.angelina.wallet_application.util.getNetworkStatus
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NetworkReceiver : BroadcastReceiver() {

    @Inject
    lateinit var networkStatusRepository: NetworkStatusRepository

    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.e("NetworkReceiver", p0?.getNetworkStatus().toString())
        networkStatusRepository.updateNetworkStatus(p0?.getNetworkStatus() ?: false)
    }

}