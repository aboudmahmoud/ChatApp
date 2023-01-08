package com.example.loginpage.utils.Compents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.loginpage.R
import com.example.loginpage.screens.FollBoxScrie
import com.example.loginpage.screens.home.helper.HomeIntnent
import com.example.loginpage.screens.profile.DialogBox2FA
import com.example.loginpage.ui.theme.NavBottomColor
import com.example.loginpage.ui.theme.nare
import com.example.loginpage.utils.MainViewModel
import com.example.loginpage.utils.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//This is Natgation Bttom Bar
@Composable
fun NavhandMadeIt(navController: NavHostController, modifier: Modifier = Modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val screens = listOf(
        Screens.HomePage.route,
        Screens.Login.route

    )
    var selected by remember { mutableStateOf(false) }

    //Inhome so Bassicly if we On Screens.HomePage.route  setVisble to navhandMadeIt
    var InHome by remember { mutableStateOf(false) }
    var selected3 by remember { mutableStateOf(false) }
    selected = currentDestination?.hierarchy?.any {
        screens.contains(it.route)

    } == true
//color here change opectiy it will opecity in 70% if not selcted
    val color = if (selected) NavBottomColor else nare

    //here how we do it
    InHome = currentDestination?.hierarchy?.any {
        Screens.HomePage.route == it.route


    } == true


//if we inhome  its true the nave bar will show
    when {
        InHome -> {
            Navbody(
                navController = navController,
                color = color,
                selected = selected,
                modifier = modifier
            )
        }
    }

}

@Composable
fun Navbody(
    navController: NavHostController,
    color: Color, selected: Boolean, modifier: Modifier,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    var showProoges by remember {
        mutableStateOf(
            false
        )
    }


    if (showProoges) {
        FollBoxScrie()
    } else {
        ContentNavbottom(
            mainViewModel,
            modifier,
            {
                showProoges=true
            },

            navController,
            color,
            selected
        )
    }


}

@Composable
private fun ContentNavbottom(
    mainViewModel: MainViewModel,
    modifier: Modifier,
     showProoges: ()->Unit,
    navController: NavHostController,
    color: Color,
    selected: Boolean
) {
    var openDialog by remember {
        mutableStateOf(false) // Initially dialog is closed
    }
    val doligeState = remember { { openDialog = false } }
    if (openDialog) {
        DialogBox2FA(
            onDismiss = doligeState,
            presnolPrfoile = true
        )
    }
    Box() {
        Image(modifier = modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.ic_tomage), contentDescription = "tomeg")

        Row(modifier = modifier.align(alignment = Alignment.Center)) {
            LogOutNav(modifier=modifier.weight(1f), showProoges, mainViewModel, navController)
            ShowProfileNv(modifier=modifier.weight(1f) ){
                openDialog=true
            }
            HomeNav(modifier, color, selected, navController)
        }
    }
}

@Composable
private fun RowScope.HomeNav(
    modifier: Modifier,
    color: Color,
    selected: Boolean,
    navController: NavHostController
) {
    Box(
        modifier = modifier.weight(1f)
           , contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_icon_awesome_home),
            contentDescription = "emailIcon",
            tint = color, modifier = modifier.clickable(enabled = !selected) {

                navController.navigate(Screens.HomePage.route)

            }
        )
    }
}

@Composable
private fun ShowProfileNv(modifier: Modifier, onClick:()->Unit) {

    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(R.drawable.proflie),
            contentDescription = "proflie",
            modifier = modifier.clickable {
                onClick()
            }
        )
    }
}

@Composable
private fun LogOutNav(
    modifier: Modifier,
    showProoges: () -> Unit,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logoutcanle),
            contentDescription = "home", modifier = Modifier.clickable {
                showProoges()
                mainViewModel.EventLogOut {
                    navController.navigate(Screens.Walcom.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                }


            }
        )
    }
}