package com.haikun.lol.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.haikun.lol.ui.theme.LolAppTheme
import com.haikun.lol.ui.theme.LolTheme
import com.haikun.lol.ui.theme.SystemUiController

class MainActivity : ComponentActivity() {


    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val lolThemeState = remember {
                mutableStateOf(LolTheme())
            }
            val systemUiController = remember { SystemUiController(window) }

            LolAppTheme(
                lolTheme = lolThemeState.value,
                systemUiController = systemUiController
            ) {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController, lolThemeState)
                }
            }
        }
    }

}
