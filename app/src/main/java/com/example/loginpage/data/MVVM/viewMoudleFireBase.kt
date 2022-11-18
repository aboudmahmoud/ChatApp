/*
package com.example.loginpage.Database.MVVM


import androidx.lifecycle.ViewModel
import com.example.loginpage.Database.SeriveQurey.UiState
import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.Repsostry.Repsotry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class viewMoudleFireBase @Inject constructor(private val repostry:Repsotry): ViewModel() {
    private val _register = MutableStateFlow<UiState<String>>(UiState.Loading)
    val register: StateFlow<UiState<String>> get() = _register


   fun registerUser(userInfo: UserInfo, result: (UiState<String>) -> Unit){
       repostry.registerUser(userInfo,result)
   }

}*/
