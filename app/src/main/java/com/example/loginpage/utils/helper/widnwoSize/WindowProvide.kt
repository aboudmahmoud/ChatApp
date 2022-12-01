package com.example.loginpage.utils.helper.widnwoSize

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun rememberWindowSize():WindowSize {
    val configration= LocalConfiguration.current
    val ScreenWidth by  remember(key1 = configration) {
        mutableStateOf(configration.screenWidthDp)
    }
    val ScreenHeight by  remember(key1 = configration) {
        mutableStateOf(configration.screenHeightDp)
    }

    return WindowSize(
        width= WindowConroller.getScreenWidth(ScreenWidth),
        hight=WindowConroller.getScreenHeight(ScreenHeight)
    )
}
