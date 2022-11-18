package com.example.loginpage.data.SeriveQurey

import com.example.loginpage.Moudle.User.UserInfo
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


class FirebaseSirvase(
 val db: FirebaseFirestore
):SiravseFiucnation{


    override fun registerUser(userInfo: UserInfo, result: (UiState<String>) -> Unit) {
        db.collection("users")
            .add(userInfo)
            .addOnSuccessListener { documentReference ->
                result.invoke(
                    UiState.Success("User data has been created successfully")
                )
            }
            .addOnFailureListener { e ->
                result.invoke(
                    UiState.Failure(e.message)
                )
            }
    }


}