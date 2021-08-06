package com.benyq.guowanandroid.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.graphics.Color
import com.benyq.guowanandroid.base.BaseActivity
import com.benyq.guowanandroid.model.vm.LoginViewModel
import com.benyq.guowanandroid.ui.page.LoginPage
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class LoginActivity : BaseActivity<LoginViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(Color.White)
            LoginPage({
                finish()
            })
        }
        viewModel.loginResult.observe(this) {
            val data = Intent()
            data.putExtra("value", "hello world")
            setResult(RESULT_OK, data)
            finish()
        }
    }
}