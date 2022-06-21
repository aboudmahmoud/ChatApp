package com.example.loginpage.Compents


import com.example.loginpage.R

sealed class BotomNagavationBar(
    val route: String,
    val icon: Int
) {
    object Home : BotomNagavationBar(
        route = Screens.HomePage.route,
        icon = R.drawable.ic_icon_awesome_home
    )
    object Proflie:BotomNagavationBar(
        route=Screens.HomePage.route,
        icon= R.drawable.ic_icon_awesome_home
    )

    object Chat:BotomNagavationBar(
        route=Screens.HomePage.route,
        icon= R.drawable.ic_icon_awesome_home
    )

}