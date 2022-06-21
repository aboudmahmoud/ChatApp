package com.example.loginpage.Page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.loginpage.R
import com.example.loginpage.ui.theme.Textcolor

@Composable
fun HomePage(

) {
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Box(modifier=Modifier.fillMaxWidth())
        {
            Row(modifier=Modifier.fillMaxWidth(),Arrangement.SpaceBetween){
                Image(painter = painterResource(R.drawable.ic_lines) , contentDescription = "lines")

                Image(painter = painterResource(R.drawable.ic_search_1_) , contentDescription = "serch")
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        LazyColumn(){}
    }
}