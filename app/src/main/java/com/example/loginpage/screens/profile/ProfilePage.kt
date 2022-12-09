package com.example.loginpage.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.example.loginpage.screens.ButtonTodo
import com.example.loginpage.screens.UserDisplay
import com.example.loginpage.ui.theme.boxRegsterColor
@Composable
fun DialogBox2FA(onDismiss: () -> Unit,
                 ImageProfile:String,
                 UserName:String,
                 UserEmail:String, presnolPrfoile:Boolean) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        ProfilePage(ImageProfile=ImageProfile,
            UserName=UserName,
            UserEmail=UserEmail, presnolPrfoile=presnolPrfoile )
    }
}
@Composable
fun ProfilePage( ImageProfile:String,
                 UserName:String,
                 UserEmail:String, presnolPrfoile:Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = boxRegsterColor
        )

        ) {
        UserDisplay(
            ImageProfile=ImageProfile,
            UserName=UserName,
            UserEmail=UserEmail
        )

        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center){
            if(!presnolPrfoile){
                ButtonTodo(ButtonText="Chat"){

                }
            }else{
                ButtonTodo(ButtonText="Edit"){

                }
            }
        }


    }
}