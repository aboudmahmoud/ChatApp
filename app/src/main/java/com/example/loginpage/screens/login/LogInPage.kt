package com.example.loginpage.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.loginpage.utils.Screens
import com.example.loginpage.R
import com.example.loginpage.ui.theme.Textcolor

@Composable
fun LogInPage(
    navController: NavController
) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(50.dp))
        TextUsebla(Hint="Login")
        Spacer(modifier = Modifier.padding(20.dp))
        CoustemMadeTextField(hint = "Email", Icone= { Icon(
            painter = painterResource(R.drawable.ic_email),
            contentDescription = "emailIcon",
            tint = Textcolor
        )},)



        Spacer(modifier = Modifier.padding(20.dp))
        PasswordTf()


        Spacer(modifier = Modifier.padding(20.dp))
        BtnToUse(textBtn="LogIn",
            {
                navController.navigate(Screens.HomePage.route)
            })
        Spacer(modifier = Modifier.padding(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Start).padding(10.dp))
        {
            Row() {
                TextUsebla(Hint="You don't have an account?")
                TextUsebla(Hint="sign up", Enabled = true, Action={
                    navController.navigate(Screens.Regster.route)
                } )
            }
        }
    }
}