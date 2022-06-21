package com.example.loginpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.loginpage.Page.BottomBar
import com.example.loginpage.Page.Nagvation
import com.example.loginpage.Page.RegsterPage1
import com.example.loginpage.Page.navhandMadeIt
import com.example.loginpage.ui.theme.Balke
import com.example.loginpage.ui.theme.LoginPageTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            LoginPageTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
              Scaffold(
                  bottomBar = { navhandMadeIt(navController = navController) },
                  content ={
                          innerPadding ->  Surface(
                      modifier = Modifier.fillMaxSize(),
                      color = Balke
                  ) {
                      //   RegsterPage1()
                      Nagvation(navController)
                  }
                  }

              )
            }
        }
    }
}
