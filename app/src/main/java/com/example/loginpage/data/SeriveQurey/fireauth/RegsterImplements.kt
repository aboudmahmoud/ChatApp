package com.example.loginpage.data.SeriveQurey.fireauth

import android.content.SharedPreferences
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.Moudle.User.CurrentOnlieOff
import com.example.loginpage.Moudle.User.UserInfo

import com.example.loginpage.utils.helper.UiState
import com.example.loginpage.utils.SharedPrefConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class RegsterImplements(
    val firesAuthe: FirebaseAuth,
    val fireStroe: FirebaseFirestore,
    val appPreferences: SharedPreferences,
    val gson: Gson
):Regster {
    override fun regsterUser(userMail: CurrenUserStatis, result: (UiState<String>) -> Unit) {
        firesAuthe.createUserWithEmailAndPassword(userMail.userInfo!!.UserEmail!!,userMail.userInfo!!.UserPassword!!)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    userMail.userInfo!!.UserID = it.result.user?.uid ?: ""
                    updateUserInfo(userMail) { state ->
                        when(state){
                            is UiState.Success -> {
                                storeSession(id = it.result.user?.uid ?: "") {
                                    if (it == null){
                                        result.invoke(UiState.Failure("User register successfully but session failed to store"))
                                    }else{
                                        result.invoke(
                                            UiState.Success("User register successfully!")
                                        )
                                    }
                                }
                            }
                            is UiState.Failure -> {
                                result.invoke(UiState.Failure(state.error))
                            }
                        else->{

                        }
                        }
                    }
                }else{
                    try {
                        throw it.exception ?: java.lang.Exception("Invalid authentication")
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        result.invoke(UiState.Failure("Authentication failed, Password should be at least 6 characters"))
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        result.invoke(UiState.Failure("Authentication failed, Invalid email entered"))
                    } catch (e: FirebaseAuthUserCollisionException) {
                        result.invoke(UiState.Failure("Authentication failed, Email already registered."))
                    } catch (e: Exception) {
                        result.invoke(UiState.Failure(e.message))
                    }
                }
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
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

    override fun loginUser(userEmail:String,UserPassword:String, result: (UiState<String>) -> Unit) {

        firesAuthe.signInWithEmailAndPassword(userEmail,UserPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    storeSession(id = task.result.user?.uid ?: ""){
                        if (it == null){
                            result.invoke(UiState.Failure("Failed to store local session"))
                        }else{
                            result.invoke(UiState.Success("Login successfully!"))
                            it.crunntStatus=CurrentOnlieOff.Online
                            updateUserInfo(it){
                                state->
                                when(state){
                                    is UiState.Failure -> {

                                    }
                                    is UiState.Success -> {

                                    }
                                    else ->{}
                                }
                            }
                        }
                    }
                }
            }.addOnFailureListener {
                result.invoke(UiState.Failure("Authentication failed, Check email and password"))
            }
    }

    override fun forgotPassword(UserEmail: String, result: (UiState<String>) -> Unit) {
        firesAuthe.sendPasswordResetEmail(UserEmail)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result.invoke(UiState.Success("Email has been sent"))

                } else {
                    result.invoke(UiState.Failure(task.exception?.message))
                }
            }.addOnFailureListener {
                result.invoke(UiState.Failure("Authentication failed, Check email"))
            }
    }

    override fun logout(result: () -> Unit) {
        getSession {
            if (it != null) {
                it.crunntStatus = CurrentOnlieOff.OffLine
                updateUserInfo(it) {
                    when (it) {
                        is UiState.Failure -> {
                            result.invoke()
                        }
                        is UiState.Success -> {
                            firesAuthe.signOut()
                            appPreferences.edit().putString(SharedPrefConstants.USER_SESSION, null)
                                .apply()
                            result.invoke()
                        }
                        else -> {}
                    }
                }
            }
        }

        //


    }

    override fun storeSession(id: String, result: (CurrenUserStatis?) -> Unit) {
        fireStroe.collection("users").document(id)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val user = it.result.toObject(CurrenUserStatis::class.java)
                appPreferences.edit().putString(SharedPrefConstants.USER_SESSION,gson.toJson(user)).apply()
                    result.invoke(user)
                }else{
                    result.invoke(null)
                }
            }
            .addOnFailureListener {
                result.invoke(null)
            }

    }

    override fun getSession(result: (CurrenUserStatis?) -> Unit) {
        val user_str = appPreferences.getString(SharedPrefConstants.USER_SESSION,null)
        if (user_str == null){
            result.invoke(null)
        }else{
            val user = gson.fromJson(user_str,CurrenUserStatis::class.java)
            result.invoke(user)
        }
    }


}