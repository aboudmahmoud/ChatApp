package com.example.loginpage.di.firebase

import com.example.loginpage.data.SeriveQurey.FirebaseSirvase
import com.google.firebase.firestore.FirebaseFirestore
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
        database: FirebaseFirestore,
        ): FirebaseSirvase {
        return FirebaseSirvase(database)
    }
}