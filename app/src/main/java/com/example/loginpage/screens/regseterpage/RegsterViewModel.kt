package com.example.loginpage.screens.regseterpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.lifecycle.*
import kotlinx.coroutines.channels.Channel

import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.data.SeriveQurey.FirebaseSirvase
import com.example.loginpage.data.SeriveQurey.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegsterViewModel @Inject constructor(
    private val repsotry: FirebaseSirvase,
    ) : ViewModel() {
    val IntentChanenl = Channel<RegsterIntent> { Channel.UNLIMITED }

    private val _addUser = MutableStateFlow<UiState<String>>(UiState.Idel)
    val addUser = _addUser.asStateFlow()
private var _useInfo by mutableStateOf(UserInfo())
    val useInfo = _useInfo

    init {
        processIntent()
    }
    //process
     fun processIntent() {
        viewModelScope.launch {
            IntentChanenl.consumeAsFlow().collect {
                when (it) {
                    is RegsterIntent.RegsttesUser -> adduseR(it.user)
                }

            }
        }
    }

    fun adduseR(user: UserInfo) {
        _addUser.value = UiState.Loading

        repsotry.registerUser(user) {
            viewModelScope.launch {
                _addUser.emit(it)
//            _addUser.value = it }
            }


        }

    }

    fun setUsrerName(username:String){
        _useInfo.UserName=username
    }

    fun setEmail(userEMal:String)
    {
        _useInfo.UserEmail=userEMal
    }

    fun setPasswrod(userPassword:String){
        _useInfo.UserPassword=userPassword
    }



}