package com.example.loginpage.screens.regseterpage

import androidx.compose.foundation.layout.*


import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.data.SeriveQurey.UiState
import com.example.loginpage.screens.BtnToUse
import com.example.loginpage.screens.FollBoxScrie
import com.example.loginpage.screens.RegsterPage1
import com.example.loginpage.screens.RegsterPage2
import com.example.loginpage.ui.theme.BtnBackground
import com.example.loginpage.ui.theme.Textcolor
import com.example.loginpage.utils.Constans.RegsetPagesSize
import com.example.loginpage.utils.Constans.RegsterPage1Posation
import com.example.loginpage.utils.Constans.RegsterPage2Posation
import com.example.loginpage.utils.Screens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ResgsterPageCompineantion(
    regsterViewModel: RegsterViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val lifecycleScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()



   // val userInfo by remember { mutableStateOf(UserInfo()) }
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(5f)) {

            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                count = RegsetPagesSize,
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { postion ->

                when (postion) {
                    RegsterPage1Posation -> RegsterPage1(
                        Scroolboussion = {
                        /*    it.also {
                                scope.launch { pagerState.scrollToPage(page = it) }

                            }*/
                       scope.launch { pagerState.scrollToPage(page = it) }
                        },
                        onChangesName =regsterViewModel::setUsrerName /*{
                        //    regsterViewModel.useInfo.UserName = it
                        }*/, onChangesEmail = regsterViewModel::setEmail/*{
                         //   regsterViewModel.useInfo.UserEmail = it
                        }*/

                    )
                    RegsterPage2Posation -> RegsterPage2(
                        onChanges =  regsterViewModel::setPasswrod/*{
                        //    regsterViewModel.useInfo.UserPassword = it
                        }*/,
                        onClick = {

                            lifecycleScope.launch {
                                regsterViewModel.IntentChanenl.send(
                                    RegsterIntent.RegsttesUser(
                                        regsterViewModel.useInfo
                                    )
                                )
                            }
                            //  regsterViewModel.processIntent(userInfo)


                        }
                    )
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

        /*  FinshBouttone(modifier = Modifier.weight(1f),peraPageState=pagerState){
              walcomeViewModel.saveOnBoradSaveData(complate=true)
              navHostController.navigate(Screen.Home.route){

                  //navHostController.popBackStack()
              }
          }*/
    }
    when (val res = regsterViewModel.addUser.collectAsState().value) {
        is UiState.Loading -> {
            FollBoxScrie()
            println("Loading")
        }
        is UiState.Failure -> {

            println("Failure")

        }
        is UiState.Success -> {


            println(res.data)

        }
        is UiState.Idel -> {
            println("Idel")
        }
    }
}


