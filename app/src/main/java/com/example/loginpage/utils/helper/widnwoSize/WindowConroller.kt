package com.example.loginpage.utils.helper.widnwoSize

object WindowConroller {
    fun getScreenWidth(Wdtih:Int): WindowType = when{
        Wdtih < 600 -> WindowType.Compact
        Wdtih < 840 -> WindowType.Medium
        else -> WindowType.Expanded
    }
    fun getScreenHeight(Height:Int): WindowType = when{
        Height < 480 -> WindowType.Compact
        Height < 900 -> WindowType.Medium
        else -> WindowType.Expanded
    }
}