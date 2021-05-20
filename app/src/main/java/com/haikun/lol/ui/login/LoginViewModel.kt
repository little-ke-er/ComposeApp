package com.haikun.lol.ui.login

import androidx.lifecycle.viewModelScope
import com.haikun.lol.ui.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel: BaseViewModel() {


    fun login(account: String, password: String, rememberPassword: Boolean,loginResult:()->Unit){
        viewModelScope.launch {
            delay(2000)
            loginResult()
        }
    }
}