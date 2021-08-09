package com.benyq.guowanandroid.net

import com.benyq.guowanandroid.model.*
import retrofit2.http.*

interface WanAndroidApi {

    @POST("/user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username")username: String, @Field("password")password: String): WanResult<LoginData>


    @GET("/lg/coin/userinfo/json")
    suspend fun personScore(): WanResult<PersonScoreData>

    @GET("/article/list/{page}/json")
    suspend fun articleList(@Path("page") page: Int): WanResult<PageData<ArticleData>>
}