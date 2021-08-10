package com.benyq.guowanandroid.model.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.benyq.guowanandroid.base.BaseViewModel
import com.benyq.guowanandroid.model.LoginData
import com.benyq.guowanandroid.net.LocalCache

/**
 *
 * @author benyq
 * @date 2021/8/5
 * @email 1520063035@qq.com
 */
class LoginViewModel : BaseViewModel() {

    var username by mutableStateOf("")
    var password by mutableStateOf("")

    val loginResult = MutableLiveData<LoginData>()

    fun login() {
        quickLaunch<LoginData> {
            onSuccess {
                LocalCache.loginData = it
                loginResult.value = it
            }
            request {
                apiService.login(username, password)
            }
        }
    }

}