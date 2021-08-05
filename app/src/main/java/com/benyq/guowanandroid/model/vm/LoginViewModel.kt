package com.benyq.guowanandroid.model.vm

import androidx.lifecycle.MutableLiveData
import com.benyq.guowanandroid.base.BaseViewModel
import com.benyq.guowanandroid.model.LoginData
import com.benyq.guowanandroid.model.UserData
import com.benyq.guowanandroid.net.LocalCache

/**
 *
 * @author benyq
 * @date 2021/8/5
 * @email 1520063035@qq.com
 */
class LoginViewModel : BaseViewModel() {

    val loginResult = MutableLiveData<LoginData>()

    fun login(username: String, password: String) {
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