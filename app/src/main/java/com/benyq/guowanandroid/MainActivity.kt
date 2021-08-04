package com.benyq.guowanandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.benyq.guowanandroid.ui.page.AppTopBar
import com.benyq.guowanandroid.ui.page.MainPage
import com.benyq.guowanandroid.ui.page.MePage
import com.benyq.guowanandroid.ui.page.MineClickAction
import com.benyq.guowanandroid.ui.theme.GuoWanAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuoWanAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        MePage(clickAction = MineClickAction())
                    }
                }
            }
        }
    }
}