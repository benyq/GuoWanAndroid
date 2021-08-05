package com.benyq.guowanandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.benyq.guowanandroid.getClass

/**
 *
 * @author benyq
 * @date 2021/8/5
 * @email 1520063035@qq.com
 */
open class BaseActivity<T: BaseViewModel> : AppCompatActivity() {

    lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(getClass(this))
    }

}
