package com.example.loginpage.screens.regseterpage

import android.widget.Toast

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api


import androidx.compose.runtime.Composable

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


import com.example.loginpage.utils.helper.UiState
import com.example.loginpage.screens.CoustemDiloage

import com.example.loginpage.screens.FollBoxScrie
import com.example.loginpage.screens.RegsterPage1
import com.example.loginpage.screens.RegsterPage2
import com.example.loginpage.utils.helper.ErrorInputValdtion
import com.example.loginpage.utils.MainActionIntent
import com.example.loginpage.screens.regseterpage.viewmodel.RegsterViewModel
import com.example.loginpage.ui.theme.BtnBackground
import com.example.loginpage.ui.theme.Textcolor

import com.example.loginpage.utils.Constans.RegsetPagesSize
import com.example.loginpage.utils.Constans.RegsterPage1Posation
import com.example.loginpage.utils.Constans.RegsterPage2Posation
import com.example.loginpage.utils.Constans.checkUserMainInfo

import com.example.loginpage.utils.Constans.isValidEmail
import com.example.loginpage.utils.Screens
import com.google.accompanist.pager.*

import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.launch


@ExperimentalPagerApi
@ExperimentalMaterial3Api
@Composable
fun ResgsterPageCompineantion(
    onNavgite: (String) -> Unit,
    regsterViewModel: RegsterViewModel = hiltViewModel()
) {

    val errorInputAction = remember<(Boolean) -> Unit> { { regsterViewModel.ErrorStatus = it }}
    // val userInfo by remember { mutableStateOf(UserInfo()) }
    MainPageContent(regsterViewModel=regsterViewModel)

    ErrorMessegshow(regsterViewModel=regsterViewModel,ErrorInputAction=errorInputAction)

    UiStateHandeler(regsterViewModel=regsterViewModel,onNavgite=onNavgite)

}

@ExperimentalPagerApi
@ExperimentalMaterial3Api
@Composable
private fun MainPageContent(
    regsterViewModel: RegsterViewModel
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(5f)) {
            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                count = RegsetPagesSize,
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { postion ->
                when (postion) {
                    //RegsterPage1Posation = 1
                    RegsterPage1Posation -> RegsterPage1(
                        scroolTo = {
                            scope.launch { pagerState.scrollToPage(page = it) }
                        },
                        onChangesName = regsterViewModel::setUsrerName,
                        onChangesEmail = regsterViewModel::setEmail
                    ) //RegsterPage1

                    //RegsterPage2Posation = 2
                    RegsterPage2Posation -> RegsterPage2(
                        onChanges = regsterViewModel::setPasswrod,
                        onClick = {
                            ResgterProcces(regsterViewModel, scope)
                        }
                    )//RegsterPage2
                }
            }
        }
        Spacer(modifier = Modifier.padding(20.dp))
        //This Repsent 3 dots . . .
        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            pagerState = pagerState, activeColor = BtnBackground, inactiveColor = Textcolor
        )
    }
}


private fun ResgterProcces(
    regsterViewModel: RegsterViewModel,
    scope: CoroutineScope
) {
    if (checkUserMainInfo(regsterViewModel.useInfo)) {
        ErrorInputValdtionSet(regsterViewModel)
    } else {
        regsterViewModel.ErrorStatus = false
        if (isValidEmail(regsterViewModel.useInfo.UserEmail!!)) {
            scope.launch {

                regsterViewModel.IntentChanenl.send(MainActionIntent.RegsttesUser)
            }
        } else {
            EmaitlNotValdtionSet(regsterViewModel)

        }
    }
}

private fun EmaitlNotValdtionSet(regsterViewModel: RegsterViewModel) {
    regsterViewModel.errorMesse =
        ErrorInputValdtion.EmailNotVaidleError
    regsterViewModel.ErrorStatus = true
}

private fun ErrorInputValdtionSet(regsterViewModel: RegsterViewModel) {
    regsterViewModel.errorMesse = ErrorInputValdtion.EmptyFaieldError
    regsterViewModel.ErrorStatus = true
}

@Composable
fun ErrorMessegshow(regsterViewModel: RegsterViewModel, ErrorInputAction: (Boolean)->Unit) {
    if (regsterViewModel.ErrorStatus) {
        CoustemDiloage(
            dialogOpene = true,
            HeadlineMessage = "Error",
            MainMessage = regsterViewModel.errorMesse.ValdationError,
            onClick = ErrorInputAction
        )

    }
}


@Composable
fun UiStateHandeler(regsterViewModel: RegsterViewModel, onNavgite: (String) -> Unit) {
    val context = LocalContext.current
    when (val res = regsterViewModel.addUser.collectAsStateWithLifecycle().value) {
        is UiState.Loading -> {
            FollBoxScrie()
        }
        is UiState.Failure -> {
            CoustemDiloage(
                dialogOpene = true,
                HeadlineMessage = "Failure",
                MainMessage = res.error!!
            )
        }
        is UiState.Success -> {
         LaunchedEffect(key1 = true){
             Toast.makeText(context, res.data, Toast.LENGTH_SHORT).show()
         }
            onNavgite(Screens.HomePage.route)
        }
        is UiState.Idel -> {

            // Toast.makeText(context,"Idel",Toast.LENGTH_SHORT).show()
            // We don't need to do anthing here  it just Uistate in idel mode difrent than Loading mode
            // so frsit ui mode here we se is the idel so in idel  than when make Requsest we make it in loading
        }
    }
}