package com.angelina.wallet_application

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.angelina.wallet_application.navigation.RootNavGraph
import com.angelina.wallet_application.repository.NetworkStatusRepository
import com.angelina.wallet_application.repository.SharedPreferenceRepository
import com.angelina.wallet_application.ui.theme.Wallet_ApplicationTheme
import com.angelina.wallet_application.util.getNetworkStatus
import com.angelina.wallet_application.worker.NotificationWorker
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ExperimentalGetImage
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferenceRepository

    @Inject
    lateinit var networkStatusRepository: NetworkStatusRepository


    private val receiver = NetworkReceiver()

    protected val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(receiver, filter)
        networkStatusRepository.updateNetworkStatus(applicationContext.getNetworkStatus())

        val workRequestBuilder =
            PeriodicWorkRequest.Builder(NotificationWorker::class.java,30, TimeUnit.MINUTES, 15, TimeUnit.MINUTES)
                .build()

        WorkManager.getInstance(this).enqueue(workRequestBuilder)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequestBuilder.id)
            .observe(this) {
                if (it.state == WorkInfo.State.SUCCEEDED) {
                    Log.e("NotificationWorker", it.outputData.getString("title") ?: "")
                }
            }

        setContent {
            Wallet_ApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    sharedPreferences.run {
                        RootNavGraph(
                            rememberNavController(),
                            getIsFirstOpen(),
                            getIsUserLogIn()
                        )

                        if (!getIsFirstOpen()) {
                            setIsFirstOpen()
                        }
                    }

                    disposable.add(networkStatusRepository.getNetworkState()
                        .filter { !it }
                        .subscribe {
                            Toast.makeText(applicationContext, "no internet connection", Toast.LENGTH_LONG).show()
                        })
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}