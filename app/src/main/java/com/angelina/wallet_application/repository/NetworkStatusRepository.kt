package com.angelina.wallet_application.repository

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStatusRepository @Inject constructor(
    private val sharedPreferenceRepository: SharedPreferenceRepository
) {

    private val networkStatus = PublishSubject.create<Boolean>()

    fun getNetworkState(): Observable<Boolean> = networkStatus

    fun updateNetworkStatus(isConnected: Boolean) {
        networkStatus.onNext(isConnected)
        sharedPreferenceRepository.setIsNoInternet(isConnected)
    }
}