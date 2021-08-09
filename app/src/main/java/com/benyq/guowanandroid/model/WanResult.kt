package com.benyq.guowanandroid.model

import com.google.gson.annotations.SerializedName

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

data class PageData<T>(
    val curPage: Int,
    @SerializedName("datas")
    val data: List<T>,
    val offset: Int,
    val pageCount: Int,
    val size: Int,
    val total: Int,
    val over: Boolean,
)