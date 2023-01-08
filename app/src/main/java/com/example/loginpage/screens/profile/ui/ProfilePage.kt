package com.example.loginpage.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.screens.ButtonTodo
import com.example.loginpage.screens.UserDisplay
import com.example.loginpage.screens.profile.viewmodel.ProfleViewModel
import com.example.loginpage.ui.theme.boxRegsterColor
import com.example.loginpage.utils.Compents.DilogForCoustemEditUser
import com.example.loginpage.utils.MainViewModel

@Composable
fun DialogBox2FA(
    onDismiss: () -> Unit,
    usrInfo: UserInfo= UserInfo(),
    presnolPrfoile: Boolean,

    ) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },

    ) {

            ProfilePage(
                usrInfo=usrInfo, presnolPrfoile = presnolPrfoile
            )

    }
}

@Composable
fun ProfilePage(
    usrInfo: UserInfo, presnolPrfoile: Boolean,profileViewModel: ProfleViewModel = hiltViewModel()
) {


    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = boxRegsterColor
        )

    ) {
        var openDialog by remember {
            mutableStateOf(false) // Initially dialog is closed
        }
        if(presnolPrfoile){
            UserDisplay(
                ImageProfile = profileViewModel.userData?.userInfo?.UserImage!!,
                UserName = profileViewModel.userData?.userInfo?.UserName!!,
                UserEmail =  profileViewModel.userData?.userInfo?.UserEmail!!
            )

        }else{
            UserDisplay(
                ImageProfile = usrInfo.UserImage!!,
                UserName = usrInfo.UserName!!,
                UserEmail =  usrInfo.UserEmail!!
            )
        }


        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (!presnolPrfoile) {
                ButtonTodo(ButtonText = "Chat",fontSize = 18.sp,) {

                }
            } else {
                ButtonTodo(ButtonText = "Edit", fontSize = 18.sp, ){
                    openDialog=true
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))


if(openDialog){
    DilogForCoustemEditUser(
        onDismiss = {openDialog=false},)
}


    }
}