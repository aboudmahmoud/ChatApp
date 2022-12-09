package com.example.loginpage.navagtion.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.data.SeriveQurey.fireauth.RegsterImplements
import com.example.loginpage.utils.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NagvitionViewModel @Inject constructor(
    repsotry: RegsterImplements,
) : ViewModel() {
    var StatredDistation: String by mutableStateOf(Screens.Walcom.route)

    init {
        repsotry.getSession {
            viewModelScope.launch {
                if (it != null) {
                    StatredDistation = Screens.HomePage.route

                }
            }
        }
    }
}