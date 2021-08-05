package com.benyq.guowanandroid.net

import com.benyq.guowanandroid.model.PersonScoreData
import com.benyq.guowanandroid.model.LoginData
import com.benyq.guowanandroid.model.WanResult
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface WanAndroidApi {

    @POST("/user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username")username: String, @Field("password")password: String): WanResult<LoginData>


    @GET("/lg/coin/userinfo/json")
    suspend fun personScore(): WanResult<PersonScoreData>

}