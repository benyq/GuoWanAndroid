package com.benyq.guowanandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.benyq.guowanandroid.base.BaseActivity
import com.benyq.guowanandroid.login.LoginActivity
import com.benyq.guowanandroid.model.vm.LoginViewModel
import com.benyq.guowanandroid.ui.page.*
import com.benyq.guowanandroid.ui.theme.GuoWanAndroidTheme
import com.orhanobut.logger.Logger

class MainActivity : BaseActivity<LoginViewModel>() {


    private lateinit var startLoginLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GuoWanAndroidTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        HomePage()
                    }
                }
            }
        }
        createLoginLauncher()
    }

    private fun createLoginLauncher() {
        startLoginLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
                Logger.d("activity result ${result?.data?.getStringExtra("value") ?: ""}")
            }
    }


}