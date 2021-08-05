package com.benyq.guowanandroid.ui.page

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benyq.guowanandroid.R
import com.benyq.guowanandroid.model.UserData
import com.google.accompanist.systemuicontroller.rememberSystemUiController

data class MineClickAction(
    val loginAction: (() -> Unit)? = null,
    val scoreBoardAction: (() -> Unit)? = null,
    val scoreAction: (() -> Unit)? = null,
    val collectionAction: (() -> Unit)? = null,
    val shareAction: (() -> Unit)? = null,
    val openSourceAction: (() -> Unit)? = null,
    val aboutAuthorAction: (() -> Unit)? = null,
)


@Composable
fun MePage(userData: UserData? = null, clickAction: MineClickAction) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color(0xFF36C1BC))
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFF36C1BC))
                .padding(top = 10.dp, start = 10.dp, bottom = 10.dp)
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_mine_set),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(25.dp)
                    .align(Alignment.TopEnd)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_avatar),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 10.dp, bottom = 10.dp)
                        .clip(CircleShape)
                        .size(60.dp)

                )

                if (userData != null) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(text = userData.username, fontSize = 16.sp, color = Color.White)
                        Row() {
                            Text(text = "ID: ${userData.id}", fontSize = 12.sp, color = Color.White)
                            Text(
                                text = "lv: ${userData.level}",
                                fontSize = 12.sp,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color.White,
                                        shape = RoundedCornerShape(50)
                                    )
                                    .padding(start = 10.dp, end = 10.dp)
                            )
                        }
                    }
                } else {
                    Text(
                        text = "未登录",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 10.dp, bottom = 10.dp)
                            .align(Alignment.CenterVertically)
                            .clickable {
                                clickAction.loginAction?.invoke()
                            }
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .align(Alignment.CenterEnd)
                    .background(
                        Color(0xFF80D5D1),
                        shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp)
                    )
                    .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
                    .clickable {
                        clickAction.scoreBoardAction?.invoke()
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_mine_score),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(20.dp)
                )
                Text(text = "积分排行榜", fontSize = 14.sp, color = Color.White)

            }

        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(Color.White)
        )

        MineItem(
            R.drawable.ic_mine1,
            "我的积分",
            userData?.score?.run { "当前积分: $this" } ?: "",
            action = clickAction.scoreAction)
        MineItem(R.drawable.ic_mine2, "我的收藏", action = clickAction.collectionAction)
        MineItem(R.drawable.ic_mine3, "我的分享", action = clickAction.shareAction)

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(Color.LightGray)
        )

        MineItem(R.drawable.ic_mine4, "开源项目", action = clickAction.openSourceAction)
        MineItem(R.drawable.ic_mine5, "关于作者", action = clickAction.aboutAuthorAction)
    }

}

@Composable
fun MineItem(
    @DrawableRes iconId: Int,
    title: String,
    content: String = "",
    action: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(Color.White)
            .clickable {
                action?.invoke()
            }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 10.dp)
                .size(30.dp)
        )
        Text(text = title, fontSize = 16.sp, color = Color.Black)
        Text(
            text = content,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )
        Image(
            painter = painterResource(id = R.drawable.ic_mine_into),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
        )
    }
}

@Composable
@Preview
fun ShowMePage() {
    val userData = UserData("benyq", 9527, 18, 1759)
    MePage(null, MineClickAction())
}

@Composable
@Preview
fun ShowMineItem() {
    MineItem(R.drawable.ic_mine1, "我的积分", "1759")
}