package com.example.loginpage.data.SeriveQurey.firesStroe

import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.utils.helper.UiState
import com.google.firebase.firestore.FirebaseFirestore


class FirebaseSirvase(
 val fireStroe: FirebaseFirestore,

): SiravseFiucnation {
    override fun GetUsetAllUser(result: (UiState<List<UserInfo>>) -> Unit) {
        fireStroe.collection("users")
            .get()
            .addOnSuccessListener {
                val users = arrayListOf<UserInfo>()
                for (document in it) {
                   // Log.d(TAG, "${document.id} => ${document.data}")
                    val user = document.toObject(UserInfo::class.java)
                    user.UserDucmentID=document.id
                    users.add(user)

                }
                result.invoke(UiState.Success(users))
            }
            .addOnFailureListener { exception ->
             //   Log.w(TAG, "Error getting documents.", exception)
                result.invoke(
                    UiState.Failure(
                        exception.localizedMessage
                    )
                )
            }
    }


}