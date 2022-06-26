package com.example.loginpage.Page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.loginpage.Moudle.User.CurrenUserStatis
import com.example.loginpage.Moudle.User.CurrentOnlieOff
import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.R

@Composable
fun HomePage(

) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth())
        {
            Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Image(painter = painterResource(R.drawable.ic_lines), contentDescription = "lines")

                Image(
                    painter = painterResource(R.drawable.ic_search_1_),
                    contentDescription = "serch"
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        getUserData()
    }
}

@Composable
fun getUserData() {

    val theList = listOf(
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

    )

    LazyColumn(modifier = Modifier.fillMaxWidth()){
        itemsIndexed(theList){
            index, item ->  UserDisplay(item)
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