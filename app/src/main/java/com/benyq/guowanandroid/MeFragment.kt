package com.benyq.guowanandroid

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import com.benyq.guowanandroid.base.BaseFragment
import com.benyq.guowanandroid.model.UserData
import com.benyq.guowanandroid.model.vm.MeViewModel
import com.benyq.guowanandroid.net.LocalCache
import com.benyq.guowanandroid.ui.page.MePage
import com.benyq.guowanandroid.ui.page.MineClickAction

/**
 * @author benyq
 * @time 2021/8/5
 * @e-mail 1520063035@qq.com
 * @note
 */
class MeFragment : BaseFragment<MeViewModel>(){


    override fun setContent(view: ComposeView) {
        view.setContent {
            MePage(viewModel.userData, clickAction = MineClickAction(

            ))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}