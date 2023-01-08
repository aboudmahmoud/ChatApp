package com.example.loginpage.di.firebase

import com.example.loginpage.utils.Constans.pair
import com.example.loginpage.utils.FirebaseStorageConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlin.random.Random

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFireStoreInstance(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthInstance(): FirebaseAuth {
        return Firebase.auth
    }


    @Provides
    @Singleton
    fun provideFirebasestorageReferenceInstance(): StorageReference {
        val (randomLong, randomInt) = pair()
        return FirebaseStorage.getInstance().getReference(FirebaseStorageConstants.ROOT_DIRECTORY+randomLong+randomInt)
    }



    @Provides
    @Singleton
    fun provideFirebaseDatabaseInstance(): DatabaseReference {
        return Firebase.database.getReference("message")
    }
}