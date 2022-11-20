package com.example.loginpage.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource

import  com.example.loginpage.R
import com.example.loginpage.ui.theme.Textcolor
import com.example.loginpage.utils.Constans.RegsterPage2Posation


@Composable
    fun RegsterPage1(
    Scroolboussion:(Int)->Unit,
    onChangesName:(String)->Unit={},
    onChangesEmail:(String)->Unit={}
    ) {
        Column(modifier = Modifier.padding(10.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.padding(50.dp))
            TextUsebla(Hint="Create Account")
            Spacer(modifier =Modifier.padding(20.dp))
            CoustemMadeTextField(hint = "Name", Icone = {
                Icon(
                    painter = painterResource(R.drawable.ic_un),
                    contentDescription = "emailIcon",
                    tint = Textcolor
                )
            }, onChanges = onChangesName)

            Spacer(modifier =Modifier.padding(20.dp))
      CoustemMadeTextField(hint = "Email",Icone={
          Icon(
              painter = painterResource(R.drawable.ic_email),
              contentDescription = "emailIcon",
              tint = Textcolor
          )
      }, onChanges = onChangesEmail)
            Spacer(modifier = Modifier.padding(20.dp))
            BtnToUse(textBtn="Continue", onClick =
              {
                    Scroolboussion(
                      RegsterPage2Posation
                    )
                   // navController.navigate(Screens.Regster2.route)
                })
            Spacer(modifier = Modifier.padding(20.dp))
         //   Image(painter =painterResource(R.drawable.ic_ponints) , contentDescription = "Points")

        }
    }




