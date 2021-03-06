package com.example.loginpage.Page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.loginpage.R
import com.example.loginpage.ui.theme.Textcolor

@Composable
fun LogInPage(

) {
    Column(modifier = Modifier.padding(10.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.padding(50.dp))
        TextUsebla("Create Account")
        Spacer(modifier = Modifier.padding(20.dp))
        TF(hint = "Email") {
            Icon(
                painter = painterResource(R.drawable.ic_email),
                contentDescription = "emailIcon",
                tint = Textcolor
            )
        }

        Spacer(modifier = Modifier.padding(20.dp))
        PasswordTf()

        Spacer(modifier = Modifier.padding(20.dp))
        BtnToUse(textBtn="LogIn",
            {
            })
        Spacer(modifier = Modifier.padding(20.dp))
        Image(painter = painterResource(R.drawable.ic_ponints) , contentDescription = "Points")

    }
}