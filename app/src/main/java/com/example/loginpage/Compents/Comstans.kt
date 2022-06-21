package com.example.loginpage.Page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.loginpage.Compents.BotomNagavationBar
import com.example.loginpage.Compents.Screens
import com.example.loginpage.R
import com.example.loginpage.ui.theme.*

@Composable
fun TextUsebla(Hint:String,  Enabled:Boolean = false,modifier: Modifier=Modifier,Action:()->Unit = {}){
    Text(text = Hint, style  = TextStyle(color = Textcolor, fontSize = 12.sp),
        modifier = modifier.clickable(enabled =Enabled ){
            Action()
    })
}

@Composable
fun BtnToUse(textBtn:String,
             onClick: () -> Unit) {
    Button(onClick =onClick,
    colors = ButtonDefaults.buttonColors(backgroundColor =BtnBackground),
        modifier = Modifier.width(280.dp)) {
        TextUsebla(Hint=textBtn)
    }
}

@Composable
fun TF(
    hint:String,
    Icone: @Composable() (() -> Unit)
):String {
    var text by remember { mutableStateOf("") }
    TextField(  value = text,
        onValueChange = { text = it },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = TextBackGround,
            textColor = Textcolor,
            focusedIndicatorColor = TextBackGround
        ),
        placeholder = { TextUsebla(hint) },
        leadingIcon=Icone)
return text

}

@Composable
fun PasswordTf():String
{
    var password by rememberSaveable { mutableStateOf("") }
  //  var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = { password = it },
        placeholder = { TextUsebla("********") },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = TextBackGround,
            textColor = Textcolor,
            focusedIndicatorColor = TextBackGround
        ),
        leadingIcon= {Icon(
            painter = painterResource(R.drawable.ic_lock),
            contentDescription = "emailIcon",
            tint = Textcolor
        )},
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
return password
}



@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BotomNagavationBar.Home,
        BotomNagavationBar.Proflie,
        BotomNagavationBar.Chat,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


        BottomNavigation {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }

        }


}

@Composable
fun RowScope.AddItem(
    screen: BotomNagavationBar,
    currentDestination: NavDestination?,
    navController: NavHostController
) {


    BottomNavigationItem(modifier = Modifier.background(color=Balke),
        icon = {
            Icon(
                painter = painterResource( screen.icon),
                contentDescription = "Navigation Icon",
                tint = NavBottomColor
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )

}


@Composable
fun navhandMadeIt(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var selected by remember { mutableStateOf(false) }
    var selected2 by remember { mutableStateOf(false) }
    var selected3 by remember { mutableStateOf(false) }

    val color = if (selected) NavBottomColor else nare
    Box(modifier = Modifier.fillMaxWidth()){
        Image(painter =painterResource(R.drawable.ic_tomage) , contentDescription = "tomeg")

        Row(modifier = Modifier.align(alignment = Alignment.Center)) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter =painterResource(R.drawable.ic_icon_awesome_home) ,
                    contentDescription = "home", modifier = Modifier.clickable {

                    navController.navigate(Screens.HomePage.route)
                }
                )
            }
            Box(
                modifier = Modifier.weight(1f)
               ,contentAlignment = Alignment.Center
            ) {
                Image(painter =painterResource(R.drawable.ic_icon_awesome_home) , contentDescription = "home",
                    modifier = Modifier.clickable {
                        selected=false
                        navController.navigate(Screens.HomePage.route)
                    }
                )
            }
            Box(modifier = Modifier
                .weight(1f),contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_icon_awesome_home),
                    contentDescription = "emailIcon",
                    tint = color,  modifier = Modifier.clickable {
                        selected=true
                        navController.navigate(Screens.HomePage.route)
                    }
                )
            }
        }
    }
}
