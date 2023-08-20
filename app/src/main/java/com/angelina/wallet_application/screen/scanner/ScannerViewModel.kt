package com.angelina.wallet_application.screen.scanner

import androidx.lifecycle.ViewModel
import com.angelina.wallet_application.repository.SharedPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val sharedPreferenceRepository: SharedPreferenceRepository
) : ViewModel() {

    fun setScanner() = sharedPreferenceRepository.setScanner(true)

}