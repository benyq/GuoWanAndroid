package com.benyq.guowanandroid.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benyq.guowanandroid.R
import com.benyq.guowanandroid.ui.theme.GrayApp

@Composable
fun AppTopBar(title: String, onBack: (() -> Unit)? = null) {
    Box(
        modifier = Modifier
            .background(GrayApp)
            .height(48.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        onBack?.run {
            Icon(painter = painterResource(R.drawable.ic_back), contentDescription = null,
                modifier = Modifier.size(36.dp).clickable(onClick = onBack).padding(8.dp))
        }
        Text(
            text = title, modifier = Modifier
                .align(Alignment.Center), fontSize = 16.sp
        )

    }
}

@Preview
@Composable
fun ShowAppToBar() {
    AppTopBar("hello") {

    }
}