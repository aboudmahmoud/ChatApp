package com.example.loginpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.loginpage.Compents.NavhandMadeIt
import com.example.loginpage.screens.Nagvation

import com.example.loginpage.ui.theme.Balke
import com.example.loginpage.ui.theme.LoginPageTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            LoginPageTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = { NavhandMadeIt(navController = navController) },
                    containerColor = Balke,
                    content ={
                            innerPadding ->
                       Box(modifier = Modifier.padding(innerPadding)){ Nagvation(navController)}
                    },
                    modifier = Modifier.fillMaxSize())
            }
        }
    }
}
