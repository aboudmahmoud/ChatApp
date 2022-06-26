package com.example.loginpage.Database.SeriveQurey

import com.example.loginpage.Database.FireBase.FirebaseModule
import com.example.loginpage.Moudle.User.UserInfo

class FirebaseSirvase:SiravseFiucnation{
   private var  db  = FirebaseModule.provideFireStoreInstance()


    override fun registerUser(userInfo: UserInfo, result: (UiState<String>) -> Unit) {
        db.collection("users")
            .add(userInfo)
            .addOnSuccessListener { documentReference ->
                result.invoke(
                    UiState.Success("User has been update successfully")
                )
            }
            .addOnFailureListener { e ->
                result.invoke(
                    UiState.Failure(e.message)
                )
            }
    }


}