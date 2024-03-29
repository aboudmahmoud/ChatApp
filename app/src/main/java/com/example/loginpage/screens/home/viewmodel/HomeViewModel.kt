package com.example.loginpage.screens.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.data.SeriveQurey.fireauth.RegsterImplements
import com.example.loginpage.data.SeriveQurey.firesStroe.FirebaseSirvase
import com.example.loginpage.screens.home.helper.HomeIntnent
import com.example.loginpage.utils.Screens
import com.example.loginpage.utils.helper.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val repsotry: RegsterImplements,
    private val repsost: FirebaseSirvase,
) : ViewModel() {
    private val IntentChanenl = Channel<HomeIntnent> { Channel.UNLIMITED }

    private val _getAllUseData = MutableStateFlow<UiState<List<CurrenUserStatis>>>(UiState.Idel)
    val dataUser = _getAllUseData.asStateFlow()


    private  val _useInfo = MutableStateFlow<CurrenUserStatis?>(CurrenUserStatis())
    val useInfo = _useInfo.asStateFlow()
    var userData:CurrenUserStatis? by mutableStateOf(null)

    init {
        processIntent()
        getStroedUser()
        getAlluser()
    }

    //process
    fun processIntent() {

        viewModelScope.launch {
            IntentChanenl.consumeAsFlow().collect {

                when (it) {
                    is HomeIntnent.logOut -> signOut(it.resulte)

                }
            }
        }
    }

    fun IntentSignOut(result: () -> Unit){
        viewModelScope.launch {
             IntentChanenl.send(HomeIntnent.logOut(result))
        }
    }

    private fun signOut(result: () -> Unit) {

        repsotry.logout(result)
    }

    private fun getAlluser() {
        repsost.GetUsetAllUser {
            viewModelScope.launch {
                _getAllUseData.emit(it)
            }
        }

    }

    private fun getStroedUser()
    {
        repsotry.getSession {
            viewModelScope.launch {
                if (it != null) {
                    _useInfo.emit(it)
                    userData=useInfo.value


                }
            }
        }
    }
}


