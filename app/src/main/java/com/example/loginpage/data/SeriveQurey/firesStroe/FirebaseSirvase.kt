package com.example.loginpage.data.SeriveQurey.firesStroe

import android.net.Uri
import android.util.Log
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.utils.helper.UiState
import com.example.loginpage.utils.helper.UploadState
import com.google.firebase.FirebaseException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class FirebaseSirvase(
 val fireStroe: FirebaseFirestore,
 val storageReference: StorageReference,
val RealTimeDate: DatabaseReference
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


    override suspend fun uploadSingleFile(fileUri: Uri, onResult: (UploadState<Uri>) -> Unit) {
        try {
            val uri: Uri = withContext(Dispatchers.IO) {
                storageReference
                    .putFile(fileUri)
                    .await()
                    .storage
                    .downloadUrl
                    .await()
            }
            onResult.invoke(UploadState.Success(uri))
        } catch (e: FirebaseException){
            onResult.invoke(UploadState.Failure(e.message))
            Log.d("Abous", "uploadSingleFile: ${e.message}")
        }catch (e: Exception){
            onResult.invoke(UploadState.Failure(e.message))
        }
    }

    override fun updateUserInfo(user: CurrenUserStatis, result: (UiState<String>) -> Unit) {
        val document = fireStroe.collection("users").document(user.userInfo!!.UserID!!)
        document
            .set(user)
            .addOnSuccessListener {

                result.invoke(
                    UiState.Success("User has been update successfully")
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }

    }


}