package com.example.loginpage.screens.chatPage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send

import androidx.compose.material3.ExperimentalMaterial3Api


import androidx.compose.material3.*
import androidx.compose.runtime.Composable


import androidx.compose.ui.draw.drawBehind

import androidx.compose.ui.graphics.Path

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import coil.compose.rememberAsyncImagePainter
import com.example.loginpage.Moudle.User.UserInfo

import com.example.loginpage.screens.InfoDatiels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loginpage.Moudle.ChatRoom.MessegDiteals
import com.example.loginpage.Moudle.ChatRoom.UseCase.MessegSendState
import com.example.loginpage.Moudle.ChatRoom.UseCase.UpdateStatChatState
import com.example.loginpage.screens.CoustemDiloage
import com.example.loginpage.screens.FollBoxScrie
import com.example.loginpage.screens.chatPage.ViewModel.ChatViewModel
import com.example.loginpage.utils.Constans.getData
import com.example.loginpage.utils.DataUser

import com.example.loginpage.utils.helper.UiState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ChatUI(

    chatViewModel: ChatViewModel = hiltViewModel()
) {
    chatViewModel.ResverData = DataUser.ReciverData
    chatViewModel.SenderuseuserData = DataUser.SenderData
    var messeagese: List<MessegDiteals>? by remember {
        mutableStateOf(listOf())
    }
    when (val res = chatViewModel.getMessges.collectAsStateWithLifecycle().value) {
        is UiState.Failure -> {
            CoustemDiloage(
                dialogOpene = true,
                HeadlineMessage = "Failure",
                MainMessage = res.error!!
            )
        }
        UiState.Idel -> {

        }
        UiState.Loading -> {
            FollBoxScrie()
        }
        is UiState.Success -> {
            messeagese = res.data
            chatViewModel.setIdelForGesstMasse()
        }
    }
    when (val res = chatViewModel.newMessaes.collectAsStateWithLifecycle().value) {
        is UpdateStatChatState.Falier -> {
            CoustemDiloage(
                dialogOpene = true,
                HeadlineMessage = "Failure",
                MainMessage = res.Errors!!
            )
        }
        UpdateStatChatState.Idel -> {

        }
        is UpdateStatChatState.Success -> {
            if (res.data.messeg.isNotEmpty()) {
                messeagese = messeagese?.plus(res.data)
            }


            //Log.d("Aboud", messeagese!![messeagese!!.size-2].messeg!!)
            chatViewModel.setIdelForNewMassege()

        }
    }
    Column() {
        LazyColumn(modifier = Modifier.weight(6f)) {
            if (messeagese != null) {
                itemsIndexed(messeagese!!) { index, item ->
                    val IsHeSender =
                        item.meesageSenderUserID == chatViewModel.ResverData!!.userInfo?.UserID
                    Box(
                        contentAlignment = if (IsHeSender) {
                            Alignment.CenterEnd
                        } else {
                            Alignment.CenterStart
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .width(200.dp)
                                .drawBehind {
                                    val cornerRadius = 10.dp.toPx()
                                    val triangleHieght = 20.dp.toPx()
                                    val triangelWidth = 25.dp.toPx()
                                    val trianglePath = Path().apply {
                                        if (IsHeSender) {
                                            moveTo(size.width, size.height - cornerRadius)
                                            lineTo(size.width, size.height + triangleHieght)
                                            lineTo(
                                                size.width - triangelWidth,
                                                size.height - cornerRadius
                                            )
                                            close()
                                        } else {
                                            moveTo(0f, size.height - cornerRadius)
                                            lineTo(0f, size.height + triangleHieght)
                                            lineTo(triangelWidth, size.height - cornerRadius)
                                            close()
                                        }
                                    }
                                    drawPath(
                                        path = trianglePath,
                                        color = if (IsHeSender) Color.Black else Color.Gray
                                    )
                                }
                                .background(
                                    color = if (IsHeSender) Color.Black else Color.Gray,
                                    shape = RoundedCornerShape(10.dp)

                                )
                                .padding(8.dp)

                        ) {
                            Text(
                                text = item.meesageSenderUserID,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = item.date,
                                color = Color.White
                            )
                            Text(
                                text = item.messeg,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.End)
                            )
                        }


                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }


        }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            var text by remember {
                mutableStateOf(
                    ""
                )
            }
            when (val rest = chatViewModel.SendMessageState.collectAsStateWithLifecycle().value) {
                is MessegSendState.Falier -> {
                    CoustemDiloage(
                        dialogOpene = true,
                        HeadlineMessage = "Failure",
                        MainMessage = rest.Errors!!
                    )
                }
                MessegSendState.Idel -> {

                }
                MessegSendState.Success -> {
                    text = ""
                    chatViewModel.setDoneMeesage()
                }
            }
            val ChatSendIntent = remember {
                {
                    chatViewModel.IntentSendMeesage(
                        MessegDiteals(
                            messeg = text,
                            messageRevsersUserID = chatViewModel.ResverData!!.userInfo!!.UserID!!,
                            meesageSenderUserID = chatViewModel.SenderuseuserData!!.userInfo!!.UserID!!,
                            date = getData()
                        )
                    )
                }
            }
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(40.dp),
                value = text,
                onValueChange = {
                    text = it
                },

                )
            IconButton(onClick = { ChatSendIntent() }


            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    tint = Color.Green,
                    contentDescription = "Send"
                )
            }

        }
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PhotoAndName(userinoo: UserInfo) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .height(100.dp)
    ) {

        Row {
            Spacer(
                modifier = Modifier
                    .width(10.dp)
                    .height(20.dp)
            )
            Image(
                painter = rememberAsyncImagePainter(userinoo.UserImage),
                contentDescription = "serch", modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(50.dp)
                    .height(50.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            InfoDatiels(InfoData = userinoo.UserName!!)
        }
    }
}