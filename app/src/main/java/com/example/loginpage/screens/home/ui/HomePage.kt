package com.example.loginpage.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
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
import com.example.loginpage.utils.Screens
import com.example.loginpage.utils.helper.UiState
import kotlinx.coroutines.launch

@Composable
fun HomePage(
    userInfo: CurrenUserStatis,
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
        Box(modifier = Modifier.fillMaxWidth())
        {
            Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Image(painter = painterResource(R.drawable.ic_lines), contentDescription = "lines",
                    modifier = Modifier.clickable {
                        scope.launch {
                            homeViewModel.IntentChanenl.send(HomeIntnent.logOut{

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
        Spacer(modifier = Modifier.padding(10.dp))
        GetUserData(userInfo=userInfo,homeViewModel=homeViewModel)
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun GetUserData( userInfo: CurrenUserStatis,homeViewModel: HomeViewModel ) {
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

                if(!item.userInfo!!.UserID.equals(userInfo.userInfo!!.UserID)){
                    UserDisplay(item)
                }
            }
        }
}}
  /*  val theList = listOf(
        CurrenUserStatis(
            UserInfo(
                "Aboud",
                "1d","https://img5.goodfon.com/wallpaper/nbig/5/e1/naruto-naruto-boruto-mitsuki.jpg", "1234", "Abdelrhman@gmail.com"
            ), crunntStatus = CurrentOnlieOff.Online
        ),
        CurrenUserStatis(
            UserInfo(
                "Aboud",
                "1d", "https://i.pinimg.com/736x/cc/d8/9c/ccd89c6a03a868f2cf78bd53c06cbe63.jpg", "1234", "Abdelrhman@gmail.com"
            )
        ),
        CurrenUserStatis(
            UserInfo(
                "Aboud",
                "1d","https://images5.alphacoders.com/110/1100926.jpg", "1234", "Abdelrhman@gmail.com"
            )
        ),
        CurrenUserStatis(
            UserInfo(
                "Aboud",
                "1d", "https://image.winudf.com/v2/image/Y29tLm1qZGV2LkJvcnV0b19zY3JlZW5fMl8xNTMxOTU0MjI4XzA3NQ/screen-2.jpg?fakeurl=1&type=.webp", "1234", "Abdelrhman@gmail.com"
            )
        ),
        CurrenUserStatis(
            UserInfo(
                "Aboud",
                "1d", "https://i.pinimg.com/originals/48/7b/59/487b59280b5cd092d1af258e2447de62.jpg", "1234", "Abdelrhman@gmail.com"
            )
        ),
        CurrenUserStatis(
            UserInfo(
                "Aboud",
                "1d","https://pbs.twimg.com/media/FUva4XzaIAAxvO3?format=jpg&name=large", "1234", "Abdelrhman@gmail.com"
            )
        ),
        CurrenUserStatis(
            UserInfo(
                "Aboud",
                "1d", "https://pbs.twimg.com/media/FUva4XzaIAAxvO3?format=jpg&name=large", "1234", "Abdelrhman@gmail.com"
            )
        ),
        CurrenUserStatis(
            UserInfo(
                UserName = "Aboud",
                UserID = "1d", UserPassword = "1234" , UserEmail = "Abdelrhman@gmail.com"
            )
        ),
        CurrenUserStatis(
            UserInfo(
                UserName = "Aboud",
                UserID = "1d", UserPassword = "1234" , UserEmail = "Abdelrhman@gmail.com"
            )
        ),
        CurrenUserStatis(
            UserInfo(
                UserName = "Aboud",
                UserID = "1d", UserPassword = "1234" , UserEmail = "Abdelrhman@gmail.com"
            )
        ),

    )*/

   /* LazyColumn(modifier = Modifier.fillMaxWidth()){
        itemsIndexed(theList){
            index, item ->  UserDisplay(item)
        } }*/






}


@Composable
fun UserDisplay(
    currentUser: CurrenUserStatis,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(10.dp)
            .height(100.dp)
    ) {
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