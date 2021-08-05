package com.benyq.guowanandroid.net

import com.benyq.guowanandroid.MMKVValue
import com.benyq.guowanandroid.model.LoginData
import com.benyq.guowanandroid.model.PersonScoreData
import com.benyq.guowanandroid.model.UserData

/**
 * @author benyq
 * @date 2021/8/5
 * @email 1520063035@qq.com
 */

object LocalCache {
    var loginData by MMKVValue("loginData", LoginData.empty())
}
