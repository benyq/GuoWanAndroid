package com.benyq.guowanandroid.ui.page

import android.text.TextUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.benyq.guowanandroid.R
import com.benyq.guowanandroid.model.ArticleData
import com.benyq.guowanandroid.model.UserData
import com.benyq.guowanandroid.model.vm.MainViewModel
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.material.math.MathUtils.lerp
import kotlin.math.absoluteValue


@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun MainPage(mainViewModel: MainViewModel = viewModel()) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.Black)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {

        AppTopBar("WanAndroid", functionIcon = R.drawable.ic_search, onFunction = {

        })

        val articleData by mainViewModel.homeArticleData.observeAsState()
        val bannerData by mainViewModel.bannerData.observeAsState()
        LazyColumn(
            modifier = Modifier
                .padding(top = 0.dp)
                .weight(1f)
        ) {
            bannerData?.let { data->
                item {
                    Banner(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth()
                            .height(200.dp), dataList = data
                    )
                    Spacer(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
                }
            }
            articleData?.run {
                itemsIndexed(this){ index, article ->
                    ArticleItem(article = article)
                    Spacer(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
                }
            }
        }
    }
    mainViewModel.bannerData()
    mainViewModel.getArticleData()
}


@ExperimentalPagerApi
@Composable
@Preview
fun ShowMainPage() {
    MainPage()
}

@Composable
@Preview
fun ShowArticleItem() {
    ArticleItem(article = fakeData()[0])
}

@Composable
fun ArticleItem(article: ArticleData) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = 5.dp, shape = RoundedCornerShape(5.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Text(
                text = article.title,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp
            )
            Row(modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()) {

                val content = if (TextUtils.isEmpty(article.shareUser)) {
                    "??????: ${article.author}"
                }else {
                    "?????????: ${article.shareUser}"
                }
                Text(
                    text = content,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = "??????: ${article.chapterName}",
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
            Text(
                text = "??????: ${article.niceDate}",
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

fun fakeData(): List<ArticleData> {
    val article = ArticleData(
        apkLink="",
        audit=1,
        author="?????????",
        canEdit=false,
        chapterId=543,
        chapterName="Android????????????",
        collect=false,
        courseId=13,
        desc="",
        descMd="",
        envelopePic="",
        fresh=false,
        host="",
        id=19132,
        link="https://www.wanandroid.com/blog/show/3039",
        niceDate="2021-07-28 00:00",
        niceShareDate="2021-07-28 00:10",
        origin="",
        prefix="",
        projectLink="",
        publishTime=1627401600000,
        realSuperChapterId=542,
        selfVisible=0,
        shareDate=1627402200000,
        shareUser="",
        superChapterId=543,
        superChapterName="????????????",
        tags= listOf(),
        title="Android ???????????? ???2021-07-21 ~ 2021-07-28???",
        type=0,
        userId=-1,
        visible=1,
        zan=0
    )

    return listOf(article, article, article, article, article, article, article)
}
