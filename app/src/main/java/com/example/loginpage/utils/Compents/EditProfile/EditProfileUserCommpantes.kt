package com.example.loginpage.utils.Compents

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.loginpage.Moudle.User.UserInfo
import com.example.loginpage.R
import com.example.loginpage.screens.CoustemDiloage
import com.example.loginpage.screens.FollBoxScrie
import com.example.loginpage.screens.TextUsebla
import com.example.loginpage.ui.theme.IrishGrover
import com.example.loginpage.ui.theme.boxRegsterColor
import com.example.loginpage.utils.Compents.EditProfile.viewModel.EditViewModel
import com.example.loginpage.utils.Screens
import com.example.loginpage.utils.helper.UiState
import com.example.loginpage.utils.helper.UploadState

@Composable
fun DilogForCoustemEditUser(
    onDismiss: () -> Unit,
    editViewModel: EditViewModel = hiltViewModel()
   ){
    Dialog(
        onDismissRequest= onDismiss,
      properties =   DialogProperties(
          dismissOnBackPress = true,
          dismissOnClickOutside = true
      )

    ){
        EditProfile(editViewModel)
    }
}

@Composable
fun EditProfile(editViewModel: EditViewModel) {
   val userInfoData by remember {
        mutableStateOf(editViewModel.userData?.userInfo)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )

    ) {
        var selectedImageUri by remember {
            mutableStateOf<Uri?>(null)
        }
        val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> if(uri!=null)selectedImageUri = uri }
        )
        if(selectedImageUri!=null){
            userInfoData?.UserImage =selectedImageUri.toString()

        }
        Spacer(modifier = Modifier.height(30.dp))
        PhotoAndName(userInfoData!!)
        Spacer(modifier = Modifier.height(20.dp))
        ServesButtonAction(UploadOnclick={
            singlePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

        },OnScveClicked={
            editViewModel.EvenEditInentent()
        }
        )
        Spacer(modifier = Modifier.height(20.dp))
        val context = LocalContext.current
        when (val res = editViewModel.EditUsreStatus.collectAsStateWithLifecycle().value) {
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
                    Toast.makeText(context, "Succes "+res.data, Toast.LENGTH_SHORT).show()
                }


            }
            UiState.Idel -> {

            }
        }
    }
}

@Composable
private fun ServesButtonAction(UploadOnclick:()->Unit={},OnScveClicked:()->Unit={}) {


    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        ButtonServers(ImageIcon = R.drawable.upload, title = "Upload", onClick = {
            UploadOnclick()
        })
        ButtonServers(ImageIcon = R.drawable.save_one, title = "Save", onClick = OnScveClicked)
    }
}

@Composable
private fun ButtonServers(
    onClick:()->Unit={},
    @DrawableRes ImageIcon:Int,
    title:String,
) {

    Button(
        onClick = { onClick() },
        enabled= true,
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        ),
        colors = ButtonDefaults.buttonColors(containerColor = boxRegsterColor),
    ) {
        Icon(painter = painterResource(id = ImageIcon), contentDescription = "",
            tint = Color.Unspecified)
        Spacer(modifier = Modifier.width(20.dp))
        TextUsebla(Hint =title, fontSize = 20.sp, textColor = Color.Black,fontFamily = IrishGrover)
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
        var text by remember {
            mutableStateOf(
                userinoo.UserName
            )
        }
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
                value = text!!,
                onValueChange = {
                    text = it
                },

                )
        }
    }
}

