package com.example.parkpromain.apis.Login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var loginRepository: LoginRepository = LoginRepository()
    private var loginResponseLiveData: LiveData<LoginResponse> = loginRepository.getLoginResponseLiveData()

    //    private val data = LoginPayload(userType = "U", phone = "01012312312", password = "zephyr09")
    fun login() {
        loginRepository.login(userType = "U", phone = "01012312312", password = "zephyr09")
    }

    fun postLoginResponseLiveData(): LiveData<LoginResponse> {
        return loginResponseLiveData
    }
}