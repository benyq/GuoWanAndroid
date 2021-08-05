package com.benyq.guowanandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.benyq.guowanandroid.login.LoginActivity
import com.benyq.guowanandroid.net.RetrofitFactory
import com.benyq.guowanandroid.ui.page.MePage
import com.benyq.guowanandroid.ui.page.MineClickAction
import com.benyq.guowanandroid.ui.theme.GuoWanAndroidTheme
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuoWanAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        MePage(clickAction = MineClickAction(loginAction = {
                            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                        }))
                    }
                }
            }
        }

        lifecycleScope.launch {
            val userData = RetrofitFactory.wanAndroidApi.login("yzjbenyq", "yezijian520")
            val personScoreData = RetrofitFactory.wanAndroidApi.personScore()
            Logger.d(userData)
            Logger.d(personScoreData)
        }
    }
}