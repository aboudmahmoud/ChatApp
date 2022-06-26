package com.example.loginpage.Repsostry

import com.example.loginpage.Database.SeriveQurey.FirebaseSirvase
import com.example.loginpage.Database.SeriveQurey.UiState
import com.example.loginpage.Moudle.User.UserInfo

class Repsotry {
   var firestore: FirebaseSirvase = FirebaseSirvase()

    fun registerUser(userInfo: UserInfo, result: (UiState<String>) -> Unit)
    {
        firestore.registerUser(userInfo, result)
    }
}