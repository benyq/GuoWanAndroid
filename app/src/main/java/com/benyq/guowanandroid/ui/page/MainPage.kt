package com.benyq.guowanandroid.ui.page

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benyq.guowanandroid.ui.theme.GrayApp


@Composable
fun MainPage() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        var name by remember {
            mutableStateOf("hello")
        }
        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(GrayApp, shape = RoundedCornerShape(8.dp)),
            textStyle = TextStyle(color = Color.Black, fontSize = 20.sp, textAlign = TextAlign.Start)
        )
        Button(onClick = {
            Log.e("MainPage", "MainPage: name $name")
        }, modifier = Modifier.padding(0.dp, 8.dp)) {
            Text(text = "确认")
        }
    }
}


@Composable
@Preview
fun ShowMainPage() {
    MainPage()
}

