package com.example.loginpage.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loginpage.utils.Screens
import com.example.loginpage.R
import com.example.loginpage.screens.login.LoginPageViewModel
import com.example.loginpage.utils.MainActionIntent
import com.example.loginpage.ui.theme.Textcolor
import com.example.loginpage.utils.Constans
import com.example.loginpage.utils.helper.ErrorInputValdtion
import com.example.loginpage.utils.helper.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun LogInPage(
    onNavgite: (String) -> Unit,
    loginViewModel: LoginPageViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val errorInputAction = remember<(Boolean) -> Unit> { { loginViewModel.ErrorStatus = it }}
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(50.dp))
        TextUsebla(Hint="Login")
        Spacer(modifier = Modifier.padding(20.dp))
        CoustemMadeTextField(hint = "Email", Icone= { Icon(
            painter = painterResource(R.drawable.ic_email),
            contentDescription = "emailIcon",
            tint = Textcolor
        )}, onChanges = loginViewModel::setEmail)



        Spacer(modifier = Modifier.padding(20.dp))
        PasswordTf(
            onChanedPassword =  loginViewModel::setPasswrod
        )


        Spacer(modifier = Modifier.padding(20.dp))
        BtnToUse(textBtn="LogIn", onClick = {
            logInPorres(loginViewModel, scope)
        }
           )
        Spacer(modifier = Modifier.padding(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Start).padding(10.dp))
        {
            Row() {
                TextUsebla(Hint="You don't have an account?")
                TextUsebla(Hint="sign up", Enabled = true, Action={
                    onNavgite(Screens.Regster.route)
                } )
            }
        }
    }

    ErrorMessegshow(loginViewModel=loginViewModel,ErrorInputAction=errorInputAction)
    UiStateHandeler(loginViewModel,onNavgite)
}


private fun logInPorres(
    loginViewModel: LoginPageViewModel,
    scope: CoroutineScope
) {
    if (Constans.checkUserLoginInfo(loginViewModel.useInfo)) {
        setEmptyEmailError(loginViewModel)
    } else {
        loginViewModel.ErrorStatus = false
        if (Constans.isValidEmail(loginViewModel.useInfo.UserEmail!!)) {
            scope.launch {
                loginViewModel.IntentChanenl.send(MainActionIntent.RegsttesUser) }
        } else {
            setEmailValidteerorError(loginViewModel)

        }
    }
}

private fun setEmailValidteerorError(loginViewModel: LoginPageViewModel) {
    loginViewModel.errorMesse =
        ErrorInputValdtion.EmailNotVaidleError
    loginViewModel.ErrorStatus = true
}


private fun setEmptyEmailError(loginViewModel: LoginPageViewModel) {
    loginViewModel.errorMesse = ErrorInputValdtion.EmptyFaieldError
    loginViewModel.ErrorStatus = true
}


@Composable
fun ErrorMessegshow(loginViewModel: LoginPageViewModel , ErrorInputAction: (Boolean)->Unit) {
    if (loginViewModel.ErrorStatus) {
        CoustemDiloage(
            dialogOpene = true,
            HeadlineMessage = "Error",
            MainMessage = loginViewModel.errorMesse.ValdationError,
            onClick = ErrorInputAction
        )

    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun UiStateHandeler( loginViewModel: LoginPageViewModel, onNavgite: (String) -> Unit) {
    val context = LocalContext.current
    when (val res = loginViewModel.LoginUserStatus.collectAsStateWithLifecycle().value) {
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
        is UiState.Idel -> {}
    }
}