package com.example.loginpage.data.SeriveQurey.firesStroe

import android.net.Uri
import android.util.Log
import com.example.loginpage.Moudle.ChatRoom.MessegDiteals
import com.example.loginpage.Moudle.ChatRoom.UseCase.MessegSendState
import com.example.loginpage.Moudle.ChatRoom.UseCase.UpdateStatChatState
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.utils.helper.UiState
import com.example.loginpage.utils.helper.UploadState
import com.google.firebase.FirebaseException
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
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

    override fun onChangeUpateMeease(res: (UpdateStatChatState<MessegDiteals>) -> Unit) {
        RealTimeDate.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val messesgs= arrayListOf<MessegDiteals>()
                if(snapshot.exists()){
                    var msg=MessegDiteals()
                    snapshot.children.forEach{
                         msg=it.getValue(MessegDiteals::class.java)!!
                    }
                    res.invoke(
                        UpdateStatChatState.Success(data = msg)
                    )
                }else{
                    res.invoke(UpdateStatChatState.Idel)
                }

                //Log.d(TAG, "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
                //Log.w(TAG, "Failed to read value.", error.toException())
                res.invoke(
                    UpdateStatChatState.Falier(Errors=error.message)
                )
            }

        })
    }


    override fun getRealTimeMessages(result: (UiState<List<MessegDiteals>>) -> Unit) {
        val messesgs= arrayListOf<MessegDiteals>()
        RealTimeDate.get().addOnSuccessListener {

            for (messeg in it.children)
            {
                val msg=messeg.getValue(MessegDiteals::class.java)
                messesgs.add(msg!!)
            }
            result.invoke(
                UiState.Success(messesgs)
            )
        }.addOnFailureListener{
            result.invoke(UiState.Failure(it.localizedMessage))
        }
    }

    override fun SendRealTimeMessages(Message: MessegDiteals, res: (MessegSendState) -> Unit) {
        RealTimeDate.push().setValue(Message).addOnSuccessListener {
            res.invoke(
                MessegSendState.Success
            )
        }.addOnFailureListener{
            res.invoke(MessegSendState.Falier(Errors = it.localizedMessage))
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