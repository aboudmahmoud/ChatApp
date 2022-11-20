package com.example.loginpage.utils

import java.util.regex.Pattern

object Constans {
    val RegsterPage1Posation:Int=0
    val RegsterPage2Posation:Int=1
    val RegsetPagesSize:Int=2




    fun isValidEmail(email: CharSequence): Boolean {
        var isValid = true
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        if (!matcher.matches()) {
            isValid = false
        }
        return isValid
    }

}