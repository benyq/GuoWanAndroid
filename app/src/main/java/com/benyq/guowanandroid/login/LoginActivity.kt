package com.benyq.guowanandroid.login

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benyq.guowanandroid.base.BaseActivity
import com.benyq.guowanandroid.model.vm.LoginViewModel
import com.benyq.guowanandroid.ui.page.LoginPage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.orhanobut.logger.Logger

class LoginActivity : BaseActivity<LoginViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(Color.White)
            Scaffold(modifier = Modifier.fillMaxSize()) {
                LoginPage({
                    finish()
                })
            }
        }
        viewModel.loginResult.observe(this) {
            finish()
        }
    }
}