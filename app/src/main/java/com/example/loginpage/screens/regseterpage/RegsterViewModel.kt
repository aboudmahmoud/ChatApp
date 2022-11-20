package com.example.loginpage.screens.regseterpage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.lifecycle.*
import kotlinx.coroutines.channels.Channel

import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.data.SeriveQurey.FirebaseSirvase
import com.example.loginpage.data.SeriveQurey.UiState
import com.example.loginpage.utils.Constans
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
     var ErrorStatus=false

    private var _useInfo by mutableStateOf(UserInfo())
    val useInfo = _useInfo

    init {
        processIntent()
    }

    //process
    fun processIntent() {

        viewModelScope.launch {
            _addUser.value = UiState.Idel
            IntentChanenl.consumeAsFlow().collect {

                when (it) {
                    is RegsterIntent.RegsttesUser -> AddUser()
                }
            }
        }
    }

    private fun AddUser() {

        _addUser.value = UiState.Loading
        if (checkUserMainInfo()) {
            _addUser.value = UiState.Failure("UI Error :  Plase dont let any Field empty")

        } else {
            Valdatieemail()
        }
    }

    private fun checkUserMainInfo(): Boolean {
        return _useInfo.UserEmail == null
                || _useInfo.UserName == null ||
                _useInfo.UserPassword == null
    }

    private fun Valdatieemail() {
        if (Constans.isValidEmail(_useInfo.UserEmail!!)) {
            RegsterUser()

        } else {

            _addUser.value = UiState.Failure("UI Error : PLase Inser Vaild Email")
        }
    }

    private fun RegsterUser() {
        repsotry.registerUser(_useInfo) {
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