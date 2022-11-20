package com.example.loginpage.screens.regseterpage

import android.widget.Toast
import androidx.compose.foundation.layout.*



import androidx.compose.runtime.Composable

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.loginpage.data.SeriveQurey.UiState
import com.example.loginpage.screens.CoustemDiloage

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

import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ResgsterPageCompineantion(
    onNavgite: (String) -> Unit,
    regsterViewModel: RegsterViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val lifecycleScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val context = LocalContext.current

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
                    //RegsterPage1Posation = 1
                    RegsterPage1Posation -> RegsterPage1(
                        Scroolboussion = {

                       scope.launch { pagerState.scrollToPage(page = it) }
                        },
                        onChangesName =regsterViewModel::setUsrerName,
                        onChangesEmail = regsterViewModel::setEmail

                    ) //RegsterPage1
                    //RegsterPage2Posation = 2
                    RegsterPage2Posation -> RegsterPage2(
                        onChanges =  regsterViewModel::setPasswrod,
                        onClick = {

                            lifecycleScope.launch {
                                regsterViewModel.IntentChanenl.send(RegsterIntent.RegsttesUser)
                            }
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


var ErrorMesseg by remember {
    mutableStateOf("")
}
    var ErrorStatus by remember {
        mutableStateOf(false)
    }
    when (val res = regsterViewModel.addUser.collectAsState().value) {
        is UiState.Loading -> {
            ErrorStatus=false
            Toast.makeText(context,"Loading",Toast.LENGTH_SHORT).show()
            FollBoxScrie()

        }
        is UiState.Failure -> {
         //   Toast.makeText(context,"Failure",Toast.LENGTH_SHORT).show()
            ErrorStatus=true

            ErrorMesseg=res.error!!
        }
        is UiState.Success -> {
            ErrorStatus=false
          Toast.makeText(context,res.data,Toast.LENGTH_SHORT).show()
            onNavgite(Screens.Login.route)


        }
        is UiState.Idel -> {
            Toast.makeText(context,"Idel",Toast.LENGTH_SHORT).show()
          // We don't need to do anthing here  it just Uistate in idel mode difrent than Loading mode
            // so frsit ui mode here we se is the idel so in idel  than when make Requsest we make it in loading
        }
    }

    if(ErrorStatus){
        println("Wassam")
        CoustemDiloage(dialogOpene = true , HeadlineMessage ="Failure" , MainMessage =ErrorMesseg)
    }
}


