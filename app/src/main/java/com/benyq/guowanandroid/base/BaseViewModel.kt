package com.benyq.guowanandroid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benyq.guowanandroid.model.WanResult
import com.benyq.guowanandroid.net.RetrofitFactory
import com.benyq.guowanandroid.net.WanAndroidApi
import com.benyq.guowanandroid.tryCatch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *
 * @author benyq
 * @date 2021/8/5
 * @email 1520063035@qq.com
 */
open class BaseViewModel : ViewModel() {

    protected val apiService: WanAndroidApi = RetrofitFactory.wanAndroidApi

    private fun launch(
        block: suspend CoroutineScope.() -> Unit,
        error: ((Throwable) -> Unit)? = null,
        finalBlock: (() -> Unit)? = null
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            tryCatch({
                block()
            }, {
                error?.invoke(it)
            }, {
                finalBlock?.invoke()
            })
        }
    }

    fun <T> launch(
        request: suspend CoroutineScope.() -> WanResult<T>,
        success: (T) -> Unit,
        errorBlock: ((Throwable) -> Unit)? = null,
        finalBlock: (() -> Unit)? = null
    ) {

        launch({
            val result = request()
            if (result.errorCode == 0 && result.data != null) {
                success(result.data)
            } else {
                errorBlock?.invoke(Throwable(result.errorMsg))
            }
        }, {
            errorBlock?.invoke(it)
        }, {
            finalBlock?.invoke()
        })

    }


    fun <R> quickLaunch(block: Execute<R>.() -> Unit) {
        Execute<R>().apply {
            block()
            launch(requestBlock!!, successBlock!!, errorBlock, finalBlock)
        }
    }


    inner class Execute<R> {
         var requestBlock: (suspend CoroutineScope.() -> WanResult<R>)? = null
         var finalBlock: (() -> Unit)? = null
         var successBlock: ((R) -> Unit)? = null
         var errorBlock: ((Throwable) -> Unit)? = null

        fun request(block: suspend CoroutineScope.()->WanResult<R>){
            this.requestBlock = block
        }

        fun onSuccess(block: (R) -> Unit) {
            this.successBlock = block
        }

        fun onError(block: (Throwable) -> Unit) {
            this.errorBlock = block
        }

        fun onFinal(block: () -> Unit) {
            this.finalBlock = block
        }

    }
}