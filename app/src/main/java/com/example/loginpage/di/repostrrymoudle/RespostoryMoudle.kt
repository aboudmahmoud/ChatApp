package com.example.loginpage.di.repostrrymoudle

import android.content.SharedPreferences
import com.example.loginpage.data.SeriveQurey.fireauth.RegsterImplements
import com.example.loginpage.data.SeriveQurey.firesStroe.FirebaseSirvase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RespostoryMoudle {
    @Provides
    @Singleton
    fun provideFirebaseSirvase(
        firebaseFirestore: FirebaseFirestore,
       storageReference: StorageReference,
        RealTimeDate: DatabaseReference
        ): FirebaseSirvase {
        return FirebaseSirvase(firebaseFirestore,storageReference,RealTimeDate)
    }

    @Provides
    @Singleton
    fun provideResgetFireAuthe(
        fireauth:FirebaseAuth,
        firebaseFirestore: FirebaseFirestore,
        appPreferences: SharedPreferences,
        gson: Gson
    ): RegsterImplements{
        return RegsterImplements(
            firesAuthe = fireauth ,
            fireStroe = firebaseFirestore,
            appPreferences=appPreferences,
            gson=gson
        )
    }
}