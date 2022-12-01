package com.example.loginpage.screens.walcome

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginpage.R
import com.example.loginpage.ui.theme.*
import com.example.loginpage.utils.Screens

@Composable
fun WalcomePage(
    onNavgite: (String) -> Unit={},
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(TextBackGround)) {
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderWalcome(onNavgite=onNavgite)
           Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
               Image(modifier = Modifier.weight(4f).fillMaxSize(),painter = painterResource(id = R.drawable.backgroud) , contentDescription = "")
               Text(modifier = Modifier.weight(0.5f),text = "Message wherever you are", style = TextStyle(
                   color = boxRegsterColor,
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Bold,
                   fontFamily = fonBalooPaaji2,
                   textAlign = TextAlign.Center
               ), textAlign = TextAlign.Center)
           }

        }
    }
   
}

@Composable
private fun HeaderWalcome( onNavgite: (String) -> Unit) {
    Spacer(modifier = Modifier.height(20.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        InfoBoxAction(
            backgroundcolor = boxLoginColor,
            imageId = R.drawable.laptop,
            textInfor = "LOGIN",
            onNavgite = onNavgite,
            RoutePath = Screens.Login.route
        )
        InfoBoxAction(
            backgroundcolor = boxRegsterColor,
            imageId = R.drawable.regst,
            textInfor = "Register an account",
            onNavgite = onNavgite,
            RoutePath = Screens.Regster.route
        )
    }
}

@Composable
fun InfoBoxAction(
    backgroundcolor: Color,
    @DrawableRes imageId:Int,
    textInfor:String,
    onNavgite: (String) -> Unit,
    RoutePath:String,
    ) {
    Box(modifier = Modifier.clickable {
        onNavgite(RoutePath)
    }
        .width(160.dp)
        .height(150.dp)
        .clip(RoundedCornerShape(8))
        .background(backgroundcolor), contentAlignment = Alignment.Center){
        Column(modifier = Modifier.fillMaxSize(),verticalArrangement=Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = imageId) , contentDescription = "")
            Text(text = textInfor, style = TextStyle(
                fontFamily =fontAswome6,
                fontSize = 11.sp
            ))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun WalcomePagePreview() {
    WalcomePage()
}