package com.benyq.guowanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.benyq.guowanandroid.getClass
import com.benyq.guowanandroid.ui.page.MainPage

/**
 * @author benyq
 * @time 2021/8/5
 * @e-mail 1520063035@qq.com
 * @note
 */
abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(getClass(this))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext()).apply {

        }
        setContent(composeView)
        return composeView
    }

    protected abstract fun setContent(view: ComposeView)
}