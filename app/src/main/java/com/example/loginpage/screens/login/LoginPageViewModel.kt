package com.example.loginpage.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.utils.helper.UiState
import com.example.loginpage.data.SeriveQurey.fireauth.RegsterImplements
import com.example.loginpage.utils.MainActionIntent
import com.example.loginpage.utils.helper.ErrorInputValdtion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginPageViewModel @Inject constructor(
    private val repsotry: RegsterImplements,
) : ViewModel() {
    val IntentChanenl = Channel<MainActionIntent> { Channel.UNLIMITED }
    private val _LoginUser = MutableStateFlow<UiState<String>>(UiState.Idel)
    val LoginUserStatus = _LoginUser.asStateFlow()
    var ErrorStatus by mutableStateOf(false)
    private var _useInfo by mutableStateOf(UserInfo())
    val useInfo = _useInfo
    lateinit var errorMesse: ErrorInputValdtion

    init {
        processIntent()
    }

    //process
    fun processIntent() {

        viewModelScope.launch {
            IntentChanenl.consumeAsFlow().collect {

                when (it) {
                    is MainActionIntent.RegsttesUser -> loginInUser()
                }
            }
        }
    }

    private fun loginInUser() {
        _LoginUser.value = UiState.Loading
        repsotry.loginUser(_useInfo.UserEmail!!,_useInfo.UserPassword!!) {
            viewModelScope.launch {
                _LoginUser.emit(it)

            }

        }
    }


    fun setEmail(userEmail: String) {
        _useInfo.UserEmail = userEmail
    }

    fun setPasswrod(userPassword: String) {
        _useInfo.UserPassword = userPassword
    }
}

