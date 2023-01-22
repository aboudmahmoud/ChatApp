package com.example.loginpage.utils

import android.annotation.SuppressLint
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.Moudle.User.UserInfo
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.random.Random

object Constans {
    val RegsterPage1Posation:Int=0
    val RegsterPage2Posation:Int=1
    val RegsetPagesSize:Int=2

    fun checkUserLoginInfo(userInfo: UserInfo): Boolean {
        return userInfo.UserEmail == null ||  userInfo.UserPassword == null || chechkEmpty(userInfo) }

    private fun chechkEmpty(userInfo: UserInfo): Boolean {
        return userInfo.UserEmail!!.isEmpty() ||  userInfo.UserPassword!!.isEmpty()
    }

    @SuppressLint("SuspiciousIndentation")
    fun getData():String{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
        val date = Date()
     val dates=   dateFormat.format(date)
        return dates
    }
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
    fun pair(): Pair<Long, Int> {
        val randomLong = Random.nextLong()
        val min = 1
        val max = 1000
        val randomInt = Random.nextInt(min, max + 1)
        return Pair(randomLong, randomInt)
    }

}

object SharedPrefConstants {
    val LOCAL_SHARED_PREF = "local_shared_pref"
    val USER_SESSION = "user_session"
}

object FirebaseStorageConstants {
    val ROOT_DIRECTORY = "app"
    val Profile_Image = "image"
}

object DataUser{
    private lateinit var _SenderData: CurrenUserStatis
    private lateinit var _LocalUserData: CurrenUserStatis
    val SenderData get()= _SenderData
    val ReciverData get() = _LocalUserData
    fun SetDataForSender(SenderDate:CurrenUserStatis){
        _SenderData=SenderDate
    }
    fun SetDataForLocalUser(LocalUserDate:CurrenUserStatis){
        _LocalUserData=LocalUserDate
    }
}



