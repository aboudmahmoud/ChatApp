package com.example.loginpage.utils

import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.loginpage.Moudle.User.UserInfo
import java.util.regex.Matcher

import java.util.regex.Pattern

object Constans {
    val RegsterPage1Posation:Int=0
    val RegsterPage2Posation:Int=1
    val RegsetPagesSize:Int=2

    fun checkUserLoginInfo(userInfo: UserInfo): Boolean {
        return userInfo.UserEmail == null || userInfo.UserPassword == null }

    fun checkUserMainInfo(userInfo: UserInfo): Boolean {
        return userInfo.UserName == null ||checkUserLoginInfo(userInfo) }

    fun isValidEmail(email: CharSequence): Boolean {
        val matcher = setMatcher(email)
        if (matchTheEmail(matcher!!)) return false
        return true
    }

    private fun setMatcher(email: CharSequence): Matcher? {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher
    }

    private fun matchTheEmail(matcher: Matcher): Boolean {
        if (!matcher.matches()) {
            return true
        }
        return false
    }


}

object SharedPrefConstants {
    val LOCAL_SHARED_PREF = "local_shared_pref"
    val USER_SESSION = "user_session"
}