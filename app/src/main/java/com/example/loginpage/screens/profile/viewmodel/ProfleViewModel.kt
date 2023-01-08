package com.example.loginpage.screens.profile.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.data.SeriveQurey.fireauth.RegsterImplements
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProfleViewModel @Inject constructor(
    private val repsotry: RegsterImplements,
) : ViewModel() {
    private  val _useInfo = MutableStateFlow<CurrenUserStatis?>(CurrenUserStatis())
    val useInfo = _useInfo.asStateFlow()
    var userData: CurrenUserStatis? by mutableStateOf(null)
    init {

        getStroedUser()
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