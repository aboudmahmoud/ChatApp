package com.example.loginpage.utils.helper

sealed class ErrorInputValdtion(val ValdationError:String){
   object EmptyFaieldError: ErrorInputValdtion("Plase dont let any field empty")
    object EmailNotVaidleError: ErrorInputValdtion("Plase Enater Vailde Email")
}
