package com.benyq.guowanandroid.model.vm

import androidx.lifecycle.MutableLiveData
import com.benyq.guowanandroid.base.BaseViewModel
import com.benyq.guowanandroid.model.PersonScoreData
import com.benyq.guowanandroid.model.UserData
import com.benyq.guowanandroid.net.LocalCache

/**
 * @author benyq
 * @time 2021/8/5
 * @e-mail 1520063035@qq.com
 * @note
 */
class MeViewModel : BaseViewModel(){

    val userData = MutableLiveData<UserData>()

    init {
        LocalCache.loginData.run {
            if (!isEmpty()) {
                userData.value = UserData(username, id, coinCount, -1)
            }
        }
    }

    fun getCoinCount() {
        quickLaunch<PersonScoreData> {
            onSuccess {
                val loginData = LocalCache.loginData
                userData.value = UserData(loginData.username, loginData.id, it.coinCount, it.rank)
            }
            request {
                apiService.personScore()
            }
        }
    }
}