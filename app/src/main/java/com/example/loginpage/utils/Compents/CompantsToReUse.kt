package com.example.loginpage.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.systemGestureExclusion
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.loginpage.utils.Compents.BotomNagavationBar
import com.example.loginpage.R

import com.example.loginpage.ui.theme.*


@Composable
fun TextUsebla(
    modifier: Modifier = Modifier,
    Hint: String,
    Enabled: Boolean = false,
    Action: () -> Unit = {}
) {
    Text(text = Hint, style = TextStyle(color = Textcolor, fontSize = 12.sp),
        modifier = modifier.clickable(enabled = Enabled) {
            Action()
        })
}

@Composable
fun BtnToUse(
    textBtn: String,
    onClick: () -> Unit,
    ) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = BtnBackground),
        modifier = Modifier.width(280.dp)
    ) {
        TextUsebla(Hint = textBtn)
    }
}

@ExperimentalMaterial3Api
@Composable
fun CoustemMadeTextField(
    hint: String,
    Icone: @Composable() (() -> Unit),
    onChanges:(String)->Unit={}
) {
    var text by rememberSaveable  { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it
            onChanges(it)},
        colors = TextFieldDefaults.textFieldColors(
            containerColor = TextBackGround,
            disabledTextColor = Color.Transparent,
            textColor = Textcolor,
            focusedIndicatorColor = TextBackGround,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = { TextUsebla(Hint=hint) },
        leadingIcon = Icone
    )


}

@ExperimentalMaterial3Api
@Composable
fun PasswordTf(
    onChanedPassword : (String) -> Unit ={}
) {
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(true) }

    TextField(
        value = password,
        onValueChange = { password = it
            onChanedPassword(it)
                        },
        placeholder = { TextUsebla(Hint="insert ur password here") },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = TextBackGround,
            textColor = Textcolor,
            focusedIndicatorColor = TextBackGround,
                    unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
        ),
        leadingIcon = {
        IconButton(onClick = { passwordVisible=!passwordVisible }) {
            Icon(
                painter = painterResource( if(passwordVisible) R.drawable.ic_lock else R.drawable.unlock),
                contentDescription = "emailIcon",
                tint = Textcolor
            )
        }
        },
        visualTransformation = if(passwordVisible)PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )

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


    NavigationBar {
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


    NavigationBarItem(modifier = Modifier.background(color = Balke),
        icon = {
            Icon(
                painter = painterResource(screen.icon),
                contentDescription = "Navigation Icon",
                tint = NavBottomColor
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        colors = NavigationBarItemDefaults.colors(
            unselectedIconColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
            unselectedTextColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),


            ),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )

}

@Composable
fun FollBoxScrie() {
    Box(modifier = Modifier.fillMaxSize().background(color =Balke), contentAlignment = Alignment.Center) {
        CustomCircularProgressBar()
    }
}

@Composable
private fun CustomCircularProgressBar(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier.size(100.dp),
        color = MaterialTheme.colorScheme.primary,
        strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth

    )
}
@Composable
fun CoustemDiloage(dialogOpene:Boolean,
                   HeadlineMessage:String,
                   MainMessage:String, onClick: (Boolean) -> Unit={}) {
    var dialogOpen by remember {
        mutableStateOf(dialogOpene)
    }

    if (dialogOpen) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality,
                // simply leave this block empty.
                dialogOpen = false
                onClick(false)
            },
            confirmButton = {
                TextButton(onClick = {
                    // perform the confirm action
                    dialogOpen = false
                    onClick(false)
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dialogOpen = false
                    onClick(false)
                }) {
                    Text(text = "Dismiss")
                }
            },
            title = {
                Text(text = HeadlineMessage)
            },
            text = {
                Text(text = MainMessage)
            },
            modifier = Modifier // Set the width and padding
                .fillMaxWidth()
                .padding(32.dp),
            shape = RoundedCornerShape(5.dp),
            containerColor  = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }

}


