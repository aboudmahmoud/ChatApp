package com.example.loginpage.navagtion

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.data.SeriveQurey.fireauth.RegsterImplements
import com.example.loginpage.utils.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NagvitionViewModel @Inject constructor(
    private val repsotry: RegsterImplements,
) : ViewModel() {
    private var _useInfo: UserInfo? by mutableStateOf(null)
    val useInfo = _useInfo
    var StatredDistation: String by mutableStateOf(Screens.Walcom.route)

    init {
        repsotry.getSession {
            viewModelScope.launch {
                if (it != null) {
                    _useInfo = it.userInfo
                    StatredDistation = Screens.HomePage.route
                }
            }
        }
    }
}