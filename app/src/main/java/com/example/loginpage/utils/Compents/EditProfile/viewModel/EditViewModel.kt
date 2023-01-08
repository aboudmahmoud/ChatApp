package com.example.loginpage.utils.Compents.EditProfile.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.data.SeriveQurey.fireauth.RegsterImplements
import com.example.loginpage.data.SeriveQurey.firesStroe.FirebaseSirvase
import com.example.loginpage.screens.home.helper.HomeIntnent
import com.example.loginpage.utils.Compents.EditProfile.Intent.EditInten
import com.example.loginpage.utils.MainViewModel
import com.example.loginpage.utils.helper.UiState
import com.example.loginpage.utils.helper.UploadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val repsotry: RegsterImplements,
    private val repsost: FirebaseSirvase,
) : ViewModel() {

    val IntentChanenl = Channel<EditInten> { Channel.UNLIMITED }
    private val _EditUsreStatus = MutableStateFlow<UiState<String>>(UiState.Idel)
    val EditUsreStatus = _EditUsreStatus.asStateFlow()
    private val _useInfo = MutableStateFlow<CurrenUserStatis?>(CurrenUserStatis())
    val useInfo = _useInfo.asStateFlow()
    var userData: CurrenUserStatis? by mutableStateOf(null)

    init {
        processIntent()
        getStroedUser()
    }

    //process
    fun processIntent() {
        viewModelScope.launch {
            IntentChanenl.consumeAsFlow().collect {

                when (it) {
                    is EditInten.EditAction -> EditUser(it.user)
                }
            }
        }
    }

    fun EvenEditInentent() {
        viewModelScope.launch {
            IntentChanenl.send(EditInten.EditAction(userData!!))
        }
    }

    private fun EditUser(user: CurrenUserStatis) {

        viewModelScope.launch {
            repsost.uploadSingleFile(Uri.parse(user.userInfo!!.UserImage!!)) {
                _EditUsreStatus.value = UiState.Loading
                when (it) {
                    is UploadState.Failure -> {
                        _EditUsreStatus.value=  UiState.Failure(it.error)
                    }

                    is UploadState.Success -> {
                        user.userInfo!!.UserImage=it.data.toString()
                        repsotry.updateUserInfo(user) {UISTate->
                            when(UISTate){
                                is UiState.Failure -> {
                                    _EditUsreStatus.value = UiState.Failure(UISTate.error)
                                }

                                is UiState.Success -> {
                                    repsotry.storeSession(  user.userInfo!!.UserID!!){
                                        if(it==null){
                                            _EditUsreStatus.value=   UiState.Failure("User Update successfully but session failed to store")
                                        }else{
                                            _EditUsreStatus.value=   UiState.Success("User Update successfully")
                                        }
                                    }
                                }
                                else ->{}
                            }


                        }
                    }
                    else -> {

                    }
                }
            }
        }

        /*  repsotry.updateUserInfo(user){
              viewModelScope.launch {
                  _EditUsreStatus.emit(it)
              }
          }*/
    }

    private fun getStroedUser() {
        repsotry.getSession {
            viewModelScope.launch {
                if (it != null) {
                    _useInfo.emit(it)
                    userData = useInfo.value


                }
            }
        }
    }
}