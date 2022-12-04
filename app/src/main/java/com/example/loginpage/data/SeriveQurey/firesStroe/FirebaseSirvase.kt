package com.example.loginpage.data.SeriveQurey.firesStroe

import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.utils.helper.UiState
import com.google.firebase.firestore.FirebaseFirestore


class FirebaseSirvase(
 val fireStroe: FirebaseFirestore,

): SiravseFiucnation {
    override fun GetUsetAllUser(result: (UiState<List<CurrenUserStatis>>) -> Unit) {
        fireStroe.collection("users")
            .get()
            .addOnSuccessListener {
                val users = arrayListOf<CurrenUserStatis>()
                for (document in it) {
                   // Log.d(TAG, "${document.id} => ${document.data}")
                    val user = document.toObject(CurrenUserStatis::class.java)
                    user.userInfo!!.UserDucmentID=document.id
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