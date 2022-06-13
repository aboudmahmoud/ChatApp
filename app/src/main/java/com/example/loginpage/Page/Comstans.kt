package com.example.loginpage.Page

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginpage.R
import com.example.loginpage.ui.theme.BtnBackground
import com.example.loginpage.ui.theme.TextBackGround
import com.example.loginpage.ui.theme.Textcolor

@Composable
fun TextUsebla(Hint:String ) {
    Text(Hint, style  = TextStyle(color = Textcolor, fontSize = 12.sp))
}

@Composable
fun BtnToUse(textBtn:String,
             onClick: () -> Unit) {
    Button(onClick =onClick,
    colors = ButtonDefaults.buttonColors(backgroundColor =BtnBackground),
        modifier = Modifier.width(280.dp)) {
        TextUsebla(Hint=textBtn)
    }
}

@Composable
fun TF(
    hint:String,
    Icone: @Composable() (() -> Unit)
):String {
    var text by remember { mutableStateOf("") }
    TextField(  value = text,
        onValueChange = { text = it },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = TextBackGround,
            textColor = Textcolor,
            focusedIndicatorColor = TextBackGround
        ),
        placeholder = { TextUsebla(hint) },
        leadingIcon=Icone)
return text

}

@Composable
fun PasswordTf():String
{
    var password by rememberSaveable { mutableStateOf("") }
  //  var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = { password = it },
        placeholder = { TextUsebla("********") },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = TextBackGround,
            textColor = Textcolor,
            focusedIndicatorColor = TextBackGround
        ),
        leadingIcon= {Icon(
            painter = painterResource(R.drawable.ic_lock),
            contentDescription = "emailIcon",
            tint = Textcolor
        )},
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
return password
}