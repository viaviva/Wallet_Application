package com.angelina.wallet_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.angelina.wallet_application.navigation.RootNavGraph
import com.angelina.wallet_application.repository.SharedPreferenceRepository
import com.angelina.wallet_application.ui.theme.Wallet_ApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferenceRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                }
            }
        }
    }
}