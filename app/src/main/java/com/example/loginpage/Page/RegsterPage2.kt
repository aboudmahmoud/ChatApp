package com.example.loginpage.Page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.loginpage.Compents.Screens
import com.example.loginpage.R

@Composable
fun RegsterPage2(
    navController: NavController
) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.padding(50.dp))
        TextUsebla("Almost Done")
        Spacer(modifier = Modifier.padding(20.dp))
      Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {

          Row {
              Image(painter = painterResource(R.drawable.ic_femailcard) , contentDescription = "FemailCared")
              Spacer(modifier = Modifier.padding(20.dp))
              Image(painter = painterResource(R.drawable.ic_mailcard) , contentDescription = "mailcared")
          }
      }

        Spacer(modifier = Modifier.padding(20.dp))
        PasswordTf()
        Spacer(modifier = Modifier.padding(20.dp))
        BtnToUse(textBtn="Create Account",
            {
                navController.navigate(Screens.Login.route)
            })
        Spacer(modifier = Modifier.padding(20.dp))
        Image(painter = painterResource(R.drawable.ic_ponints1) , contentDescription = "Points")

    }
}