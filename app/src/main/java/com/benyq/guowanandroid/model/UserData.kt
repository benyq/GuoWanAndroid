package com.benyq.guowanandroid.model

data class UserData(val username: String, val id: Int, val score: Int, val level: Int)

data class LoginData(val username: String, val id: Int, val type: Int, val coinCount: Int)

data class PersonScoreData(val coinCount: Int, val rank: Int, val userId: Int, val username: String)

