package com.benyq.guowanandroid.ui.page

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.benyq.guowanandroid.R
import com.benyq.guowanandroid.model.UserData
import com.benyq.guowanandroid.ui.Pager
import com.benyq.guowanandroid.ui.PagerState

/**
 * @author benyq
 * @time 2021/8/7
 * @e-mail 1520063035@qq.com
 * @note
 */

@Composable
fun HomePage() {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState: PagerState = run {
            remember() { PagerState(maxPage = 3) }
        }
        Pager(state = pagerState, modifier = Modifier.weight(1f)) {

            when(page) {
                0 -> {
                    MainPage()
                }
                1 -> {
                    ArticlePage("https://www.wanandroid.com/blog/show/3039")
                }
                2 -> {
                    MainPage()
                }
                3 -> {
                    val test = MutableLiveData<UserData>()
                    MePage(userLiveData = test, clickAction = MineClickAction())
                }
            }
        }
        HomeBottomBar(current = 0, currentChanged = {
            pagerState.currentPage = it
        })
    }
}


@Composable
fun HomeBottomBar(current: Int, currentChanged: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(4.dp, 0.dp)
    ) {

        HomeBottomBarItem(modifier = Modifier
            .weight(1f)
            .clickable {
                currentChanged(0)
            }, R.drawable.ic_mine1, "主题", Color.Blue)
        HomeBottomBarItem(modifier = Modifier
            .weight(1f)
            .clickable {
                currentChanged(1)
            }, R.drawable.ic_mine1, "主题", Color.Blue)
        HomeBottomBarItem(modifier = Modifier
            .weight(1f)
            .clickable {
                currentChanged(2)
            }, R.drawable.ic_mine1, "主题", Color.Blue)
        HomeBottomBarItem(modifier = Modifier
            .weight(1f)
            .clickable {
                currentChanged(3)
            }, R.drawable.ic_mine1, "我的", Color.Blue)
    }
}


@Composable
fun HomeBottomBarItem(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    title: String,
    tint: Color
) {
    Column(
        modifier = modifier.padding(0.dp, 8.dp, 0.dp, 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = tint
        )
        Text(text = title, fontSize = 11.sp, color = tint)
    }
}

@Composable
@Preview
fun ShowHomeBottomBar() {
    HomeBottomBar(1, { index ->

    })
}

@Composable
@Preview
fun ShowHomePage() {
    HomePage()
}
