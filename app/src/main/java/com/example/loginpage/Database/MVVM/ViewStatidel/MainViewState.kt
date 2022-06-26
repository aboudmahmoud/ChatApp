package com.example.loginpage.Database.MVVM.ViewStatidel

sealed class MainViewState{
    //idle
    object Idle:MainViewState()
    //number
    data class Number(val Num:Int):MainViewState()
    //error
    data class Error(val error:String):MainViewState()
}
