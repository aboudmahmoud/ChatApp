package com.example.loginpage.screens.regseterpage.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.example.loginpage.Moudle.User.CurrenUserStatis
import kotlinx.coroutines.channels.Channel

import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.utils.helper.UiState
import com.example.loginpage.data.SeriveQurey.fireauth.RegsterImplements
import com.example.loginpage.utils.helper.ErrorInputValdtion
import com.example.loginpage.utils.MainActionIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegsterViewModel @Inject constructor(
    private val repsotry: RegsterImplements,
) : ViewModel() {
    val IntentChanenl = Channel<MainActionIntent> { Channel.UNLIMITED }

    private val _addUser = MutableStateFlow<UiState<String>>(UiState.Idel)
    val addUser = _addUser.asStateFlow()

    var ErrorStatus by mutableStateOf(false)
private var _curentUserInfo by mutableStateOf(CurrenUserStatis())
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
                    is MainActionIntent.RegsttesUser -> AddUser()
                }
            }
        }
    }

    private fun AddUser() {
        _curentUserInfo.userInfo=_useInfo
        _addUser.value = UiState.Loading
        repsotry.regsterUser(_curentUserInfo) {
            viewModelScope.launch {
                _addUser.emit(it)
//            _addUser.value = it }
            }

        }
    }


    fun setUsrerName(username: String) {
        _useInfo.UserName = username
    }

    fun setEmail(userEmail: String) {
        _useInfo.UserEmail = userEmail
    }

    fun setPasswrod(userPassword: String) {
        _useInfo.UserPassword = userPassword
    }


}