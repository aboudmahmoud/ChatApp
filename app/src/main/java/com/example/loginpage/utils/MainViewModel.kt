package com.example.loginpage.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.data.SeriveQurey.fireauth.RegsterImplements
import com.example.loginpage.data.SeriveQurey.firesStroe.FirebaseSirvase
import com.example.loginpage.screens.home.helper.HomeIntnent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
 class MainViewModel @Inject constructor(
    private val repsotry: RegsterImplements,
    private val repsost: FirebaseSirvase,
) : ViewModel(){
    private val IntentChanenl = Channel<HomeIntnent> { Channel.UNLIMITED }
    private  val _useInfo = MutableStateFlow<CurrenUserStatis?>(CurrenUserStatis())
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
                    is HomeIntnent.logOut -> signOut(it.resulte)

                }
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
     fun EventLogOut(result: () -> Unit){
         viewModelScope.launch {
             IntentChanenl.send(HomeIntnent.logOut {
                 result.invoke()
             })
         }
    }
    private fun signOut(result: () -> Unit) {
        repsotry.logout(result)
    }
}