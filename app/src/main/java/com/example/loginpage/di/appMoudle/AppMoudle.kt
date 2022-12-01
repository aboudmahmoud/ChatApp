package com.example.loginpage.di.appMoudle
import android.content.Context
import android.content.SharedPreferences
import com.example.loginpage.utils.SharedPrefConstants

import com.google.gson.Gson


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppMoudle {

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SharedPrefConstants.LOCAL_SHARED_PREF,Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }


}