package com.example.loginpage.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.data.SeriveQurey.fireauth.RegsterImplements
import com.example.loginpage.data.SeriveQurey.firesStroe.FirebaseSirvase
import com.example.loginpage.screens.home.helper.HomeIntnent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repsotry: RegsterImplements,
    private val repsost: FirebaseSirvase,
) : ViewModel(){
    val IntentChanenl = Channel<HomeIntnent> { Channel.UNLIMITED }
    init {
        processIntent()
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
    private fun signOut(result: () -> Unit) {
        repsotry.logout(result)
    }
}