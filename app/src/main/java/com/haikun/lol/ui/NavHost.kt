package com.haikun.lol.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.haikun.lol.ui.home.HomeScreen
import com.haikun.lol.ui.home.HomeViewModel
import com.haikun.lol.ui.login.LoginScreen
import com.haikun.lol.ui.login.LoginViewModel
import com.haikun.lol.ui.settings.SettingsScreen
import com.haikun.lol.ui.theme.LolTheme
import org.koin.androidx.compose.getViewModel

@ExperimentalComposeUiApi
@Composable
fun NavHost(navController: NavHostController, lolThemeState: MutableState<LolTheme>) {

    NavHost(navController, startDestination = "LoginPage") {

        composable("LoginPage") {
            Screen<LoginViewModel> {
                LoginScreen(navController)
            }
        }

        composable(
            "HomePage?userName={userName}",
            arguments = listOf(navArgument("userName") { defaultValue = "蔡徐坤" })
        ) {
            Screen<HomeViewModel> {
                HomeScreen(navController,it.arguments?.getString("userName"))
            }
        }

        composable(
            "SettingsScreen",
        ) {
            Screen<HomeViewModel> {
                SettingsScreen(navController,lolThemeState)
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
inline fun <reified T : BaseViewModel> Screen(content: @Composable () -> Unit) {
    val viewModel = getViewModel<T>()
    val loadingState: Boolean by viewModel.loadingState.observeAsState(false)
    if (loadingState) {
        LoadingDialog()
    }
    content()
}


@Composable
fun LoadingDialog() {
    AlertDialog(
        text = {
            CircularProgressIndicator(modifier = Modifier
                .padding(horizontal = 80.dp)
                .size(60.dp, 60.dp))
        },
        buttons = {

        },
        onDismissRequest = {  }
    )
    /*Box(
        modifier = Modifier
            .background(color = DialogBg)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator(modifier = Modifier.size(60.dp, 60.dp))
    }*/
}

