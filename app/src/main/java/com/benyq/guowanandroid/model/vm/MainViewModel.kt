package com.benyq.guowanandroid.model.vm

import androidx.lifecycle.MutableLiveData
import com.benyq.guowanandroid.base.BaseViewModel
import com.benyq.guowanandroid.model.ArticleData
import com.benyq.guowanandroid.model.PageData

/**
 * @author benyq
 * @time 2021/8/8
 * @e-mail 1520063035@qq.com
 * @note
 */
class MainViewModel : BaseViewModel(){
    val homeArticleData = MutableLiveData<List<ArticleData>>()

    private val page: Int = 1

    fun getArticleData() {
        quickLaunch<PageData<ArticleData>> {
            request {
                apiService.articleList(page)
            }
            onSuccess {
                if (it.over) {
                    //全部获取
                }
                homeArticleData.value = it.data
            }
            onError {

            }
        }
    }

}