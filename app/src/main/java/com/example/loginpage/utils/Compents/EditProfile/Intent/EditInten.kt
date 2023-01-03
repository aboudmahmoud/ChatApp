package com.example.loginpage.utils.Compents.EditProfile.Intent

import com.example.loginpage.Moudle.User.CurrenUserStatis

sealed class EditInten {
    class EditAction(val user: CurrenUserStatis):EditInten()
}