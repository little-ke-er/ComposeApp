package com.haikun.lol.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val loadingState: MutableLiveData<Boolean> = MutableLiveData()

    fun showLoaDing(){
        if (loadingState.value!=true){
            loadingState.value=true
        }
    }

    fun hideLoading(){
        if (loadingState.value!=false){
            loadingState.value=false
        }
    }
}