package com.alexanderpodkopaev.developerslifepost.data

import retrofit2.http.GET

interface DevLifePostApi {
    @GET("random?json=true")
    suspend fun getRandomPost() : PostModel

}