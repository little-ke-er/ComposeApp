package com.haikun.lol.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.haikun.lol.ui.commonCompose.LolAppBar
import com.haikun.lol.ui.commonCompose.SubTitleTextButton
import com.haikun.lol.ui.theme.ColorPallet
import com.haikun.lol.ui.theme.LolTheme

@Composable
fun SettingsScreen(navController: NavHostController, lolThemeState: MutableState<LolTheme>) {
    var isExpansion: Boolean by remember {
        mutableStateOf(false)
    }
    val value = getScreenTitle(lolThemeState.value.pallet)
    Scaffold(topBar = {
        LolAppBar("设置中心", navController)
    }) {
        Column {
            SubTitleTextButton(onClick = {
                isExpansion = !isExpansion
            }, text = "皮肤设置", value = value)
            if (isExpansion) {
                Divider()
                Row(horizontalArrangement = Arrangement.SpaceAround,modifier = Modifier.fillMaxWidth()) {
                    ColorPallet.values().forEach {
                        Column(
                            horizontalAlignment = CenterHorizontally,
                            modifier = Modifier
                                .clickable {
                                    lolThemeState.value = lolThemeState.value.copy(pallet = it)
                                }
                                .padding(16.dp)
                        ) {
                            Text(
                                text = getScreenTitle(it),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                            RadioButton(selected = it == lolThemeState.value.pallet, onClick = null)
                        }
                    }

                }
            }
            Divider()

        }
    }

}

private fun getScreenTitle(pallet: ColorPallet): String {
    return when (pallet) {
        ColorPallet.GREEN -> {
            "绿野仙踪"
        }
        ColorPallet.PURPLE -> {
            "基佬紫"
        }
        ColorPallet.ORANGE -> {
            "复仇焰魂"
        }
        ColorPallet.BLUE -> {
            "蓝色忧郁"
        }
    }
}