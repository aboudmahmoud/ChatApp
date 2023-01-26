package com.example.loginpage.screens.profile

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.screens.ButtonTodo
import com.example.loginpage.screens.UsersDisplay
import com.example.loginpage.screens.profile.viewmodel.ProfleViewModel
import com.example.loginpage.ui.theme.boxRegsterColor
import com.example.loginpage.utils.Compents.DilogForCoustemEditUser
import com.example.loginpage.utils.DataUser
import com.example.loginpage.utils.MainViewModel
import com.example.loginpage.utils.Screens

@Composable
fun DialogBox2FA(
    onDismiss: () -> Unit,
    usrInfo: UserInfo= UserInfo(),
    presnolPrfoile: Boolean,
    onNavgite: (String) -> Unit,
    ) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },

    ) {

            ProfilePage(
                usrInfo = usrInfo,
                presnolPrfoile = presnolPrfoile,
                onDismiss = onDismiss,
                onNavgite=onNavgite
            )

    }
}

@Composable
fun ProfilePage(
    usrInfo: UserInfo,
    presnolPrfoile: Boolean,
    profileViewModel: ProfleViewModel = hiltViewModel(),
    mainviewModel: MainViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
    onNavgite: (String) -> Unit,
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
            UsersDisplay(
                ImageProfile = profileViewModel.userData?.userInfo?.UserImage!!,
                UserName = profileViewModel.userData?.userInfo?.UserName!!,
                UserEmail =  profileViewModel.userData?.userInfo?.UserEmail!!
            )

        }else{
            UsersDisplay(
                ImageProfile = usrInfo.UserImage!!,
                UserName = usrInfo.UserName!!,
                UserEmail =  usrInfo.UserEmail!!
            )
        }


        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (!presnolPrfoile) {
                ButtonTodo(ButtonText = "Chat",fontSize = 18.sp,) {
                    onDismiss()
                    DataUser.SetDataForSender(CurrenUserStatis(
                        userInfo= profileViewModel.userData?.userInfo,

                    ))

                    DataUser.SetDataForReciver(CurrenUserStatis(usrInfo))
                    onNavgite(Screens.Chat.route)

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