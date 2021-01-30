package com.alexanderpodkopaev.developerslifepost.data

class PostRepositoryImpl(private val postApi: DevLifePostApi) : PostRepository {

    override suspend fun getPost(): PostModel {
        return postApi.getRandomPost()
    }
}