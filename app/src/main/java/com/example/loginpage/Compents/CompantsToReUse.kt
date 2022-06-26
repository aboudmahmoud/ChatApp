package com.example.loginpage.Page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import coil.compose.rememberAsyncImagePainter
import com.example.loginpage.Compents.BotomNagavationBar
import com.example.loginpage.Compents.Screens
import com.example.loginpage.Moudle.User.CurrenUserStatis
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

//This is Natgation Bttom Bar
@Composable
fun navhandMadeIt(navController: NavHostController,modifier: Modifier=Modifier) {
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
        Screens.HomePage.route==it.route


    } == true



//if we inhome  its true the nave bar will show
    when {
        InHome -> {
            Navbody(navController = navController, color = color , selected =selected  , modifier = modifier)
        }
    }

}

@Composable
fun Navbody(navController: NavHostController,
            color: Color,selected:Boolean,modifier: Modifier) {

    Box(){
        Image(painter =painterResource(R.drawable.ic_tomage) , contentDescription = "tomeg")

        Row(modifier = modifier.align(alignment = Alignment.Center)) {
            Box(modifier = modifier.weight(1f), contentAlignment = Alignment.Center
            ) {
                Image(
                    painter =painterResource(R.drawable.ic_icon_awesome_home) ,
                    contentDescription = "home", modifier = Modifier.clickable {

                        navController.navigate(Screens.Login.route)
                    }
                )
            }
            Box(
                modifier = modifier.weight(1f),contentAlignment = Alignment.Center
            ) {
                Image(painter =painterResource(R.drawable.ic_icon_awesome_home) , contentDescription = "home",
                    modifier = modifier.clickable {
//                        selected=false
                        navController.navigate(Screens.HomePage.route)
                    }
                )
            }
            Box(modifier = modifier
                .weight(1f),contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_icon_awesome_home),
                    contentDescription = "emailIcon",
                    tint = color,  modifier = modifier.clickable(enabled = !selected) {

                        navController.navigate(Screens.HomePage.route)

                    }
                )
            }
        }
    }


}


@Composable
fun UserDisplay(
    currentUser: CurrenUserStatis,
    modifier: Modifier =Modifier
) {
    Box(modifier = modifier
        .padding(10.dp)
        .height(100.dp)){
        Row {
            Spacer(modifier = modifier
                .width(10.dp)
                .height(20.dp))
            Image(
                painter = rememberAsyncImagePainter(currentUser.userInfo.UserImage),
                contentDescription = "serch",modifier = modifier
                    .align(CenterVertically)
                    .width(50.dp)
                    .height(50.dp)
            )
            Spacer(modifier = modifier.width(20.dp))
            Column {
                TextUsebla(currentUser.userInfo.UserName)
                Spacer(modifier = modifier.height(20.dp))
                TextUsebla(currentUser.crunntStatus.status)
            }
        }
    }
}
