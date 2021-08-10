package com.benyq.guowanandroid.model.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.benyq.guowanandroid.base.BaseViewModel

/**
 *
 * @author benyq
 * @date 2021/8/9
 * @email 1520063035@qq.com
 */
class HomeViewModel : BaseViewModel(){

    var currentPosition by mutableStateOf(0)

}