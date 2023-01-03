package com.example.loginpage.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.loginpage.Moudle.User.CurrenUserStatis

import com.example.loginpage.R
import com.example.loginpage.screens.home.helper.HomeIntnent
import com.example.loginpage.screens.home.viewmodel.HomeViewModel
import com.example.loginpage.screens.profile.DialogBox2FA
import com.example.loginpage.utils.Screens
import com.example.loginpage.utils.helper.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomePage(
    onNavgite: (String) -> Unit,
homeViewModel: HomeViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(scope, homeViewModel, onNavgite)
        Spacer(modifier = Modifier.padding(10.dp))
        GetUserData(homeViewModel=homeViewModel)
    }
}

@Composable
private fun Header(
    scope: CoroutineScope,
    homeViewModel: HomeViewModel,
    onNavgite: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth())
    {
        Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Image(painter = painterResource(R.drawable.ic_lines), contentDescription = "lines",
                modifier = Modifier.clickable {
                    scope.launch {
                        homeViewModel.IntentChanenl.send(HomeIntnent.logOut {

                            onNavgite(Screens.Walcom.route)

                        })
                    }
                })

            Image(
                painter = painterResource(R.drawable.ic_search_1_),
                contentDescription = "serch"
            )
        }
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun GetUserData( homeViewModel: HomeViewModel ) {
when(val res=homeViewModel.dataUser.collectAsStateWithLifecycle().value){
    is UiState.Failure -> {
        CoustemDiloage(
            dialogOpene = true,
            HeadlineMessage = "Failure",
            MainMessage = res.error!!
        )
    }
    UiState.Idel -> {}
    UiState.Loading -> {}
    is UiState.Success -> {

        LazyColumn(modifier = Modifier.fillMaxWidth()){
            itemsIndexed(res.data){ index, item ->
                if(!item.userInfo!!.UserID.equals(homeViewModel.userData?.userInfo?.UserID)){

                    UserDisplay(item)
                }
            }
        }
}}

}


@Composable
fun UserDisplay(
    currentUser: CurrenUserStatis,
    modifier: Modifier = Modifier
) {
    var openDialog by remember {
        mutableStateOf(false) // Initially dialog is closed
    }
    val doligeState = remember { { openDialog = false }}
    Box(
        modifier = modifier.clickable {
            openDialog = true
        }
            .padding(10.dp)
            .height(100.dp)
    ) {
        if (openDialog) {
            DialogBox2FA(onDismiss = doligeState,
                usrInfo=currentUser.userInfo!!,
                presnolPrfoile = false
                )
        }
        Row {
            Spacer(
                modifier = modifier
                    .width(10.dp)
                    .height(20.dp)
            )
            Image(
                painter = rememberAsyncImagePainter(currentUser.userInfo!!.UserImage),
                contentDescription = "serch", modifier = modifier
                    .align(Alignment.CenterVertically)
                    .width(50.dp)
                    .height(50.dp)
            )
            Spacer(modifier = modifier.width(20.dp))
            Column {
                TextUsebla(Hint=currentUser.userInfo!!.UserName!!)
                Spacer(modifier = modifier.height(20.dp))
                TextUsebla(Hint=currentUser.crunntStatus.status)
            }
        }
    }
}


/*listOf(  Text(text=item.UserName,
            textAlign = TextAlign.Center,
            style  = TextStyle(color = Textcolor, fontSize = 12.sp),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),) ,    Image(painter = painterResource(R.drawable.ic_search_1_) , contentDescription = "serch"))*/