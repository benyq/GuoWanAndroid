package com.benyq.guowanandroid.model

/**
 * @author benyq
 * @date 2021/8/5
 * @email 1520063035@qq.com
 */
data class WanResult<T>(val errorCode: Int, val errorMsg: String, val data: T?) {
    companion object {
        fun error(msg: String): WanResult<Any> {
            return WanResult(-1, msg, Any())
        }
    }
}