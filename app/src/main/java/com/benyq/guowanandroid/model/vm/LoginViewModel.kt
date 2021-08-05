package com.benyq.guowanandroid.model.vm

import androidx.lifecycle.MutableLiveData
import com.benyq.guowanandroid.base.BaseViewModel
import com.benyq.guowanandroid.model.LoginData

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
                loginResult.value = it
            }
            request {
                apiService.login(username, password)
            }
        }
    }

}